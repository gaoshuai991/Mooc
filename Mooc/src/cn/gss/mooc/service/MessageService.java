package cn.gss.mooc.service;

import cn.gss.mooc.pojo.Message;
import cn.gss.mooc.pojo.Theme;
import cn.gss.util.Page;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface MessageService {
	boolean access(int msgid) ;

	boolean insert(Message vo) throws SQLException;

	Message get(int msgid) throws SQLException;

	List<Theme> insertPre() throws SQLException;

	Page<Message> findHotMessage(Page<Message> page) throws SQLException;

	Page<Message> findNewMessage(Page<Message> page) throws SQLException;

	Page<Message> findMessageByTheme(Page<Message> page) throws SQLException;

	Page<Message> findByUser(int userid,Page<Message> page) throws SQLException;

	int findCountByDate(String type) throws SQLException;

	Page<Message> list(Page<Message> page) throws SQLException;

	Page<Message> findByAdmin(String username, int theid,Page<Message> page) throws SQLException;

	boolean changeState(int id,int state);

}
