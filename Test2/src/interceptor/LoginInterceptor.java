package interceptor;
import java.util.Map;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import action.LoginAction;
import model.Users;
public class LoginInterceptor extends AbstractInterceptor{
	public String intercept(ActionInvocation ai) throws Exception {
		// �ж��Ƿ�����Ϊ��¼����(login),�����������
		if (LoginAction.class == ai.getAction().getClass())
		         return ai.invoke();
		       
		Map session = ai.getInvocationContext().getSession();
		Users user = (Users)session.get("user");
		if(user!=null){
			return ai.invoke();
		}else{
			ActionContext ac = ai.getInvocationContext();
			ac.put("errorMessage", "����û�е�¼!");
			return "login";
		}
	}
}
