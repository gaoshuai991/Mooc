package cn.gss.mooc.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class Reply implements Serializable {
	private Integer replyid;
	private String replycontents;
	private Timestamp replytime;
	private String replyip;
	private Message message;
	private User user;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "replyid")
	public Integer getReplyid() {
		return replyid;
	}

	public void setReplyid(Integer replyid) {
		this.replyid = replyid;
	}

	@Basic
	@Column(name = "replycontents")
	public String getReplycontents() {
		return replycontents;
	}

	public void setReplycontents(String replycontents) {
		this.replycontents = replycontents;
	}

	@Basic
	@Column(name = "replytime")
	public Timestamp getReplytime() {
		return replytime;
	}

	public void setReplytime(Timestamp replytime) {
		this.replytime = replytime;
	}

	@Basic
	@Column(name = "replyip")
	public String getReplyip() {
		return replyip;
	}

	public void setReplyip(String replyip) {
		this.replyip = replyip;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Reply reply = (Reply) o;

		if (replyid != reply.replyid) return false;
		if (replycontents != null ? !replycontents.equals(reply.replycontents) : reply.replycontents != null)
			return false;
		if (replytime != null ? !replytime.equals(reply.replytime) : reply.replytime != null) return false;
		if (replyip != null ? !replyip.equals(reply.replyip) : reply.replyip != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = replyid;
		result = 31 * result + (replycontents != null ? replycontents.hashCode() : 0);
		result = 31 * result + (replytime != null ? replytime.hashCode() : 0);
		result = 31 * result + (replyip != null ? replyip.hashCode() : 0);
		return result;
	}

	@ManyToOne
	@JoinColumn(name = "msgid", referencedColumnName = "msgid")
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	@ManyToOne
	@JoinColumn(name = "userid", referencedColumnName = "userid")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Reply{" +
				"replyid=" + replyid +
				", replycontents='" + replycontents + '\'' +
				", message=" + message +
				", user=" + user +
				'}';
	}
}
