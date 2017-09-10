package cn.gss.mooc.action;

import cn.gss.mooc.pojo.Admin;
import cn.gss.mooc.pojo.User;
import cn.gss.mooc.service.AdminService;
import cn.gss.mooc.service.UserService;
import cn.gss.util.MD5Code;
import cn.gss.util.action.AbstractAction;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
@ParentPackage("root")
@Namespace("/")
@Action("common")
public class CommonAction extends AbstractAction {
	private final String admin_loginRule = "admin.username:string|admin.password:string";
	private final String user_loginRule = "user.username:string|user.password:string";
	private final String user_registerRule = "user.username:string|user.password:string|user.realname:string|user.sex:string|user.city:string|user.email:string|user.qq:string";
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserService userService;

	private Admin admin = new Admin();
	private User user = new User();

	public Admin getAdmin() {
		return admin;
	}

	public User getUser() {
		return user;
	}

	public String admin_login() {
		Admin res = null;
		try {
			admin.setPassword(new MD5Code().getMD5ofStr(admin.getPassword()));
			if ((res = adminService.login(admin)) != null) {
				getSession().setAttribute("admin", res);
				setMsgAndUrl("login.success", "admin.index.page");
			} else {
				setMsgAndUrl("login.failure", "admin.login.page");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "forward.page";
	}

	public String user_login() {
		User res = null;
		try {
			user.setPassword(new MD5Code().getMD5ofStr(user.getPassword()));
			if ((res = userService.login(user)) != null) {
				getSession().setAttribute("user", res);
				setMsgAndUrl("login.success", "user.index.action");
			} else {
				setMsgAndUrl("login.failure", "user.login.page");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "forward.page";
	}

	public String user_register() {
		user.setPassword(new MD5Code().getMD5ofStr(user.getPassword()));
		StringBuilder sb = new StringBuilder();
		for (String s : getRequest().getParameterValues("hobbys")) {
			sb.append(s).append("、");
		}
		user.setHobbys(sb.toString());
		user.setState(1);
		try {
			if (userService.register(user)) {
				setMsgAndUrl("register.success", "user.login.page");
			} else {
				setMsgAndUrl("register.failure", "user.register.page");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "forward.page";
	}

	public String user_logout() {
		getSession().removeAttribute("user");
		setMsgAndUrl("logout.msg", "user.index.action");
		return "forward.page";
	}

	public String admin_logout() {
		getSession().removeAttribute("admin");
		setMsgAndUrl("logout.msg", "admin.login.page");
		return "forward.page";
	}

	@Override
	public String getDefaultColumn() {
		return null;
	}

	@Override
	public String getColumnData() {
		return null;
	}

	@Override
	protected String getSubType() {
		return null;
	}
}
