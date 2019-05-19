package com.clothes.cart.model;

import java.math.BigDecimal;

import com.clothes.item.model.Color;
import com.clothes.item.model.Size;

public class CartItem {
	private Integer id;
	private String name;
	private String img;
	private Integer count;
	private BigDecimal price;
	private Integer item_id;
	private Color color;
	private Size size;
	private Integer cart_id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getCart_id() {
		return cart_id;
	}
	public void setCart_id(Integer cart_id) {
		this.cart_id = cart_id;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Size getSize() {
		return size;
	}
	public void setSize(Size size) {
		this.size = size;
	}
	public Integer getItem_id() {
		return item_id;
	}
	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}
	@Override
	public String toString() {
		return "CartItem [id=" + id + ", name=" + name + ", img=" + img + ", count=" + count + ", price=" + price
				+ ", item_id=" + item_id + ", color=" + color + ", size=" + size + ", cart_id=" + cart_id + "]";
	}
	
	
}
