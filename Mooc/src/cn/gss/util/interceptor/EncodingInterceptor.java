package cn.gss.util.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Gss on 2017/4/1.
 */
public class EncodingInterceptor extends AbstractInterceptor {
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		return invocation.invoke();
	}
}
