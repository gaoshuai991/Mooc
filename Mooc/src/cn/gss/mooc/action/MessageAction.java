package cn.gss.mooc.action;

import cn.gss.mooc.pojo.Message;
import cn.gss.mooc.pojo.Reply;
import cn.gss.mooc.pojo.User;
import cn.gss.mooc.service.MessageService;
import cn.gss.mooc.service.ReplyService;
import cn.gss.util.HTMLReplace;
import cn.gss.util.IPUtil;
import cn.gss.util.Page;
import cn.gss.util.action.AbstractAction;
import cn.gss.util.dao.IDAO;
import com.google.gson.Gson;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

@Repository
@ParentPackage("root")
@Namespace("/user")
@Action("message")
@InterceptorRef("validateStack")
@Results({@Result(name = "user.index.page",location = "/index.jsp"),
		@Result(name = "hotmsg.page",location = "/hotmsg.jsp"),
		@Result(name = "newmsg.page",location = "/newmsg.jsp"),
		@Result(name = "thememsg.page",location = "/thememsg.jsp"),
		@Result(name = "msg.show.page",location = "/message.jsp")})
public class MessageAction extends AbstractAction {
	private final String insertRule="message.msgtopic:string|message.msgcontents:string";
	@Autowired
	private MessageService messageService;
	@Autowired
	private ReplyService replyService;

	private Message message = new Message();

	public Message getMessage() {
		return message;
	}

	public String index(){
		try {
			Page<Message> page = new Page<>();
			page.setCurPage(1);
			page.setPageNumber(5);
			getRequest().setAttribute("allHotMessage",messageService.findHotMessage(page).getData());
			getRequest().setAttribute("allNewMessage",messageService.findNewMessage(page).getData());
			getRequest().setAttribute("allMessageByTheme",messageService.findMessageByTheme(page).getData());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "user.index.page";
	}

	public void appendReply(){
		int msgid = Integer.parseInt(getParameter("msgid"));
		try {
			List<Reply> list = replyService.findByMsg(msgid, getPage()).getData();
			print(replyToJson(list));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void appendHotmsg(){
		try {
			print(messageToJson(messageService.findHotMessage(getPage()).getData()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void appendNewmsg(){
		try {
			print(messageToJson(messageService.findNewMessage(getPage()).getData()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void appendThememsg(){
		try {
			print(messageToJson(messageService.findMessageByTheme(getPage()).getData()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	public String msg_show(){
		int msgid = Integer.parseInt(getParameter("msgid"));
		try {

			Message vo = messageService.get(msgid);
			vo.setMsgtopic(HTMLReplace.replace(vo.getMsgtopic()));
			vo.setMsgcontents(HTMLReplace.replace(vo.getMsgcontents()));
			for (Iterator<Reply> iterator = vo.getReply().iterator(); iterator.hasNext(); ) {
				Reply next = iterator.next();
				next.setReplycontents(HTMLReplace.replace(next.getReplycontents()));
			}
			getRequest().setAttribute("msg",vo);
			messageService.access(msgid); // 访问量加一
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "msg.show.page";
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
		return "问题";
	}
}
