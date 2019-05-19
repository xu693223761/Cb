package com.clothes.item.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.StandardBasicTypes;

import com.clothes.item.model.Item;
import com.clothes.item.model.ItemInfo;

public class ItemDaoImpl implements ItemDao{
	SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	/**
	 * 显示商品列表
	 * @return
	 */
	public List<Item> queryAllItems() {
		Session session=null;
		try{
			session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from item");
			List<Item> list=query.list();
			if(list.size()>0){
				return list;
			}else{
				System.out.println("queryAllItem失败");
				return null;
			}
		}catch(Exception ex){
			throw new RuntimeException(ex.getMessage());
		}
	}
	/**
	 * 根据列表商品ID查询详细商品信息
	 * @param id
	 * @return
	 */
	public ItemInfo getItemInfoByItemId(Integer item_id) {
		Session session=null;
		try{
			session=sessionFactory.getCurrentSession();
			String sql = "select top 1 *  from item_info where item_id =  ";  
			Query query = session.createSQLQuery(sql).addScalar("item_id", StandardBasicTypes.INTEGER)
					.addScalar("color_id", StandardBasicTypes.INTEGER)
					.addScalar("size_id", StandardBasicTypes.INTEGER)
					.addScalar("stock", StandardBasicTypes.INTEGER)
					.addScalar("price", StandardBasicTypes.BIG_DECIMAL)
					.addScalar("img", StandardBasicTypes.STRING);
			query.setInteger(0, item_id);
			List list = query.list();
			ItemInfo iteminfo = (ItemInfo) list.get(0);
			return iteminfo;
		}catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	/**
	 * 用户选择颜色、尺寸查询详细信息
	 * @param id
	 * @param color_id
	 * @param size_id
	 * @return
	 */
	public ItemInfo getItemInfoByItemId(Integer item_id, Integer color_id, Integer size_id) {
		Session session=null;
		try{
			session=sessionFactory.getCurrentSession();
			String sql="select * from item_info where item_id = ? and color_id = ? and size_id= ?";
			Query query =session.createQuery(sql);
			query.setInteger(0, item_id);
			query.setInteger(1, color_id);
			query.setInteger(2, size_id);
			ItemInfo iteminfo = (ItemInfo) query.list();
			return iteminfo;
		}catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	/**
	 * 搜索栏
	 * @return
	 */
	public List<Item> queryItemByname(String name,String sex,Integer type) {
		Session session=null;
		try{
			session=sessionFactory.getCurrentSession();
			String sql="select * from item_info where 1=1 and name= '%?%'";
			if(!sex.equals(null)){
				sql+="and sex = '%?%' ";
			}
			if(!type.equals(null)){
				sql+="and type = ?";
			}
			Query query = session.createQuery(sql);
			query.setString(0, name);
			query.setString(1, sex);
			query.setInteger(2, type);
			List<Item> list =  query.list();
			if(list.size()>0){
				return list;
			}else{
				System.out.println("搜索失败");
				return null;
			}
		}catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
		
}
