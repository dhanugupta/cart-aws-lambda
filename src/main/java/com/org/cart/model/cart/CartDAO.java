
package com.org.cart.model.cart;

import java.util.List;

import com.org.cart.exception.DAOException;

/**
 * This interface defines the methods required for an implementation of the PetDAO object
 */
public interface CartDAO {

    Cart getCartByState(String cartState) throws DAOException;

	List<Cart> getCartByLoginId(String loginId) throws DAOException;

	String createCart(Cart cart) throws DAOException;

	void deleteItem(Cart cart) throws DAOException;

	void deleteAll(Cart cart) throws DAOException;
}
