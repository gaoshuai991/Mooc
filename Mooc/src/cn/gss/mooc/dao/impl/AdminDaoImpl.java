package cn.gss.mooc.dao.impl;

import cn.gss.mooc.dao.AdminDao;
import cn.gss.mooc.pojo.Admin;
import cn.gss.util.dao.AbstractDAOImpl;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
@Component
public class AdminDaoImpl extends AbstractDAOImpl implements AdminDao {
	@Override
	public Admin login(Admin admin) throws SQLException {
		String hql = "From Admin AS a WHERE a.username=? AND a.password=?";
		return (Admin)getQuery(hql,admin.getUsername(),admin.getPassword()).uniqueResult();
	}

	@Override
	public boolean updatePwd(int aid, String newpwd) throws SQLException {
		String hql = "UPDATE Admin AS a SET a.password=? WHERE a.aid=?";
		int a = getQuery(hql,newpwd,aid).executeUpdate();
		System.out.println("------------:"+a);
		return a == 1;
	}
}
