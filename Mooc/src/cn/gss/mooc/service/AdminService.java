package cn.gss.mooc.service;

import cn.gss.mooc.pojo.Admin;

import java.sql.SQLException;

public interface AdminService {
	Admin login(Admin admin) throws SQLException;

	boolean updatePwd(int aid,String newpwd) throws SQLException;
}
