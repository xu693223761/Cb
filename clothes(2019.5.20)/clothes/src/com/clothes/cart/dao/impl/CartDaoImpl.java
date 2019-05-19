package com.clothes.cart.dao.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.clothes.cart.dao.CartDao;
import com.clothes.cart.model.Cart;
import com.clothes.cart.model.CartItem;

public class CartDaoImpl implements CartDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 查询购物车(List)
	 */
	public Cart getCartByUserId(Integer user_id) {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Cart where user_id = ?");
			query.setInteger(0, user_id);
			List list = query.list();
			Cart cart = (Cart)list.get(0);
//			System.out.println(cart.toString());
//			for (CartItem item : cart.getItems()) {
//				System.out.println(item.toString());
//			}
			return cart;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * 添加至购物车
	 * @param cartItem
	 * @return
	 */
	public Boolean addItemToCart(CartItem cartItem) {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
//			Transaction transaction = session.beginTransaction();
			session.saveOrUpdate(cartItem);
			Query query = session.createQuery("update Cart set total_price = total_price + ? where id = ?");
			query.setBigDecimal(0, cartItem.getPrice());
			query.setInteger(1, cartItem.getCart_id());
			int result = query.executeUpdate();
//			transaction.commit();
			if (result < 0) {
				throw new RuntimeException("插入Cart失败");
			}
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * 从购物车中删除
	 * @param cartItem
	 * @return
	 */
	public Boolean deleteItemFromCart(CartItem cartItem) {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
//			Transaction transaction = session.beginTransaction();
			
			Query query = session.createQuery("delete from cart_item where id = ?");
			query.setInteger(0, cartItem.getId());
			query.executeUpdate();
			
			query = session.createQuery("update Cart set total_price = total_price - ? where id = ?");
			query.setBigDecimal(0, cartItem.getPrice());
			query.setInteger(1, cartItem.getCart_id());
			
//			transaction.commit();
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 增加购物车商品数量
	 * @return
	 */
	public Boolean incrCountByCartItem(CartItem cartItem) {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
//			Transaction transaction = session.beginTransaction();
			Query query = session.createQuery("update CartItem set count = count + 1 where id = ?");
			query.setInteger(0, cartItem.getId());
			int result = query.executeUpdate();
			if (result < 0) {
//				transaction.rollback();
				throw new RuntimeException("修改CartItem失败");
			}
			query = session.createQuery("update Cart set total_price = total_price + ? where id = ?");
			query.setBigDecimal(0, cartItem.getPrice());
			query.setInteger(1, cartItem.getCart_id());
			int result2 = query.executeUpdate();
			if (result2 < 0) {
				throw new RuntimeException("修改Cart失败");
			}
			query = session.createQuery("update ItemInfo set stock = stock - 1 where item_id = ? and color_id = ? and size_id = ?");
			query.setInteger(0, cartItem.getItem_id());
			query.setInteger(1, cartItem.getColor().getId());
			query.setInteger(2, cartItem.getSize().getId());
			int result3 = query.executeUpdate();
			if (result3 < 0) {
				throw new RuntimeException("修改ItemInfo失败");
			}
//			transaction.commit();
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 减少购物车商品数量
	 * @param id
	 * @param count
	 * @return
	 */
	public Boolean decrCountByCartItem(CartItem cartItem) {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
//			Transaction transaction = session.beginTransaction();
			
			Query query = session.createQuery("update CartItem set count = count - 1 where id = ?");
			query.setInteger(0, cartItem.getId());
			int result = query.executeUpdate();
			if (result < 0) {
//				transaction.rollback();
				throw new RuntimeException("修改CartItem失败");
			}
			
			query = session.createQuery("update Cart set total_price = total_price - ? where id = ?");
			query.setBigDecimal(0, cartItem.getPrice());
			query.setInteger(1, cartItem.getCart_id());
			int result2 = query.executeUpdate();
			if (result2 < 0) {
				throw new RuntimeException("修改Cart失败");
			}
			
			query = session.createQuery("update ItemInfo set stock = stock + 1 where item_id = ? and color_id = ? and size_id = ?");
			query.setInteger(0, cartItem.getItem_id());
			query.setInteger(1, cartItem.getColor().getId());
			query.setInteger(2, cartItem.getSize().getId());
			int result3 = query.executeUpdate();
			if (result3 < 0) {
				throw new RuntimeException("修改ItemInfo失败");
			}
			
//			transaction.commit();
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * 修改购物车商品数量（正负数）
	 * @return
	 */
	public Boolean updateCountByCartItemId(CartItem cartItem) {
		Session session = null;
		try {
//			session = sessionFactory.openSession();
//			Query query = session.createQuery("update CartItem set count = count + ? where id = ?");
//			query.setInteger(0, count);
//			query.setInteger(1, id);
//			int res = query.executeUpdate();
//			if (res > 0) {
//				return true;
//			} 
//			return false;
			session = sessionFactory.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			Query query = session.createQuery("update CartItem set count = ? where id = ?");
			query.setInteger(0, cartItem.getCount());
			query.setInteger(1, cartItem.getId());
			int result = query.executeUpdate();
			query = session.createQuery("update Cart set total_price = (select sum(count*price) from CartItem where cart_id = ?) where id = ?");
			query.setInteger(0, cartItem.getCart_id());
			query.setInteger(1, cartItem.getCart_id());
			int result2 = query.executeUpdate();
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	} 
	
	/**
	 * 清空购物车
	 */
	public Boolean deleteCartById(Integer id) {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("delete from Cart where id = ?");
			query.setInteger(0, id);
			int res = query.executeUpdate();
			if (res < 0) {
				throw new RuntimeException("删除Cart失败");
			}
//			int i = 1/0;
			query = session.createQuery("delete from CartItem where cart_id = ?");
			query.setInteger(0, id);
			int res1 = query.executeUpdate();
			if (res1 < 0) {
				throw new RuntimeException("删除CartItem失败");
			}
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
}
