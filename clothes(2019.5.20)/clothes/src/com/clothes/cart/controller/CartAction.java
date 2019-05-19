package com.clothes.cart.controller;

import java.math.BigDecimal;

import com.clothes.cart.model.Cart;
import com.clothes.cart.model.CartItem;
import com.clothes.cart.service.CartService;
import com.clothes.item.model.Color;
import com.clothes.item.model.Size;
import com.opensymphony.xwork2.ActionSupport;

public class CartAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CartService cartService;

	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}

	public String getCartByUserId() {
		Cart cart = cartService.getCartByUserId(1);
//		System.out.println(cart.toString());
//		for (CartItem cartItem : cart.getItems()) {
//			System.out.println(cartItem.getColor().getColor().toString());
//			System.out.println(cartItem.getSize().getSize().toString());
//		}
		return SUCCESS;
	}
	
	
	/**
	 * 	Boolean addItemToCart(CartItem cartItem);
	
	Boolean deleteItemFromCart(CartItem cartItem);
	
	Boolean incrCountByCartItem(CartItem cartItem);
	
	Boolean decrCountByCartItem(CartItem cartItem);
	Boolean updateCountByCartItemId(CartItem cartItem);
	
	Boolean deleteCartById(Integer id);
	 */
	
	
	public String deleteCartById() {
		cartService.deleteCartById(2);
		return SUCCESS;
	}
	
	public String addItemToCart() {
		/**
		 * saveorupdate不太好用，会更新其他表
		 */
		CartItem cartItem = new CartItem();
		Color color = new Color();
		color.setId(4);
		color.setColor("灰绿色");
		cartItem.setColor(color);
		cartItem.setCount(1);
		cartItem.setPrice(new BigDecimal(100));
		Size size = new Size();
		size.setId(3);
		size.setSize("xl");
		cartItem.setSize(size);
		cartItem.setItem_id(1);
		cartItem.setCart_id(1);
		cartService.addItemToCart(cartItem);
		return SUCCESS;
	}
}
