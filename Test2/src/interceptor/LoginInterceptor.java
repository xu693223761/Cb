package interceptor;
import java.util.Map;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import action.LoginAction;
import model.Users;
public class LoginInterceptor extends AbstractInterceptor{
	public String intercept(ActionInvocation ai) throws Exception {
		// 判断是否请求为登录界面(login),如果是则不拦截
		if (LoginAction.class == ai.getAction().getClass())
		         return ai.invoke();
		       
		Map session = ai.getInvocationContext().getSession();
		Users user = (Users)session.get("user");
		if(user!=null){
			return ai.invoke();
		}else{
			ActionContext ac = ai.getInvocationContext();
			ac.put("errorMessage", "您还没有登录!");
			return "login";
		}
	}
}
