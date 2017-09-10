package cn.gss.mooc.pojo;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "user",catalog = "mooc")
public class User implements Serializable {
	@Expose
	private int userid;
	@Expose
	private String username;
	private String password;
	@Expose
	private String realname;
	@Expose
	private String sex;
	@Expose
	private String hobbys;
	@Expose
	private Timestamp birthday;
	@Expose
	private String city;
	@Expose
	private String email;
	@Expose
	private String qq;
	@Expose
	private Timestamp createtime;
	@Expose
	private Integer state;
	private List<Message> message;
	private List<Reply> reply;

	@Id
	@Column(name = "userid")
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	@Basic
	@Column(name = "username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Basic
	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Basic
	@Column(name = "realname")
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	@Basic
	@Column(name = "sex")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Basic
	@Column(name = "hobbys")
	public String getHobbys() {
		return hobbys;
	}

	public void setHobbys(String hobbys) {
		this.hobbys = hobbys;
	}

	@Basic
	@Column(name = "birthday")
	public Timestamp getBirthday() {
		return birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	@Basic
	@Column(name = "city")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Basic
	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Basic
	@Column(name = "qq")
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Basic
	@Column(name = "createtime")
	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
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

		User user = (User) o;

		if (userid != user.userid) return false;
		if (username != null ? !username.equals(user.username) : user.username != null) return false;
		if (password != null ? !password.equals(user.password) : user.password != null) return false;
		if (realname != null ? !realname.equals(user.realname) : user.realname != null) return false;
		if (sex != null ? !sex.equals(user.sex) : user.sex != null) return false;
		if (hobbys != null ? !hobbys.equals(user.hobbys) : user.hobbys != null) return false;
		if (birthday != null ? !birthday.equals(user.birthday) : user.birthday != null) return false;
		if (city != null ? !city.equals(user.city) : user.city != null) return false;
		if (email != null ? !email.equals(user.email) : user.email != null) return false;
		if (qq != null ? !qq.equals(user.qq) : user.qq != null) return false;
		if (createtime != null ? !createtime.equals(user.createtime) : user.createtime != null) return false;
		if (state != null ? !state.equals(user.state) : user.state != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = userid;
		result = 31 * result + (username != null ? username.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (realname != null ? realname.hashCode() : 0);
		result = 31 * result + (sex != null ? sex.hashCode() : 0);
		result = 31 * result + (hobbys != null ? hobbys.hashCode() : 0);
		result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
		result = 31 * result + (city != null ? city.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (qq != null ? qq.hashCode() : 0);
		result = 31 * result + (createtime != null ? createtime.hashCode() : 0);
		result = 31 * result + (state != null ? state.hashCode() : 0);
		return result;
	}

	@OneToMany(mappedBy = "user")
	public List<Message> getMessage() {
		return message;
	}

	public void setMessage(List<Message> message) {
		this.message = message;
	}

	@OneToMany(mappedBy = "user")
	public List<Reply> getReply() {
		return reply;
	}

	@Override
	public String toString() {
		return "User{" +
				"userid=" + userid +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", realname='" + realname + '\'' +
				", sex='" + sex + '\'' +
				", hobbys='" + hobbys + '\'' +
				", birthday=" + birthday +
				'}';
	}

	public void setReply(List<Reply> reply) {
		this.reply = reply;
	}
}
