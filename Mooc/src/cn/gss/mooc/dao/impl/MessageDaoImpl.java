package cn.gss.mooc.dao.impl;

import cn.gss.mooc.dao.MessageDao;
import cn.gss.mooc.pojo.Message;
import cn.gss.util.Page;
import cn.gss.util.dao.AbstractDAOImpl;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Queue;
import java.util.Set;

@Component
public class MessageDaoImpl extends AbstractDAOImpl implements MessageDao {
	@Override
	public boolean insert(Message vo) throws SQLException {
		vo.setMsgtime(new Timestamp(System.currentTimeMillis()));
		vo.setState(1);
		return getSession().save(vo) != null;
	}

	@Override
	public boolean update(Message vo) throws SQLException {
		return false;
	}

	@Override
	public boolean deleteBatch(Set<Integer> ids) throws SQLException {
		return false;
	}

	@Override
	public Message get(Integer id) throws SQLException {
		return getSession().get(Message.class, id);
	}

	@Override
	public List<Message> list() throws SQLException {
		return null;
	}

	@Override
	public List<Message> listSplit(Integer cp, Integer ls, String col, String kw) throws SQLException {
		return listSplit(Message.class, cp, ls, col, kw);
	}

	@Override
	public Integer getAllCount(String col, String kw) throws SQLException {
		return getAllCount("Message", col, kw);
	}

	@Override
	public boolean access(int msgid) throws SQLException {
		String hql = "UPDATE Count c SET c.accesscount=c.accesscount+1 WHERE c.message.msgid=?";
		return getQuery(hql,msgid).executeUpdate() == 1;
	}

	@Override
	public boolean changeState(int id, int state) throws SQLException {
		return changeState("Message", "msgid", id, state);
	}

	@Override
	public Page<Message> findNewMessage(Page<Message> page) throws SQLException {
		String hql = "From Message m WHERE state=1 ORDER BY m.msgtime DESC";
		Query<Message> query = getQuery(hql, Message.class);
		query.setFirstResult((page.getCurPage() - 1) * page.getPageNumber());
		query.setMaxResults(page.getPageNumber());
		page.setData(query.list());
		return page;
	}

	@Override
	public Page<Message> findHotMessage(Page<Message> page) throws SQLException {
		String hql = "SELECT m From Message m , Count c WHERE m.msgid=c.msgid AND state=1 ORDER BY c.replycount DESC";
		Query<Message> query = getQuery(hql, Message.class);
		query.setFirstResult((page.getCurPage() - 1) * page.getPageNumber());
		query.setMaxResults(page.getPageNumber());
		page.setData(query.list());
		return page;
	}

	@Override
	public Page<Message> findMessageByTheme(Page<Message> page) throws SQLException {
		String hql = "FROM Message m WHERE m.msgtime IN (" +
				"SELECT MAX(m1.msgtime) FROM Message m1 GROUP BY m1.theme.id) AND state=1 " +
				"ORDER BY m.msgtime DESC";
		Query<Message> query = getQuery(hql, Message.class);
		query.setFirstResult((page.getCurPage() - 1) * page.getPageNumber());
		query.setMaxResults(page.getPageNumber());
		page.setData(query.list());
		return page;
	}

	@Override
	public Page<Message> findByUser(int userid, Page<Message> page) throws SQLException {
		String hql = "FROM Message m WHERE m.user.userid=? AND state=1";
		Query<Message> query = getQuery(hql, Message.class, userid);
		query.setFirstResult((page.getCurPage() - 1) * page.getPageNumber());
		query.setMaxResults(page.getPageNumber());
		page.setData(query.list());
		return page;
	}

	@Override
	public int findCountByDate(String type) throws SQLException {
		String sql = "SELECT COUNT(*) FROM Message m WHERE date_add(m.msgtime, interval 1 " + type + ") >= now() AND state=1";
		return ((BigInteger) getSQLQuery(sql).uniqueResult()).intValue();
	}

	@Override
	public Page<Message> findByAdmin(String username, int theid,Page<Message> page) throws SQLException {
		String hql = "FROM Message m WHERE (m.msgtopic LIKE ? OR m.msgcontents LIKE ?) AND m.user.userid IN " +
				"(SELECT u.userid FROM User u WHERE u.username LIKE ?)";
		if (theid != -1) {
			hql += "AND m.theme.theid=" + theid;
		}
		try {
			Query<Message> query = getQuery(hql,Message.class,"%"+page.getKeyword()+"%","%"+page.getKeyword()+"%","%"+username+"%");
			query.setFirstResult((page.getCurPage() - 1) * page.getPageNumber());
			query.setMaxResults(page.getPageNumber());
			List<Message> list = query.list();
			page.setData(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	@Override
	public int getAllCountByAdmin(String username, int theid, Page<Message> page) throws SQLException {
		String hql = "SELECT COUNT(*) FROM Message m WHERE (m.msgtopic LIKE ? OR m.msgcontents LIKE ?) AND m.user.userid IN " +
				"(SELECT u.userid FROM User u WHERE u.username LIKE ?)";
		if (theid != -1) {
			hql += "AND m.theme.theid=" + theid;
		}
		int count = 0;
		try {
			Query<Long> query = getQuery(hql,Long.class,"%"+page.getKeyword()+"%","%"+page.getKeyword()+"%","%"+username+"%");
			count = query.uniqueResult().intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
}
