package cn.gss.util.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Gss on 2017/4/1.
 */
public class RandCodeInterceptor extends AbstractInterceptor {
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String rand = (String) invocation.getInvocationContext().getSession().get("rand");
		String code = request.getParameter("code");
		if (code != null) { // 如果有输入内容
			if (code.equalsIgnoreCase(rand)) {
				return invocation.invoke();
			} else { // 验证码不正确
				request.setAttribute("msg", "验证码输入有误！");
				request.setAttribute("url", "/login.jsp");
			}
		} else { //没有输入内容
			request.setAttribute("msg", "验证码不能为空！");
			request.setAttribute("url", "/login.jsp");
		}
		return "forward.page";
	}
}
