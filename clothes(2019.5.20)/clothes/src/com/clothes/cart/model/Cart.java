package com.clothes.cart.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class Cart {
	private Integer id;
	private BigDecimal total_price;
	private Integer user_id;
	private Set<CartItem> items;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BigDecimal getTotal_price() {
		return total_price;
	}
	public void setTotal_price(BigDecimal total_price) {
		this.total_price = total_price;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Set<CartItem> getItems() {
		return items;
	}
	public void setItems(Set<CartItem> items) {
		this.items = items;
	}
	@Override
	public String toString() {
		return "Cart [id=" + id + ", total_price=" + total_price + ", user_id=" + user_id + ", items=" + items + "]";
	}
}
