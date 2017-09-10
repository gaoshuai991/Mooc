package cn.gss.mooc.service.impl;

import cn.gss.mooc.dao.ReplyDao;
import cn.gss.mooc.pojo.Reply;
import cn.gss.mooc.service.ReplyService;
import cn.gss.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.sql.SQLException;
@Service
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	private ReplyDao replyDao;

	@Override
	public Reply get(int replyid) throws SQLException {
		return replyDao.get(replyid);
	}

	@Override
	public boolean insert(Reply vo) throws SQLException {
		return replyDao.insert(vo);
	}

	@Override
	public Serializable add(Reply vo) throws SQLException {
		return replyDao.add(vo);
	}

	@Override
	public Page<Reply> findByMsg(int msgid, Page<Reply> page) throws SQLException {
		return replyDao.findByMsg(msgid,page);
	}

	@Override
	public int findCountByDate(String type) throws SQLException {
		return replyDao.findCountByDate(type);
	}
}
