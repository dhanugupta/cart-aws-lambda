package com.org.cart.model.cart;


public class ReadCartResponse {

	private Object cart = new Object();
	private String code;
	private String message;
	private String loginId;



	/**
	 * @return the cart
	 */
	public Object getCart() {
		return cart;
	}
	/**
	 * @param cart the cart to set
	 */
	public void setCart(Object cart) {
		this.cart = cart;
	}
	/**
	 * @return the loginId
	 */
	public String getLoginId() {
		return loginId;
	}
	/**
	 * @param loginId the loginId to set
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}



}
