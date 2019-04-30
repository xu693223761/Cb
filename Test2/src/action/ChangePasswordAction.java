package action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.UsersDao;
import model.Users;

public class ChangePasswordAction extends ActionSupport{
	String oldpwd;
	String newpwd;
	Users user;
	
	public String getOldpwd() {
		return oldpwd;
	}

	public void setOldpwd(String oldpwd) {
		this.oldpwd = oldpwd;
	}

	public String getNewpwd() {
		return newpwd;
	}

	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	UsersDao ud=new UsersDao();
	
	public ChangePasswordAction(){}
	
	public String execute(){
		//获得当前登录用户
		Map map=ActionContext.getContext().getSession();
		user=(Users)map.get("user");
		if(ud.changePassword(user, oldpwd, newpwd)){
			return SUCCESS;
		}else{
			addFieldError("oldPwd","旧密码错误");
			return INPUT;
		}
	}

}
