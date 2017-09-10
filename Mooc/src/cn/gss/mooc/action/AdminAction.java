package cn.gss.mooc.action;

import cn.gss.mooc.pojo.Admin;
import cn.gss.mooc.pojo.Message;
import cn.gss.mooc.pojo.Theme;
import cn.gss.mooc.pojo.User;
import cn.gss.mooc.service.*;
import cn.gss.util.MD5Code;
import cn.gss.util.Page;
import cn.gss.util.action.AbstractAction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONBuilder;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository
@ParentPackage("root")
@Namespace("/")
@Action("admin")
@InterceptorRef("adminStack")
public class AdminAction extends AbstractAction {
	private String updatePwdRule = "admin.password:string|newpwd:string";
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserService userService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private ThemeService themeService;
	@Autowired
	private ReplyService replyService;
	private Admin admin = new Admin();

	public Admin getAdmin() {
		return admin;
	}

	public String updatePwd() {
		try {
			System.err.println(admin.getUsername()+","+admin.getPassword());
			admin.setPassword(new MD5Code().getMD5ofStr(admin.getPassword()));

			if (adminService.login(admin) != null) {
				String newpwd = getParameter("newpwd");
				if (adminService.updatePwd(admin.getAid(), new MD5Code().getMD5ofStr(newpwd))) {
					setMsgAndUrl("password.update.success", "admin.login.page");
				} else {
					setMsgAndUrl("password.update.failure", "admin.index.page");
				}
			}else{
				setMsgAndUrl("password.update.failure", "admin.login.index");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "forward.page";
	}

	public void index(){
		JSONObject res = new JSONObject();
		try {
			res.put("msgday",messageService.findCountByDate("day"));
			res.put("msgweek",messageService.findCountByDate("week"));
			res.put("msgmonth",messageService.findCountByDate("month"));
			res.put("replyday",replyService.findCountByDate("day"));
			res.put("replyweek",replyService.findCountByDate("week"));
			res.put("replymonth",replyService.findCountByDate("month"));
			print(res);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alltheme(){
		try {
			List<Theme> list = messageService.insertPre();
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			print(gson.toJson(list));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void msglist(){
		int curPage = Integer.parseInt(getParameter("curPage"));
		int pageNum = Integer.parseInt(getParameter("pageNum"));
		int theid = Integer.parseInt(getParameter("theid"));
		String username = getParameter("username");
		String kw = getParameter("kw");
		Page<Message> page = new Page<>(curPage,pageNum,"",kw);
		try {
			page = messageService.findByAdmin(username,theid,page);
			JSONObject res = new JSONObject();
			res.put("totalPage",page.getTotalPage());
			res.put("rows",page.getRows());
			JSONArray arr = new JSONArray();
			for (Message m : page.getData()) {
				JSONObject t = new JSONObject();
				t.put("msgid", m.getMsgid());
				t.put("msgtopic", m.getMsgtopic());
				t.put("msgtime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(m.getMsgtime()));
				t.put("state", m.getState());
				JSONObject user = new JSONObject();
				user.put("userid", m.getUser().getUserid());
				user.put("username", m.getUser().getUsername());
				user.put("realname", m.getUser().getRealname());
				t.put("user", user);
				arr.add(t);
			}
			res.put("data", arr);
			System.err.println(res);
			print(res);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void togglemsgstate(){
		int msgid = Integer.parseInt(getParameter("msgid"));
		int state = Integer.parseInt(getParameter("state"));
		print(messageService.changeState(msgid,state));
	}

	public void userlist(){
		int curPage = Integer.parseInt(getParameter("curPage"));
		int pageNum = Integer.parseInt(getParameter("pageNum"));
		String usernamekw = getParameter("kw");
		Page<User> page = userService.listSplit(curPage, pageNum, "username", usernamekw);
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		JSONObject res = new JSONObject();
		res.put("totalPage", page.getTotalPage());
		res.put("rows", page.getRows());
		res.put("data",gson.toJson(page.getData()));
		print(res);
	}
	public void toggleuserstate(){
		int userid = Integer.parseInt(getParameter("userid"));
		int state = Integer.parseInt(getParameter("state"));
		print(userService.changeState(userid,state));
	}

	public void themelist(){
		int curPage = Integer.parseInt(getParameter("curPage"));
		int pageNum = Integer.parseInt(getParameter("pageNum"));
		String kw = getParameter("kw");
		Page<Theme> page = new Page<>(curPage, pageNum, "thename", kw);
		page = themeService.findByAdmin(page);
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		JSONObject res = new JSONObject();
		res.put("totalPage", page.getTotalPage());
		res.put("rows", page.getRows());
		res.put("data",gson.toJson(page.getData()));
		print(res);
	}

	public void addtheme(){
		String thename = getParameter("thename");
		print(themeService.insert(new Theme(thename,0)));
	}

	public void deltheme(){
		int theid = Integer.parseInt(getParameter("theid"));
		print(themeService.delete(theid));
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
		return "管理员";
	}
}
