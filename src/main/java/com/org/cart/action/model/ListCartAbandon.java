package com.org.cart.action.model;

import java.util.ArrayList;
import java.util.List;

import com.org.cart.model.cart.Cart;

public class ListCartAbandon {
	List<Cart> cart = new ArrayList<Cart>();

	/**
	 * @return the cart
	 */
	public List<Cart> getCart() {
		return cart;
	}

	/**
	 * @param cart the cart to set
	 */
	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}






}
