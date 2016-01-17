package com.org.cart.model.cart;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.org.cart.application.DynamoDBConfiguration;
import com.org.cart.utils.DateUtils;

/**
 * The Cart object. This bean also contains the annotations required for the DynamoDB object mapper to work
 */
@DynamoDBTable(tableName = DynamoDBConfiguration.CART_TABLE_NAME)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Cart implements Serializable {
    /**
	 *
	 */
	private static final long serialVersionUID = -5633936564993343482L;
	private String itemState ="OPEN";
    private String loginId;

    private String sku;

    private String email;
	private String createdAt = DateUtils.getStringDate(new Date());
	private String updatedAt = DateUtils.getStringDate(new Date());

	private String source;

	private String client;

	private String clientIp;
	private String lang = "en";
	private String locale = "US";

	private String country = "US";


	private String checkoutUrl;


	private Map<String,String> additionalAttributes = new HashMap<String,String>();


    @DynamoDBAttribute(attributeName = "itemState")
    public String getItemState() {
        return itemState;
    }

    public void setItemState(String itemState) {
        this.itemState = itemState;
    }

    @DynamoDBHashKey
    @DynamoDBAttribute(attributeName = "loginId")
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

	/**
	 * @return the sku
	 */
    @DynamoDBHashKey
    @DynamoDBAttribute(attributeName = "sku")
	public String getSku() {
		return sku;
	}

	/**
	 * @param sku the sku to set
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the email
	 */
	@DynamoDBAttribute(attributeName = "email")
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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


	/**
	 * @return the source
	 */
	@DynamoDBAttribute(attributeName = "source")
	public String getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the clientIp
	 */
	@DynamoDBAttribute(attributeName = "clientIp")
	public String getClientIp() {
		return clientIp;
	}

	/**
	 * @param clientIp the clientIp to set
	 */
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	/**
	 * @return the lang
	 */
	@DynamoDBAttribute(attributeName = "lang")
	public String getLang() {
		return lang;
	}

	/**
	 * @param lang the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}

	/**
	 * @return the locale
	 */
	@DynamoDBAttribute(attributeName = "locale")
	public String getLocale() {
		return locale;
	}

	/**
	 * @param locale the locale to set
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}

	/**
	 * @return the country
	 */
	@DynamoDBAttribute(attributeName = "country")
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the client
	 */
	@DynamoDBAttribute(attributeName = "client")
	public String getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(String client) {
		this.client = client;
	}



	/**
	 * @return the checkoutUrl
	 */
	@DynamoDBAttribute(attributeName = "checkoutUrl")
	public String getCheckoutUrl() {
		return checkoutUrl;
	}

	/**
	 * @param checkoutUrl the checkoutUrl to set
	 */
	public void setCheckoutUrl(String checkoutUrl) {
		this.checkoutUrl = checkoutUrl;
	}



	/**
	 * @return the additionalAttributes
	 */
	@DynamoDBAttribute(attributeName = "additionalAttributes")
	public Map<String, String> getAdditionalAttributes() {
		return additionalAttributes;
	}

	/**
	 * @param additionalAttributes the additionalAttributes to set
	 */
	public void setAdditionalAttributes(Map<String, String> additionalAttributes) {
		this.additionalAttributes = additionalAttributes;
	}



}
