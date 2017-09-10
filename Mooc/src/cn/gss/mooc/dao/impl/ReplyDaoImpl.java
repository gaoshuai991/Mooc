package cn.gss.mooc.dao.impl;

import cn.gss.mooc.dao.ReplyDao;
import cn.gss.mooc.pojo.Reply;
import cn.gss.util.Page;
import cn.gss.util.dao.AbstractDAOImpl;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
@Component
public class ReplyDaoImpl extends AbstractDAOImpl implements ReplyDao {
	@Override
	public Serializable add(Reply vo) throws SQLException {
		vo.setReplytime(new Timestamp(System.currentTimeMillis()));
		return getSession().save(vo);
	}

	@Override
	public Page<Reply> findByMsg(int msgid, Page<Reply> page) throws SQLException {
		String hql = "FROM Reply r WHERE r.message.msgid=? ORDER BY r.replytime ASC";
		Query<Reply> query = getQuery(hql,Reply.class,msgid);
		query.setFirstResult((page.getCurPage() - 1) * page.getPageNumber());
		query.setMaxResults(page.getPageNumber());
		page.setData(query.list());
		return page;
	}

	@Override
	public int findCountByDate(String type) throws SQLException {
		String sql = "SELECT COUNT(*) FROM reply WHERE date_add(replytime,interval 1 " + type + ") >= now()";
		return ((BigInteger)getSQLQuery(sql).uniqueResult()).intValue();
	}

	@Override
	public boolean insert(Reply vo) throws SQLException {
		vo.setReplytime(new Timestamp(System.currentTimeMillis()));
		return getSession().save(vo) != null;
	}

	@Override
	public boolean update(Reply vo) throws SQLException {
		return false;
	}

	@Override
	public boolean deleteBatch(Set<Integer> ids) throws SQLException {
		return false;
	}

	@Override
	public Reply get(Integer id) throws SQLException {
		return getSession().get(Reply.class,id);
	}

	@Override
	public List<Reply> list() throws SQLException {
		return null;
	}

	@Override
	public List<Reply> listSplit(Integer cp, Integer ls, String col, String kw) throws SQLException {
		return null;
	}

	@Override
	public Integer getAllCount(String col, String kw) throws SQLException {
		return null;
	}
}
