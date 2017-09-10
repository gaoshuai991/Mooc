package cn.gss.mooc.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Message implements Serializable {
	private int msgid;
	private String msgtopic;
	private String msgcontents;
	private Timestamp msgtime;
	private String msgip;
	private Integer state;
	private Count count;
	private Theme theme;
	private User user;
	private List<Reply> reply;

	@Id
	@Column(name = "msgid")
	public int getMsgid() {
		return msgid;
	}

	public void setMsgid(int msgid) {
		this.msgid = msgid;
	}

	@Basic
	@Column(name = "msgtopic")
	public String getMsgtopic() {
		return msgtopic;
	}

	public void setMsgtopic(String msgtopic) {
		this.msgtopic = msgtopic;
	}

	@Basic
	@Column(name = "msgcontents")
	public String getMsgcontents() {
		return msgcontents;
	}

	public void setMsgcontents(String msgcontents) {
		this.msgcontents = msgcontents;
	}

	@Basic
	@Column(name = "msgtime")
	public Timestamp getMsgtime() {
		return msgtime;
	}

	public void setMsgtime(Timestamp msgtime) {
		this.msgtime = msgtime;
	}

	@Basic
	@Column(name = "msgip")
	public String getMsgip() {
		return msgip;
	}

	public void setMsgip(String msgip) {
		this.msgip = msgip;
	}

	@Basic
	@Column(name = "state")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Message message = (Message) o;

		if (msgid != message.msgid) return false;
		if (msgtopic != null ? !msgtopic.equals(message.msgtopic) : message.msgtopic != null) return false;
		if (msgcontents != null ? !msgcontents.equals(message.msgcontents) : message.msgcontents != null) return false;
		if (msgtime != null ? !msgtime.equals(message.msgtime) : message.msgtime != null) return false;
		if (msgip != null ? !msgip.equals(message.msgip) : message.msgip != null) return false;
		if (state != null ? !state.equals(message.state) : message.state != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = msgid;
		result = 31 * result + (msgtopic != null ? msgtopic.hashCode() : 0);
		result = 31 * result + (msgcontents != null ? msgcontents.hashCode() : 0);
		result = 31 * result + (msgtime != null ? msgtime.hashCode() : 0);
		result = 31 * result + (msgip != null ? msgip.hashCode() : 0);
		result = 31 * result + (state != null ? state.hashCode() : 0);
		return result;
	}

	@OneToOne(mappedBy = "message")
	public Count getCount() {
		return count;
	}

	public void setCount(Count count) {
		this.count = count;
	}

	@ManyToOne
	@JoinColumn(name = "theid", referencedColumnName = "theid")
	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	@ManyToOne
	@JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@OneToMany(mappedBy = "message")
	public List<Reply> getReply() {
		return reply;
	}

	public void setReply(List<Reply> reply) {
		this.reply = reply;
	}

	@Override
	public String toString() {
		return "Message{" +
				"msgtopic='" + msgtopic + '\'' +
				", msgtime=" + msgtime +
				"theid="+theme.getTheid()+",thename="+theme.getThename()+"}";
	}
}
