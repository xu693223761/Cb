package com.clothes.cart.service.impl;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.clothes.cart.dao.CartDao;
import com.clothes.cart.model.Cart;
import com.clothes.cart.model.CartItem;
import com.clothes.cart.service.CartService;

public class CartServiceImpl implements CartService{

	private CartDao cartDao;

	public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}

	public Cart getCartByUserId(Integer id) {
		// TODO Auto-generated method stub
		return cartDao.getCartByUserId(id);
	}

	@Transactional(propagation=Propagation.REQUIRED,rollbackFor = {RuntimeException.class} )
	public Boolean addItemToCart(CartItem cartItem) {
		// TODO Auto-generated method stub
		return cartDao.addItemToCart(cartItem);
	}

	public Boolean deleteItemFromCart(CartItem cartItem) {
		// TODO Auto-generated method stub
		return cartDao.deleteItemFromCart(cartItem);
	}

	public Boolean incrCountByCartItem(CartItem cartItem) {
		// TODO Auto-generated method stub
		return cartDao.incrCountByCartItem(cartItem);
	}

	public Boolean decrCountByCartItem(CartItem cartItem) {
		// TODO Auto-generated method stub
		return cartDao.decrCountByCartItem(cartItem);
	}

	public Boolean updateCountByCartItemId(CartItem cartItem) {
		// TODO Auto-generated method stub
		return cartDao.updateCountByCartItemId(cartItem);
	}

	@Transactional(propagation=Propagation.REQUIRED,rollbackFor = {RuntimeException.class} )
	public Boolean deleteCartById(Integer id) {
		// TODO Auto-generated method stub
		return cartDao.deleteCartById(id);
	}

}
