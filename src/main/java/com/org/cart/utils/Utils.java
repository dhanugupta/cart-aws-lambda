package com.org.cart.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

public class Utils {

	private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);


	Utils(){

	}

	public static JsonObject getJsonObject(JsonObject inputObj,String key){
		JsonObject response = null;
        if (inputObj.get(key) != null) {
        	response = inputObj.get(key).getAsJsonObject();
        }
        return response;
	}

	public static String getStringObject(JsonObject inputObj,String key){
		String response = null;
        if (inputObj.get(key) != null) {
        	response = inputObj.get(key).getAsString();
        }
        return response;
	}


}
