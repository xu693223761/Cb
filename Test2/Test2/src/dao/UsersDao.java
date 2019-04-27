package dao;

import common.HibernateSessionFactory;
import model.Users;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class UsersDao {
		//验证登录
		public boolean checkLogin(Users User){
			Session session;
			try{
				session=HibernateSessionFactory.getSession();
				String hql = "from Users where name=? and password=?";
				Query query=session.createQuery(hql);
				query.setParameter(0,User.getName());
				query.setParameter(1, User.getPassword());
				List list = query.list();
				if(list.size()>0){
					return true;
				}else{
					return false;
				}
				
			}catch(Exception ex){
				ex.printStackTrace();
				return false;
			}finally{
				HibernateSessionFactory.closeSession();
			}
		}
		//注册用户，返回受影响行数
		public int registerUser(Users user){
			Session session;
			int num=0;
			try{
				session=HibernateSessionFactory.getSession();
				Transaction trans = session.beginTransaction();
				num=Integer.parseInt(session.save(user).toString());
				trans.commit();
				
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				HibernateSessionFactory.closeSession();
			}
			return num;
		}
		//修改密码
		public boolean changePassword(Users user,String oldPwd,String newPwd){
				boolean flag=true;
				Session session;
				Transaction transaction=null;
				try{
					session=HibernateSessionFactory.getSession();
					String hql = "from Users where name=? and password=?";
					Query query = session.createQuery(hql);
					query.setParameter(0, user.getName());
					query.setParameter(1, user.getPassword());
					List<Users> list = query.list();
					if(list.size()==0){
						flag = false;//旧密码错误
					}else{
						user=list.get(0);
						user.setPassword(newPwd);
						transaction = session.beginTransaction();
						session.update(user);
						transaction.commit();
					}
					return flag;
				}catch(Exception ex){
					ex.printStackTrace();
					return false;
				}finally{
					HibernateSessionFactory.closeSession();
				}
		}
		
		
}
