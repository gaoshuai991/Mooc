package cn.gss.mooc.dao;

import cn.gss.mooc.pojo.Message;
import cn.gss.util.Page;
import cn.gss.util.dao.IDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface MessageDao extends IDAO<Integer, Message> {
	boolean access(int msgid) throws SQLException;

	boolean changeState(int id, int state) throws SQLException;

	Page<Message> findNewMessage(Page<Message> page) throws SQLException;

	Page<Message> findHotMessage(Page<Message> page) throws SQLException;

	Page<Message> findMessageByTheme(Page<Message> page) throws SQLException;

	Page<Message> findByUser(int userid,Page<Message> page) throws SQLException;

	int findCountByDate(String type) throws SQLException;

	Page<Message> findByAdmin(String username, int theid,Page<Message> page) throws SQLException;

	int getAllCountByAdmin(String username, int theid,Page<Message> page) throws SQLException;
}
