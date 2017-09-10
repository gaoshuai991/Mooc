package cn.gss.mooc.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Count implements Serializable {
	private int msgid;
	private Integer accesscount;
	private Integer replycount;
	private Message message;

	@Id
	@Column(name = "msgid")
	public int getMsgid() {
		return msgid;
	}

	public void setMsgid(int msgid) {
		this.msgid = msgid;
	}

	@Basic
	@Column(name = "accesscount")
	public Integer getAccesscount() {
		return accesscount;
	}

	public void setAccesscount(Integer accesscount) {
		this.accesscount = accesscount;
	}

	@Basic
	@Column(name = "replycount")
	public Integer getReplycount() {
		return replycount;
	}

	public void setReplycount(Integer replycount) {
		this.replycount = replycount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Count count = (Count) o;

		if (msgid != count.msgid) return false;
		if (accesscount != null ? !accesscount.equals(count.accesscount) : count.accesscount != null) return false;
		if (replycount != null ? !replycount.equals(count.replycount) : count.replycount != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = msgid;
		result = 31 * result + (accesscount != null ? accesscount.hashCode() : 0);
		result = 31 * result + (replycount != null ? replycount.hashCode() : 0);
		return result;
	}

	@OneToOne
	@JoinColumn(name = "msgid", referencedColumnName = "msgid", nullable = false)
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
}
