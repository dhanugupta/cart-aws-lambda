package com.org.cart.action;

import com.amazonaws.services.lambda.runtime.Context;
import com.org.cart.exception.AuthorizationException;
import com.org.cart.exception.BadRequestException;
import com.org.cart.exception.DAOException;
import com.org.cart.model.LambdaRequest;

public interface Action {

	String handle(LambdaRequest lambdaRequest, Context context) throws  AuthorizationException,BadRequestException,DAOException,Exception;
}
