package cn.gss.mooc.action;

import cn.gss.mooc.pojo.Message;
import cn.gss.mooc.pojo.Reply;
import cn.gss.mooc.pojo.User;
import cn.gss.mooc.service.MessageService;
import cn.gss.mooc.service.ReplyService;
import cn.gss.mooc.service.UserService;
import cn.gss.util.HTMLReplace;
import cn.gss.util.IPUtil;
import cn.gss.util.MD5Code;
import cn.gss.util.Page;
import cn.gss.util.action.AbstractAction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
@ParentPackage("root")
@Namespace("/")
@Action("user")
@InterceptorRef("userStack")
@Results({@Result(name = "msg.insert.page", location = "/user/addmsg.jsp"),
		@Result(name = "mymsg.page", location = "/user/mymsg.jsp")})
public class UserAction extends AbstractAction {
	// user
	private final String updateRule = "user.realname:string|user.sex:string|user.city:string|user.email:string|user.qq:string";
	private final String updatePwdRule = "user.userid:int";
	//message
	private final String insertMsgRule = "message.msgtopic:string|message.msgcontents:string";
	@Autowired
	private MessageService messageService;
	@Autowired
	private ReplyService replyService;

	private Message message = new Message();

	public Message getMessage() {
		return message;
	}

	@Autowired
	private UserService userService;

	private User user = new User();

	public User getUser() {
		return user;
	}

	public void get() {
		int userid = Integer.parseInt(getParameter("userid"));
		try {
			User vo = userService.get(userid);
			JSONObject obj = new JSONObject();
			obj.put("userid", vo.getUserid());
			obj.put("username", vo.getUsername());
			obj.put("realname", vo.getRealname());
			obj.put("birthday", vo.getBirthday());
			obj.put("hobbys", vo.getHobbys());
			obj.put("sex", vo.getSex());
			obj.put("city", vo.getCity());
			obj.put("email", vo.getEmail());
			obj.put("qq", vo.getQq());
			obj.put("createtime", vo.getCreatetime());
			print(obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String update() {
		try {
			StringBuilder sb = new StringBuilder();
			for (String s : getRequest().getParameterValues("hobbys")) {
				sb.append(s).append("、");
			}
			user.setHobbys(sb.toString());
			if (userService.update(user)) {
				setMsgAndUrl("update.success.msg", "user.index.action");
			} else {
				setMsgAndUrl("update.failure.msg", "user.center.page");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "forward.page";
	}

	public String updatePwd() {
		String oldpwd = getParameter("oldpwd");
		user.setPassword(new MD5Code().getMD5ofStr(oldpwd));
		try {
			System.out.println(user.getUserid() + " : " + oldpwd);
			if (userService.login(user) != null) {
				if (userService.updatePwd(user.getUserid(), new MD5Code().getMD5ofStr(getParameter("newpwd")))) {
					getSession().removeAttribute("user");
					setMsgAndUrl("password.update.success", "user.index.action");
				} else {
					setMsgAndUrl("password.update.failure", "user.index.action");
				}
			} else {
				setMsgAndUrl("oldpwd.error", "user.updatePwd.page");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "forward.page";
	}

	public String insertMsgPre() {
		try {
			getRequest().setAttribute("allTheme", messageService.insertPre());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "msg.insert.page";
	}

	public String insertMsg() {
		User user = new User();
		user.setUserid(((User) getSession().getAttribute("user")).getUserid());
		message.setUser(user);
		message.setMsgip(IPUtil.getIP(getRequest()));
		try {
			if (messageService.insert(message)) {
				setMsgAndUrl("msg.insert.success", "user.index.action");
			} else {
				setMsgAndUrl("msg.insert.failure", "msg.insertPre.action");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "forward.page";
	}

	public void reply(){
		int msgid = Integer.parseInt(getParameter("msgid"));
		String replycontent = getParameter("replycontent");
		Reply vo = new Reply();
		Message message = new Message();
		message.setMsgid(msgid);
		User user = new User();
		user.setUserid(((User)getSession().getAttribute("user")).getUserid());
		vo.setMessage(message);
		vo.setUser(user);
		vo.setReplycontents(replycontent);
		vo.setReplyip(IPUtil.getIP(getRequest()));
		try {
			Object pk = null;
			if((pk = replyService.add(vo)) != null){
				List<Reply> list = new ArrayList<>();
				list.add(replyService.get((Integer)pk));
				JSONArray res = replyToJson(list);
				print(res.get(0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String mymsg() {
		int userid = ((User) getSession().getAttribute("user")).getUserid();
		List<Message> list = null;
		try {
			Page<Message> page = new Page<>();
			page.setCurPage(1);
			page.setPageNumber(5);
			list = messageService.findByUser(userid,page).getData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (list != null) {
			for (Iterator<Message> iterator = list.iterator(); iterator.hasNext(); ) {
				Message next = iterator.next();
				next.setMsgtopic(HTMLReplace.replace(next.getMsgtopic()));
			}
		}
		getRequest().setAttribute("allMessage",list);
		return "mymsg.page";
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
		return "用户";
	}
}
