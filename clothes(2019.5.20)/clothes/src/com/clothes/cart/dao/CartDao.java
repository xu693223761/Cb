package com.clothes.cart.dao;

import com.clothes.cart.model.Cart;
import com.clothes.cart.model.CartItem;

public interface CartDao {
	
	Cart getCartByUserId(Integer id);
	
	Boolean addItemToCart(CartItem cartItem);
	
	Boolean deleteItemFromCart(CartItem cartItem);
	
	Boolean incrCountByCartItem(CartItem cartItem);
	
	Boolean decrCountByCartItem(CartItem cartItem);
	
	Boolean updateCountByCartItemId(CartItem cartItem);
	
	Boolean deleteCartById(Integer id);
}
