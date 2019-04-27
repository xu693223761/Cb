package action;

import java.sql.SQLException;

import com.opensymphony.xwork2.ActionSupport;
import dao.UsersDao;
import model.Users;


public class registaction extends  ActionSupport{
	Users user;
	UsersDao ud = new UsersDao();
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public UsersDao getUd() {
		return ud;
	}

	public void setUd(UsersDao ud) {
		this.ud = ud;
	}

	public registaction(){}
	
	public String execute() throws Exception{
		if(null==user.getName() || "".equals(user.getName())){
            this.addFieldError("user.name", "用户名不能为空!");
            return INPUT;
        }
        if(null==user.getPassword() || "".equals(user.getPassword())){
            this.addFieldError("user.password", "密码不能为空!");
            return INPUT;
        }

			if(ud.registerUser(user)>0){
				System.out.println("注册成功");
				return SUCCESS;
			}else{
				return INPUT;
			}
	}

	
}
