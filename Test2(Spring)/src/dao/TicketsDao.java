package dao;

import java.util.List;

import org.hibernate.Query; //用于查询数据
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import model.Ticket;

public class TicketsDao{
	SessionFactory sessionFactory;
		
	public TicketsDao() {
	
	}
	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public int insertTicket(Ticket ticket){
		Session session=null;
		int num=0;
		try{
			session=sessionFactory.openSession();
			Transaction transaction=session.beginTransaction();
			num=Integer.parseInt(session.save(ticket).toString());
			transaction.commit();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return num;
	}

	public List<Ticket> queryTickets(){
		Session session=null;
		try{
			session=sessionFactory.openSession();
			String hql="from Ticket";
			Query query=session.createQuery(hql);
			List list=query.list();
			if(list.size()>0){
				return list;
			}else{
				System.out.print("查询失败");
				return null;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}
	//查询每页需要显示的数据
	public List<Ticket> queryByPage(int pageNo, int pageSize) {
		Session session=null;
		try{
			session=sessionFactory.openSession();
			String hql="from Ticket";
			Query query = session.createQuery(hql);
			query.setFirstResult((pageNo-1)*pageSize);
			query.setMaxResults(pageSize);
			List<Ticket> list=query.list();
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{//关闭session
			session.close();//调用HibernateSessionFactory的静态方法关闭Session
		}	
		
	}

	public boolean deleteTicketById(int id) {
		Session session=null;
		try{
			session=sessionFactory.openSession();
			Ticket ticket = (Ticket) session.get(Ticket.class, id);
			Transaction trans = session.beginTransaction();
			session.delete(ticket);
			trans.commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			session.close();
		}
	}

	public Ticket queryTicketById(int id) {
		Session session=null;
		try{
			session=sessionFactory.openSession();
			Ticket ticket=(Ticket) session.get(Ticket.class, id);
			return ticket;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}

	public boolean updateTicket(Ticket newticket) {
		Session session=null;
		try{
			session=sessionFactory.openSession();
			Transaction trans=session.beginTransaction();
			session.saveOrUpdate(newticket);
			trans.commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			session.close();
		}

	}
	
}
