package cn.gss.mooc.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "UserFilter", urlPatterns = "/user/*")
public class UserFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		if (request.getSession().getAttribute("user") == null) {
			request.setAttribute("msg", "您还未登录，请先登录！");
			request.setAttribute("url", "/login.jsp");
			request.getRequestDispatcher("/forward.jsp").forward(request, response);
		} else
			chain.doFilter(req, resp);
	}

	public void init(FilterConfig config) throws ServletException {

	}

}
