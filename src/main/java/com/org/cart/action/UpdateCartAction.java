package com.org.cart.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;
import com.org.cart.dao.DynamoDBManager;
import com.org.cart.exception.BadRequestException;
import com.org.cart.exception.DAOException;
import com.org.cart.model.LambdaRequest;
import com.org.cart.model.cart.Cart;
import com.org.cart.model.cart.CartDAO;
import com.org.cart.model.cart.ReadCartResponse;

public class UpdateCartAction extends AbstractAction{

	static final Logger LOGGER = LoggerFactory.getLogger(UpdateCartAction.class);

	private static final String LOGINID = "loginId";

	private static final String SKU = "sku";

	private static final String SUCCESS = "200";


	@Override
	public String handle(LambdaRequest lambdaRequest, Context context)
			throws BadRequestException,DAOException, Exception {

		validateRequest(lambdaRequest);

		Cart cartBody = new Gson().fromJson(lambdaRequest.getBody(), Cart.class);

		CartDAO dao = DynamoDBManager.getCartDAO();

		try {
			dao.createCart(cartBody);
		} catch (Exception e) {

			LOGGER.info("Error while creating the cart" + e.getMessage());
			throw new BadRequestException("ERROR UPDATING THE CART ITEM");
		}

		ReadCartResponse readCartResponse = new ReadCartResponse();
		readCartResponse.setLoginId(cartBody.getLoginId());
		readCartResponse.setCode(SUCCESS);

		return new Gson().toJson(readCartResponse);
	}

	private void validateRequest(LambdaRequest lambdaRequest) throws BadRequestException {
		if(!lambdaRequest.getBody().has(LOGINID)) {
			throw new BadRequestException("NO LOGINID PARAM PRESENT");
		}
		if(!lambdaRequest.getBody().has(SKU)) {
			throw new BadRequestException("NO SKU PARAM PRESENT");
		}
		if(StringUtils.isEmpty(lambdaRequest.getBody().get(LOGINID).getAsString())){
			throw new BadRequestException("EMPTY LOGINID PRESENT");
		}

		if(StringUtils.isEmpty(lambdaRequest.getBody().get(SKU).getAsString())){
			throw new BadRequestException("EMPTY SKU PRESENT");
		}

	}

}
