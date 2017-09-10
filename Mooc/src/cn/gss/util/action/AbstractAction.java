package cn.gss.util.action;

import cn.gss.mooc.pojo.Message;
import cn.gss.mooc.pojo.Reply;
import cn.gss.util.HTMLReplace;
import cn.gss.util.Page;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Created by Gss on 2017/3/30.
 */
public abstract class AbstractAction extends ActionSupport {
	//定义分页有关的操作
	private int cp = 1;
	private int ls = 5;
	private String col;
	private String kw;

	public void setCol(String col) {
		this.col = col;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public void setKw(String kw) {
		this.kw = kw;
	}

	public void setLs(int ls) {
		this.ls = ls;
	}

	public int getCp() {
		return cp;
	}

	public int getLs() {
		return ls;
	}

	public String getCol() {
		if (this.col == null || this.col.equals("")) {
			this.col = getDefaultColumn();
		}
		return this.col;
	}

	public String getKw() {
		if(this.kw == null)
			this.kw = "";
		return kw;
	}

	public JSONArray replyToJson(List<Reply> list){
		JSONArray res = new JSONArray();
		for (Reply r : list) {
			JSONObject obj = new JSONObject();
			obj.put("replyid", r.getReplyid());
			obj.put("replycontents", HTMLReplace.replace(r.getReplycontents()));
			obj.put("replytime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(r.getReplytime()));
			obj.put("replyip", r.getReplyip());
			JSONObject user = new JSONObject();
			user.put("userid", r.getUser().getUserid());
			user.put("username", r.getUser().getUsername());
			user.put("realname", r.getUser().getRealname());
			user.put("sex", r.getUser().getSex());
			user.put("city", r.getUser().getCity());
			obj.put("user", user);
			res.add(obj);
		}
		return res;
	}
	public JSONArray messageToJson(List<Message> list){
		for (Iterator<Message> iterator = list.iterator(); iterator.hasNext(); ) {
			Message next = iterator.next();
			next.setMsgtopic(HTMLReplace.replace(next.getMsgtopic()));
		}
		JSONArray res = new JSONArray();
		for (Message m : list) {
			JSONObject obj = new JSONObject();
			obj.put("msgid", m.getMsgid());
			JSONObject user = new JSONObject();
			user.put("userid", m.getUser().getUserid());
			user.put("username", m.getUser().getUsername());
			user.put("realname", m.getUser().getRealname());
			obj.put("user", user);
			obj.put("msgtopic", HTMLReplace.replace(m.getMsgtopic()));
			obj.put("msgcontents",HTMLReplace.replace(m.getMsgcontents()));
			obj.put("msgtime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(m.getMsgtime()));
			JSONObject count = new JSONObject();
			count.put("accesscount",m.getCount().getAccesscount());
			count.put("replycount",m.getCount().getReplycount());
			obj.put("count", count);
			JSONObject theme = new JSONObject();
			theme.put("thename",m.getTheme().getThename());
			obj.put("theme", theme);
			res.add(obj);
		}
		return res;
	}
	public <T> Page<T> getPage(){
		int curPage = Integer.parseInt(getParameter("curPage"));
		int pageNumber = Integer.parseInt(getParameter("pageNumber"));

		Page<T> page = new Page<>();
		page.setCurPage(curPage);
		page.setPageNumber(pageNumber);

		return page;
	}

	/**
	 * 定义默认查询列
	 * @return
	 */
	public abstract String getDefaultColumn();

	/**
	 * 获取列数据
	 * @return
	 */
	public abstract String getColumnData();

	public void handleSplitRequestParams(Object allRecorders, String urlKey, String paramName, String paramValue) {
		this.getRequest().setAttribute("currentPage",this.getCp());
		this.getRequest().setAttribute("lineSize",this.getLs());
		this.getRequest().setAttribute("column",this.getCol());
		this.getRequest().setAttribute("keyword",this.getKw());
		this.getRequest().setAttribute("allRecorders",allRecorders);
		this.getRequest().setAttribute("columnData",this.getColumnData());
		this.getRequest().setAttribute("url",getForwardUrl(urlKey));
		this.getRequest().setAttribute("paramName",paramName);
		this.getRequest().setAttribute("paramValue",paramValue);
	}

	/**
	 * 从资源文件读取要在forward.jsp跳转的url
	 *
	 * @param key
	 * @return
	 */
	public String getForwardUrl(String key) {
		return super.getText(key);
	}

	/**
	 * 从资源文件中读取提示信息
	 *
	 * @param key
	 * @return
	 */
	public String getMessage(String key) {
		return super.getText(key, new String[]{getSubType()});
	}

	/**
	 * 设置每个Action执行完之后要传递给forward.jsp的信息
	 * 所有的信息都要通过资源文件读取
	 *
	 * @param msgKey
	 * @param urlKey
	 */
	public void setMsgAndUrl(String msgKey, String urlKey) {
		this.getRequest().setAttribute("msg", getMessage(msgKey));
		this.getRequest().setAttribute("url", getMessage(urlKey));
	}

	/**
	 * 获取子类类型参数，用于传入资源文件，由子类实现
	 *
	 * @return
	 */
	protected abstract String getSubType();

	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	public HttpSession getSession() {
		return getRequest().getSession();
	}

	public ServletContext getApplication() {
		return ServletActionContext.getServletContext();
	}

	public String getParameter(String s){
		return getRequest().getParameter(s);
	}

	public void print(Object msg) {
		try {
			this.getResponse().getWriter().print(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void printJSON(Object msg) {
		try {
			JSONObject obj = new JSONObject();
			obj.put("data", msg);
			this.getResponse().getWriter().print(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String createSingleFileName(String contentType) {
		if (contentType.contains("image")) {
			return UUID.randomUUID().toString() + "." + contentType.substring(contentType.indexOf("/") + 1);
		}
		return null;
	}

	public boolean saveFile(String filePath, File file) {
		File saveFile = new File(filePath);
		BufferedInputStream inputStream = null;
		BufferedOutputStream outputStream = null;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(file));
			outputStream = new BufferedOutputStream(new FileOutputStream(saveFile));
			byte[] data = new byte[1024];
			int len = 0;
			while ((len = inputStream.read(data)) != -1) {
				outputStream.write(data, 0, len);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
}
