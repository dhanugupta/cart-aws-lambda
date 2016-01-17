package com.org.cart.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.JsonObject;


@JsonIgnoreProperties(ignoreUnknown=true)
public class LambdaRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private JsonObject body;

	private JsonObject params;

	private JsonObject query;

	private JsonObject headers;

	private String method;

	private String httpMethod;

	private String apiId;

	private String requestId;

	private String resourceId;

	private String resourcePath;

	private String sourceId;

	private String userAgent;

	private String apiKey;

	private String stage;


	/**
	 * @return the body
	 */
	public JsonObject getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(JsonObject body) {
		this.body = body;
	}

	/**
	 * @return the query
	 */
	public JsonObject getQuery() {
		return query;
	}

	/**
	 * @param query the query to set
	 */
	public void setQuery(JsonObject query) {
		this.query = query;
	}



	/**
	 * @return the resourcePath
	 */
	public String getResourcePath() {
		return resourcePath;
	}

	/**
	 * @param resourcePath the resourcePath to set
	 */
	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	/**
	 * @return the params
	 */
	public JsonObject getParams() {
		return params;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(JsonObject params) {
		this.params = params;
	}

	/**
	 * @return the headers
	 */
	public JsonObject getHeaders() {
		return headers;
	}

	/**
	 * @param headers the headers to set
	 */
	public void setHeaders(JsonObject headers) {
		this.headers = headers;
	}

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return the httpMethod
	 */
	public String getHttpMethod() {
		return httpMethod;
	}

	/**
	 * @param httpMethod the httpMethod to set
	 */
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	/**
	 * @return the apiId
	 */
	public String getApiId() {
		return apiId;
	}

	/**
	 * @param apiId the apiId to set
	 */
	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	/**
	 * @return the requestId
	 */
	public String getRequestId() {
		return requestId;
	}

	/**
	 * @param requestId the requestId to set
	 */
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	/**
	 * @return the resourceId
	 */
	public String getResourceId() {
		return resourceId;
	}

	/**
	 * @param resourceId the resourceId to set
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	/**
	 * @return the sourceId
	 */
	public String getSourceId() {
		return sourceId;
	}

	/**
	 * @param sourceId the sourceId to set
	 */
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	/**
	 * @return the userAgent
	 */
	public String getUserAgent() {
		return userAgent;
	}

	/**
	 * @param userAgent the userAgent to set
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	/**
	 * @return the apiKey
	 */
	public String getApiKey() {
		return apiKey;
	}

	/**
	 * @param apiKey the apiKey to set
	 */
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	/**
	 * @return the stage
	 */
	public String getStage() {
		return stage;
	}

	/**
	 * @param stage the stage to set
	 */
	public void setStage(String stage) {
		this.stage = stage;
	}



}
