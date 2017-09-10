package cn.gss.mooc.dao;

import cn.gss.mooc.pojo.Message;
import cn.gss.mooc.pojo.User;
import cn.gss.util.Page;
import cn.gss.util.dao.IDAO;

import java.sql.SQLException;
import java.util.Set;

public interface UserDao extends IDAO<Integer,User> {
	User login(User user) throws SQLException;

	boolean updatePwd(int userid,String newpwd) throws SQLException;

	boolean changeState(int id,int state) throws SQLException;

	Page<User> findByAdmin(Page<User> page) throws SQLException;

	int getAllCountByAdmin(Page<Message> page) throws SQLException;
}
