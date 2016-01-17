package com.org.cart.utils;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

	private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

	private static ObjectMapper objectMapper = new ObjectMapper();

	private JsonUtils(){

	}
	public static String toJsonString(Object object) {
		try {
			if(object==null) {
				return null;
			}

			return OBJECTMAPPER.writeValueAsString(object);

		} catch (Exception e) {
			LOGGER .warn(e.getMessage(), e);
			return null;

		}
    }


	public static String toJsonStringNonNull(Object object) {
		try {
			if(object==null) {
				return null;
			}

			OBJECTMAPPER.setSerializationInclusion(Include.NON_NULL);
			return OBJECTMAPPER.writeValueAsString(object);

		} catch (Exception e) {
			LOGGER .warn(e.getMessage(), e);
			return null;
		}
    }


	@SuppressWarnings({ "unchecked" })
	public static Map<String, Object> getMapObject(Object object) {
		try {
			return objectMapper.readValue(JsonUtils.toJsonString(object), Map.class);
		}catch(Exception e){
			LOGGER.warn("getMapObject Exception{}",e);
			LOGGER.warn(String.format("Exception with Map Object", object));
			return null;
		}


	}


	@SuppressWarnings({ "unchecked" })
	public static Map<String, Object> getMapObjectFromString(String str) {
		try {
			return objectMapper.readValue(str, Map.class);
		}catch(Exception e){
			LOGGER.warn("{}",e);
			return null;
		}


	}


}
