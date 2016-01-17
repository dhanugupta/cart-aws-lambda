package com.org.cart.action;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;
import com.org.cart.dao.DynamoDBManager;
import com.org.cart.exception.BadRequestException;
import com.org.cart.model.LambdaRequest;
import com.org.cart.model.cart.Cart;
import com.org.cart.model.cart.CartDAO;
import com.org.cart.model.cart.ReadCartResponse;


public class ReadCartAction extends AbstractAction{

	static final Logger LOGGER = LoggerFactory.getLogger(ReadCartAction.class);

	private static final String LOGINID = "loginId";

	private static final String SUCCESS = "200";

	@Override
	public String handle(LambdaRequest lambdaRequest, Context context)
			throws BadRequestException {

		validateRequest(lambdaRequest);

		String loginId = lambdaRequest.getQuery().get(LOGINID).getAsString();

		CartDAO dao = DynamoDBManager.getCartDAO();
		List<Cart> cartList = new ArrayList<Cart>();
		try {
			cartList = dao.getCartByLoginId(loginId);
		} catch (Exception e) {
			LOGGER.info("ERROR RETRIEVING THE DATA FROM DYANMODB " + e.getMessage());
			throw new BadRequestException("ERROR RETRIEVING THE DATA FROM DYANMODB");
		}

		ReadCartResponse readCartResponse = new ReadCartResponse();
		readCartResponse.setLoginId(loginId);
		readCartResponse.setCart(cartList);
		readCartResponse.setCode(SUCCESS);

		return new Gson().toJson(readCartResponse);

	}

	private void validateRequest(LambdaRequest lambdaRequest) throws BadRequestException {
		if(lambdaRequest.getQuery() == null){
			throw new BadRequestException("NO REQUEST PARAM PRESENT");
		}
		if(!lambdaRequest.getQuery().has(LOGINID)) {
			throw new BadRequestException("NO LOGINID PARAM PRESENT");
		}
		if(StringUtils.isEmpty(lambdaRequest.getQuery().get(LOGINID).getAsString())){
			LOGGER.info("jkshfjs");
			throw new BadRequestException("EMPTY LOGINID PRESENT");
		}

	}

}
