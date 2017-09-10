package cn.gss.util.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

/**
 * Created by Gss on 2017/4/1.
 */
@ParentPackage("root")
@Namespace("/")
@Action("CheckCode")
public class RandCodeCheckAction extends AbstractAction {
	private String code;

	public void setCode(String code) {
		this.code = code;
	}

	public void check() {
		String rand = (String) getSession().getAttribute("rand");
		System.out.println(rand.equalsIgnoreCase(this.code));
		super.print(rand.equalsIgnoreCase(this.code));
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
		return null;
	}
}
