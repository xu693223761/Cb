package com.clothes.item.dao.impl;

import java.util.List;

import com.clothes.item.model.Item;
import com.clothes.item.model.ItemInfo;

public  interface ItemDao {
	/**
	 * 显示商品列表
	 * @return
	 */
	public List<Item> queryAllItems();
	/**
	 * 根据列表商品ID查询详细商品信息
	 * @param id
	 * @return
	 */
	ItemInfo getItemInfoByItemId(Integer item_id);
	/**
	 * 用户选择颜色、尺寸查询详细信息
	 * @param id
	 * @param color_id
	 * @param size_id
	 * @return
	 */
	ItemInfo getItemInfoByItemId(Integer item_id,Integer color_id,Integer size_id);
	/**
	 * 搜索栏
	 * @return
	 */
	public List<Item> queryItemByname(String name,String sex,Integer type);
	
}
