/*
 * Copyright 2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance
 * with the License. A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package com.org.cart.model;

import java.io.Serializable;
import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.org.cart.application.DynamoDBConfiguration;
import com.org.cart.utils.DateUtils;

/**
 * The Pet object. This bean also contains the annotations required for the DynamoDB object mapper to work
 */
@DynamoDBTable(tableName = DynamoDBConfiguration.CART_ACCESS_TABLE_NAME)
@JsonIgnoreProperties(ignoreUnknown=true)
public class CartAccess implements Serializable {

	private static final long serialVersionUID = -5633936564993343482L;
	private String createdAt = DateUtils.getStringDate(new Date());
	private String updatedAt = DateUtils.getStringDate(new Date());

	private String accessKey;

	private String resourcePath;

	private boolean access;



	/**
	 * @return the accessKey
	 */
	@DynamoDBHashKey
    @DynamoDBAttribute(attributeName = "accessKey")
	public String getAccessKey() {
		return accessKey;
	}

	/**
	 * @param accessKey the accessKey to set
	 */
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
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
	 * @return the access
	 */
    @DynamoDBAttribute(attributeName = "access")
	public boolean isAccess() {
		return access;
	}

	/**
	 * @param access the access to set
	 */
	public void setAccess(boolean access) {
		this.access = access;
	}

	/**
	 * @return the createdAt
	 */
	@DynamoDBAttribute(attributeName = "createdAt")
	public String getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the updatedAt
	 */
	@DynamoDBAttribute(attributeName = "updatedAt")
	public String getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}


}
