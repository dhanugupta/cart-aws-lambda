package com.org.cart.action;

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

public class DeleteCartAction extends AbstractAction{

	static final Logger LOGGER = LoggerFactory.getLogger(DeleteCartAction.class);

	private static final String LOGINID = "loginId";

	private static final String SUCCESS = "200";

	@Override
	public String handle(LambdaRequest lambdaRequest, Context context)
			throws BadRequestException {

		validateRequest(lambdaRequest);

		CartDAO dao = DynamoDBManager.getCartDAO();
		Cart cart = new Cart();
		cart.setLoginId(lambdaRequest.getQuery().get(LOGINID).getAsString());
		if(!StringUtils.isEmpty(lambdaRequest.getQuery().get("sku"))){
			cart.setSku(lambdaRequest.getQuery().get("sku").getAsString());
		}
		try {
			dao.deleteItem(cart);
		} catch (Exception e) {
			LOGGER.info("Error while deleting the cart item response\n" + e.getMessage());
			throw new BadRequestException("ERROR Deleting THE Cart Data");
		}

		ReadCartResponse readCartResponse = new ReadCartResponse();

		readCartResponse.setCode(SUCCESS);

		return new Gson().toJson(readCartResponse);
	}

	private void validateRequest(final LambdaRequest lambdaRequest) throws BadRequestException {
		if(!lambdaRequest.getQuery().has(LOGINID)) {
			throw new BadRequestException("NO LOGINID PARAM PRESENT");
		}
		if(StringUtils.isEmpty(lambdaRequest.getQuery().get(LOGINID).getAsString())){
			throw new BadRequestException("EMPTY LOGINID PRESENT");
		}

	}

}
