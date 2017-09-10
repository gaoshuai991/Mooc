package cn.gss.mooc.interceptor;

import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class AdminInterceptor extends AbstractInterceptor {
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> map = invocation.getInvocationContext()
				.getSession();
		if (map.get("admin") == null) { // 现在没有admin的属性
			ServletActionContext.getRequest().setAttribute("msg", "您还未登录！");
			ServletActionContext.getRequest().setAttribute("url", "/admin_login.jsp");
			return "forward.page";
		} else {
			return invocation.invoke();
		}
	}

}
