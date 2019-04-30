package action;
import java.util.Map;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.Users;
import dao.UsersDao;
import java.util.*;

public class LoginAction extends ActionSupport{
	Users user;
	UsersDao ud=new UsersDao();
	public LoginAction(){}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}

	public String execute(){
		if(null==user.getName() || "".equals(user.getName())){
            this.addFieldError("user.name", "�û�������Ϊ��!");
            return INPUT;
        }
        if(null==user.getPassword() || "".equals(user.getPassword())){
            this.addFieldError("user.password", "���벻��Ϊ��!");
            return INPUT;
        }
	
		if(ud.checkLogin(user)){
			//�����¼�û���
			Map session=ActionContext.getContext().getSession();
			session.put("user",user);
			return SUCCESS;
		}else{
			return INPUT;
		}
	}

}
