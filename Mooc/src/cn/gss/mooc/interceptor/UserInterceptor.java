package cn.gss.mooc.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;

import java.util.Map;

@SuppressWarnings("serial")
public class UserInterceptor extends AbstractInterceptor {
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> map = invocation.getInvocationContext()
				.getSession();
		if (map.get("user") == null) { // 现在没有admin的属性
			ServletActionContext.getRequest().setAttribute("msg", "您还未登录,请先登录！");
			ServletActionContext.getRequest().setAttribute("url", "/login.jsp");
			return "forward.page";
		} else {
			return invocation.invoke();
		}
	}

}
