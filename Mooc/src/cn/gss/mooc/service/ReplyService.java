package cn.gss.mooc.service;

import cn.gss.mooc.pojo.Reply;
import cn.gss.util.Page;

import java.io.Serializable;
import java.sql.SQLException;

public interface ReplyService {
	Reply get(int replyid) throws SQLException;

	boolean insert(Reply vo) throws SQLException;
	Serializable add(Reply vo) throws SQLException;

	Page<Reply> findByMsg(int msgid, Page<Reply> page) throws SQLException;

	int findCountByDate(String type) throws SQLException;
}
