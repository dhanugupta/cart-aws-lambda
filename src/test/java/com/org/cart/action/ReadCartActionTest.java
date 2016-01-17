package com.org.cart.action;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.JsonObject;
import com.org.cart.action.ReadCartAction;
import com.org.cart.exception.BadRequestException;
import com.org.cart.exception.DAOException;
import com.org.cart.model.LambdaRequest;
import com.org.cart.model.cart.Cart;
import com.org.cart.model.cart.CartDAO;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring/responses-context.xml"})
public class ReadCartActionTest {

	static final Logger LOGGER = LoggerFactory.getLogger(ReadCartActionTest.class);

	private ReadCartAction readCartAction;

	private LambdaRequest lambdaRequest;

	private Context context;

	private CartDAO cartDao;

	@Before
	public void setUp() throws Exception {

		readCartAction  = new ReadCartAction();
		cartDao = mock(CartDAO.class);
		context = mock(Context.class);
		lambdaRequest = new LambdaRequest();

		//cartDao = DAOFactory.getCartDAO();

		//ReflectionTestUtils.setField(readCartAction, "cartDao", cartDao );

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = BadRequestException.class)
	public void testNoQuery() throws BadRequestException {
		readCartAction.handle(lambdaRequest, context);
	}

	@Test(expected = BadRequestException.class)
	public void testNoLoginId() throws BadRequestException {
        JsonObject queryObject = new JsonObject();
        queryObject.addProperty("name", " test");
	    lambdaRequest.setQuery(queryObject);
        LOGGER.info("query {}",lambdaRequest.getQuery());
		readCartAction.handle(lambdaRequest, context);
	}

	@Test(expected = BadRequestException.class)
	public void testEmptyLoginId() throws BadRequestException {
		JsonObject queryObject = new JsonObject();
        queryObject.addProperty("loginId", "");

	    lambdaRequest.setQuery(queryObject);
		readCartAction.handle(lambdaRequest, context);
	}

	@Test
	@Ignore
	public void testWithLoginId() throws BadRequestException, DAOException {
		JsonObject queryObject = new JsonObject();
        queryObject.addProperty("loginId", " test");

	    lambdaRequest.setQuery(queryObject);
	    when(cartDao.getCartByLoginId(anyString())).thenReturn(new ArrayList<Cart>());
	    String response = readCartAction.handle(lambdaRequest, context);
	    assertNotNull(response);
	}


}
