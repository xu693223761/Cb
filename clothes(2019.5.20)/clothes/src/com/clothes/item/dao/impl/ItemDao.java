package com.clothes.item.dao.impl;

import java.util.List;

import com.clothes.item.model.Item;
import com.clothes.item.model.ItemInfo;

public  interface ItemDao {
	/**
	 * ��ʾ��Ʒ�б�
	 * @return
	 */
	public List<Item> queryAllItems();
	/**
	 * �����б���ƷID��ѯ��ϸ��Ʒ��Ϣ
	 * @param id
	 * @return
	 */
	ItemInfo getItemInfoByItemId(Integer item_id);
	/**
	 * �û�ѡ����ɫ���ߴ��ѯ��ϸ��Ϣ
	 * @param id
	 * @param color_id
	 * @param size_id
	 * @return
	 */
	ItemInfo getItemInfoByItemId(Integer item_id,Integer color_id,Integer size_id);
	/**
	 * ������
	 * @return
	 */
	public List<Item> queryItemByname(String name,String sex,Integer type);
	
}
