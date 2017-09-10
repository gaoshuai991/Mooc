package cn.gss.mooc.service.impl;

import cn.gss.mooc.dao.UserDao;
import cn.gss.mooc.dao.impl.UserDaoImpl;
import cn.gss.mooc.pojo.User;
import cn.gss.mooc.service.UserService;
import cn.gss.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;


	@Override
	public User get(int userid) throws SQLException {
		return userDao.get(userid);
	}

	@Override
	public boolean register(User user) throws SQLException {
		return userDao.insert(user);
	}

	@Override
	public boolean updatePwd(int userid, String newpwd) throws SQLException {
		return userDao.updatePwd(userid, newpwd);
	}

	@Override
	public User login(User user) throws SQLException {
		return userDao.login(user);
	}

	@Override
	public boolean update(User user) throws SQLException {
		return userDao.update(user);
	}

	@Override
	public boolean lock(int id) throws SQLException {
		return userDao.changeState(id,0);
	}

	@Override
	public boolean active(int id) throws SQLException {
		return userDao.changeState(id,1);
	}

	@Override
	public List<User> list() throws SQLException {
		return userDao.list();
	}

	@Override
	public Page<User> listSplit(int cp, int ls, String col, String kw) {
		Page<User> page = null;
		try {
			page = new Page<>();
			page.setData(userDao.listSplit(cp, ls, col,kw));
			page.setRows(userDao.getAllCount(col, kw));
			page.setTotalPage((page.getRows() + ls - 1) / ls);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return page;
	}

	@Override
	public boolean changeState(int id, int state) {
		try {
			return userDao.changeState(id, state);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
