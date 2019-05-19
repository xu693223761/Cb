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
	 * ��ѯ���ﳵ(List)
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
	 * ��������ﳵ
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
				throw new RuntimeException("����Cartʧ��");
			}
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * �ӹ��ﳵ��ɾ��
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
	 * ���ӹ��ﳵ��Ʒ����
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
				throw new RuntimeException("�޸�CartItemʧ��");
			}
			query = session.createQuery("update Cart set total_price = total_price + ? where id = ?");
			query.setBigDecimal(0, cartItem.getPrice());
			query.setInteger(1, cartItem.getCart_id());
			int result2 = query.executeUpdate();
			if (result2 < 0) {
				throw new RuntimeException("�޸�Cartʧ��");
			}
			query = session.createQuery("update ItemInfo set stock = stock - 1 where item_id = ? and color_id = ? and size_id = ?");
			query.setInteger(0, cartItem.getItem_id());
			query.setInteger(1, cartItem.getColor().getId());
			query.setInteger(2, cartItem.getSize().getId());
			int result3 = query.executeUpdate();
			if (result3 < 0) {
				throw new RuntimeException("�޸�ItemInfoʧ��");
			}
//			transaction.commit();
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * ���ٹ��ﳵ��Ʒ����
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
				throw new RuntimeException("�޸�CartItemʧ��");
			}
			
			query = session.createQuery("update Cart set total_price = total_price - ? where id = ?");
			query.setBigDecimal(0, cartItem.getPrice());
			query.setInteger(1, cartItem.getCart_id());
			int result2 = query.executeUpdate();
			if (result2 < 0) {
				throw new RuntimeException("�޸�Cartʧ��");
			}
			
			query = session.createQuery("update ItemInfo set stock = stock + 1 where item_id = ? and color_id = ? and size_id = ?");
			query.setInteger(0, cartItem.getItem_id());
			query.setInteger(1, cartItem.getColor().getId());
			query.setInteger(2, cartItem.getSize().getId());
			int result3 = query.executeUpdate();
			if (result3 < 0) {
				throw new RuntimeException("�޸�ItemInfoʧ��");
			}
			
//			transaction.commit();
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * �޸Ĺ��ﳵ��Ʒ��������������
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
	 * ��չ��ﳵ
	 */
	public Boolean deleteCartById(Integer id) {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("delete from Cart where id = ?");
			query.setInteger(0, id);
			int res = query.executeUpdate();
			if (res < 0) {
				throw new RuntimeException("ɾ��Cartʧ��");
			}
//			int i = 1/0;
			query = session.createQuery("delete from CartItem where cart_id = ?");
			query.setInteger(0, id);
			int res1 = query.executeUpdate();
			if (res1 < 0) {
				throw new RuntimeException("ɾ��CartItemʧ��");
			}
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
}
