package cn.gss.mooc.service.impl;

import cn.gss.mooc.dao.AdminDao;
import cn.gss.mooc.pojo.Admin;
import cn.gss.mooc.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDao adminDao;

	@Override
	public Admin login(Admin admin) throws SQLException {
		return adminDao.login(admin);
	}

	@Override
	public boolean updatePwd(int aid, String newpwd) throws SQLException {
		return adminDao.updatePwd(aid,newpwd);
	}
}
