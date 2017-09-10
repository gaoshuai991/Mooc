package cn.gss.mooc.pojo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Admin implements Serializable{
	private int aid;
	private String username;
	private String password;
	private Integer authority;

	@Id
	@Column(name = "aid")
	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
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
	@Column(name = "authority")
	public Integer getAuthority() {
		return authority;
	}

	public void setAuthority(Integer authority) {
		this.authority = authority;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Admin admin = (Admin) o;

		if (aid != admin.aid) return false;
		if (username != null ? !username.equals(admin.username) : admin.username != null) return false;
		if (password != null ? !password.equals(admin.password) : admin.password != null) return false;
		if (authority != null ? !authority.equals(admin.authority) : admin.authority != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = aid;
		result = 31 * result + (username != null ? username.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (authority != null ? authority.hashCode() : 0);
		return result;
	}
}
