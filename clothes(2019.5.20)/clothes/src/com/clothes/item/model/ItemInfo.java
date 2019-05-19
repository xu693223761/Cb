package com.clothes.item.model;

import java.util.List;

public class ItemInfo {
	private Integer item_id;
	private Color color_id;
	private Size size_id;
	private Integer stock;
	private Integer price;
	private Integer img;

	public Integer getItem_id() {
		return item_id;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}

	public Color getColor_id() {
		return color_id;
	}

	public void setColor_id(Color color_id) {
		this.color_id = color_id;
	}

	public Size getSize_id() {
		return size_id;
	}

	public void setSize_id(Size size_id) {
		this.size_id = size_id;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getImg() {
		return img;
	}

	public void setImg(Integer img) {
		this.img = img;
	}

}
