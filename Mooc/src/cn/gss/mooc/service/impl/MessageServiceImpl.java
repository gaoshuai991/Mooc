package cn.gss.mooc.service.impl;

import cn.gss.mooc.dao.MessageDao;
import cn.gss.mooc.dao.ThemeDao;
import cn.gss.mooc.pojo.Message;
import cn.gss.mooc.pojo.Theme;
import cn.gss.mooc.service.MessageService;
import cn.gss.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	private MessageDao messageDao;
	@Autowired
	private ThemeDao themeDao;

	@Override
	public boolean access(int msgid) {
		try {
			return messageDao.access(msgid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean insert(Message vo) throws SQLException {
		return messageDao.insert(vo);
	}

	@Override
	public Message get(int msgid) throws SQLException {
		Message vo = messageDao.get(msgid);
		vo.getReply().size();
		return vo;
	}

	@Override
	public List<Theme> insertPre() throws SQLException {
		return themeDao.list();
	}

	@Override
	public Page<Message> findHotMessage(Page<Message> page) throws SQLException {
		return messageDao.findHotMessage(page);
	}

	@Override
	public Page<Message> findNewMessage(Page<Message> page) throws SQLException {
		return messageDao.findNewMessage(page);
	}

	@Override
	public Page<Message> findMessageByTheme(Page<Message> page) throws SQLException {
		return messageDao.findMessageByTheme(page);
	}

	@Override
	public Page<Message> findByUser(int userid, Page<Message> page) throws SQLException {
		return messageDao.findByUser(userid, page);
	}

	@Override
	public int findCountByDate(String type) throws SQLException {
		return messageDao.findCountByDate(type);
	}

	@Override
	public Page<Message> list(Page<Message> page) throws SQLException {
		List<Message> data = messageDao.listSplit(page.getCurPage(), page.getPageNumber(), page.getColumn(), page.getKeyword());
		int count = messageDao.getAllCount(page.getColumn(), page.getKeyword());
		page.setData(data);
		page.setRows(count);
		page.setTotalPage((count + page.getPageNumber() - 1) / page.getPageNumber());
		return page;
	}

	@Override
	public Page<Message> findByAdmin(String username, int theid, Page<Message> page) throws SQLException {
		Page<Message> res = messageDao.findByAdmin(username,theid,page);
		int count = messageDao.getAllCountByAdmin(username, theid, page);
		res.setRows(count);
		res.setTotalPage((count + res.getPageNumber() - 1) / res.getPageNumber());
		return res;
	}

	@Override
	public boolean changeState(int id,int state) {
		try {
			return messageDao.changeState(id,state);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
