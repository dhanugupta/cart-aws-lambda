package com.org.cart.router;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.org.cart.action.Action;
import com.org.cart.exception.BadRequestException;
import com.org.cart.exception.DAOException;
import com.org.cart.exception.InternalErrorException;
import com.org.cart.model.LambdaRequest;

public class RequestRouter {

	static final Logger LOGGER = LoggerFactory.getLogger(RequestRouter.class);

	private static final String ACTIONCLASS = "resourcePath";


	 /**
     * The main Lambda function handler. Receives the request as an input stream, parses the json and looks for the
     * "action" property to decide where to route the request. The "body" property of the incoming request is passed
     * to the DemoAction implementation as a request body.
     *
     * @param request  The InputStream for the incoming event. This should contain an "action" and "body" properties. The
     *                 action property should contain the namespaced name of the class that should handle the invocation.
     *                 The class should implement the DemoAction interface. The body property should contain the full
     *                 request body for the action class.
     * @param response An OutputStream where the response returned by the action class is written
     * @param context  The Lambda Context object
     * @throws BadRequestException    This Exception is thrown whenever parameters are missing from the request or the action
     *                                class can't be found
     * @throws InternalErrorException This Exception is thrown when an internal error occurs, for example when the database
     *                                is not accessible
     */
	public static void lambdaHandler(InputStream request, OutputStream response, Context context) throws BadRequestException, InternalErrorException,DAOException,Exception {
        //Initialize the context logger
    	LambdaLogger logger = context.getLogger();

    	JsonParser parser = new JsonParser();
        JsonObject inputObj;
        try {
            inputObj = parser.parse(IOUtils.toString(request)).getAsJsonObject();
        } catch (Exception e) {
            logger.log("Error while reading request\n" + e.getMessage());
            throw new InternalErrorException("Error while reading request\n"+e.getMessage());
        }

        LOGGER.info("input obj {}",inputObj);


        //Validate InputStream Request
        validateServiceObject(inputObj);

        //LOAD THE CONTEXT PROPERTIES
    	ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/*.xml");

        String actionClass = getActionClass(applicationContext,inputObj);

        applicationContext.close();

        Action action = null;

        try {
            action = Action.class.cast(Class.forName(actionClass).newInstance());
        } catch (final InstantiationException e) {
            logger.log("Error while instantiating action class\n" + e.getMessage());
            throw new InternalErrorException("Error while instantiating action class\n"+e.getMessage());
        } catch (final IllegalAccessException e) {
            logger.log("Illegal access while instantiating action class\n" + e.getMessage());
            throw new InternalErrorException("Illegal access while instantiating action class\n"+e.getMessage());
        } catch (final ClassNotFoundException e) {
            logger.log("Action class could not be found\n" + e.getMessage());
            throw new InternalErrorException("Action class could not be found\n"+e.getMessage());
        }

        if (action == null) {
            logger.log("Action class is null");
            throw new BadRequestException("Invalid action class");
        }

        String json_string = new Gson().toJson(inputObj);
        LambdaRequest lambdaRequest = new Gson().fromJson(json_string, LambdaRequest.class);
		String output = action.handle(lambdaRequest, context);//action.handle(inputObj, context);

        try {
            IOUtils.write(output, response);
        } catch (final IOException e) {
            logger.log("Error while writing response\n" + e.getMessage());
            throw new InternalErrorException(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
	private static String getActionClass(ConfigurableApplicationContext applicationContext,
			JsonObject inputObj) {

    	Map<String,Object> beanMapping = (Map<String, Object>) applicationContext.getBean("apiMap");
		String actionName = inputObj.get(ACTIONCLASS).getAsString();
		String actionBean ="";
		for (Map.Entry<String, Object> beanMap : beanMapping.entrySet()) {
			if(actionName.contains(beanMap.getKey())) {
				actionBean = beanMap.getValue().toString();
				break;
			}
		}

		return actionBean;

	}
	private static void validateServiceObject(JsonObject inputObj) throws BadRequestException {
		if(ObjectUtils.isEmpty(inputObj) || StringUtils.isEmpty(inputObj.get(ACTIONCLASS))){
            throw new BadRequestException("Could not find "+ACTIONCLASS+" key in request");
		}

	}

}
