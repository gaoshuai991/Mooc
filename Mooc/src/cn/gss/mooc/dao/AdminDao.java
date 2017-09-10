package cn.gss.mooc.dao;

import cn.gss.mooc.pojo.Admin;

import java.sql.SQLException;

public interface AdminDao {
	Admin login(Admin admin) throws SQLException;

	boolean updatePwd(int aid, String newpwd) throws SQLException;
}
