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
            this.addFieldError("user.name", "用户名不能为空!");
            return INPUT;
        }
        if(null==user.getPassword() || "".equals(user.getPassword())){
            this.addFieldError("user.password", "密码不能为空!");
            return INPUT;
        }
	
		if(ud.checkLogin(user)){
			//保存登录用户名
			Map session=ActionContext.getContext().getSession();
			session.put("user",user);
			return SUCCESS;
		}else{
			return INPUT;
		}
	}

}
