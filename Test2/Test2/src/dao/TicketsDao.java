package dao;

import java.util.List;

import org.hibernate.Query; //���ڲ�ѯ����
import org.hibernate.Session;
import org.hibernate.Transaction;
import common.HibernateSessionFactory;
import model.Ticket;

public class TicketsDao {
	
	public int insertTicket(Ticket ticket){
		Session session=null;
		int num=0;
		try{
			session=HibernateSessionFactory.getSession();
			Transaction transaction=session.beginTransaction();
			num=Integer.parseInt(session.save(ticket).toString());
			transaction.commit();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return num;
	}

	public List<Ticket> queryTickets(){
		Session session=null;
		try{
			session=HibernateSessionFactory.getSession();
			String hql="from Ticket";
			Query query=session.createQuery(hql);
			List list=query.list();
			if(list.size()>0){
				return list;
			}else{
				System.out.print("��ѯʧ��");
				return null;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}
	//��ѯÿҳ��Ҫ��ʾ������
	public List<Ticket> queryByPage(int pageNo, int pageSize) {
		Session session=null;
		try{
			session=HibernateSessionFactory.getSession();
			String hql="from Ticket";
			Query query = session.createQuery(hql);
			query.setFirstResult((pageNo-1)*pageSize);
			query.setMaxResults(pageSize);
			List<Ticket> list=query.list();
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{//�ر�session
			HibernateSessionFactory.closeSession();//����HibernateSessionFactory�ľ�̬�����ر�Session
		}	
		
	}
	
}
