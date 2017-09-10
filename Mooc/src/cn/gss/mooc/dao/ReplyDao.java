package cn.gss.mooc.dao;

import cn.gss.mooc.pojo.Reply;
import cn.gss.util.Page;
import cn.gss.util.dao.IDAO;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface ReplyDao extends IDAO<Integer,Reply> {
	Serializable add(Reply vo) throws SQLException;

	Page<Reply> findByMsg(int msgid, Page<Reply> page) throws SQLException;

	int findCountByDate(String type) throws SQLException;
}
