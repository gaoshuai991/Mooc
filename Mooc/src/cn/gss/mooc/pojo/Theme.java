package cn.gss.mooc.pojo;

import com.google.gson.annotations.Expose;
import com.opensymphony.xwork2.inject.Scope;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Theme implements Serializable {
	@Expose
	private int theid;
	@Expose
	private String thename;
	@Expose
	private Integer count;
	private List<Message> message;

	public Theme() {
	}

	public Theme(String thename,Integer count) {
		this.thename = thename;
		this.count = count;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "theid")
	public int getTheid() {
		return theid;
	}

	public void setTheid(int theid) {
		this.theid = theid;
	}

	@Basic
	@Column(name = "thename")
	public String getThename() {
		return thename;
	}

	public void setThename(String thename) {
		this.thename = thename;
	}

	@Basic
	@Column(name = "count")
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Theme theme = (Theme) o;

		if (theid != theme.theid) return false;
		if (thename != null ? !thename.equals(theme.thename) : theme.thename != null) return false;
		if (count != null ? !count.equals(theme.count) : theme.count != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = theid;
		result = 31 * result + (thename != null ? thename.hashCode() : 0);
		result = 31 * result + (count != null ? count.hashCode() : 0);
		return result;
	}

	@OneToMany(mappedBy = "theme")
	public List<Message> getMessage() {
		return message;
	}

	public void setMessage(List<Message> message) {
		this.message = message;
	}
}
