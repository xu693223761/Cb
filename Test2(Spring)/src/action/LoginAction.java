package action;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.Users;
import dao.UsersDao;
import java.util.*;

public class LoginAction extends ActionSupport{
	private Users user;
	UsersDao usersDao;
	public LoginAction(){}
	public Users getUser() {
		return user;
	}
	
	
	public UsersDao getUsersDao() {
		return usersDao;
	}
	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}
	public void setUser(Users user) {
		this.user = user;
	}

	public String execute()throws Exception{
		if(null==user.getName() || "".equals(user.getName())){
            this.addFieldError("user.name", "�û�������Ϊ��!");
            return INPUT;
        }
        if(null==user.getPassword() || "".equals(user.getPassword())){
            this.addFieldError("user.password", "���벻��Ϊ��!");
            return INPUT;
        }
      
		if(usersDao.checkLogin(user)){
			//�����¼�û���
			Map session=ActionContext.getContext().getSession();
			session.put("user",user);
			return SUCCESS;
		}else{
			return INPUT;
		}
	}

}
