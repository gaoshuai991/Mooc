package cn.gss.mooc.service;

import cn.gss.mooc.pojo.User;
import cn.gss.util.Page;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface UserService {
	User get(int userid) throws SQLException;

	boolean register(User user) throws SQLException;

	boolean updatePwd(int userid, String newpwd) throws SQLException;

	User login(User user) throws SQLException;

	boolean update(User user) throws SQLException;

	boolean lock(int id) throws SQLException;

	boolean active(int id) throws SQLException;

	List<User> list() throws SQLException;

	Page<User> listSplit(int cp,int ls,String col,String kw);

	boolean changeState(int id,int state);
}
