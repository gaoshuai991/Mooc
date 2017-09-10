package cn.gss.mooc.dao.impl;

import cn.gss.mooc.dao.ThemeDao;
import cn.gss.mooc.pojo.Theme;
import cn.gss.util.dao.AbstractDAOImpl;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
@Component
public class ThemeDaoImpl extends AbstractDAOImpl implements ThemeDao {
	@Override
	public boolean insert(Theme vo) throws SQLException {
		return getSession().save(vo) != null;
	}

	@Override
	public boolean update(Theme vo) throws SQLException {
		return false;
	}

	@Override
	public boolean deleteBatch(Set<Integer> ids) throws SQLException {
		return handleDeleteBatch("Theme","theid",ids);
	}

	@Override
	public Theme get(Integer id) throws SQLException {
		return null;
	}

	@Override
	public List<Theme> list() throws SQLException {
		return list(Theme.class);
	}

	@Override
	public List<Theme> listSplit(Integer cp, Integer ls, String col, String kw) throws SQLException {
		return listSplit(Theme.class,cp,ls,col,kw);
	}

	@Override
	public Integer getAllCount(String col, String kw) throws SQLException {
		return getAllCount("Theme",col,kw);
	}

	@Override
	public boolean delete(int theid) throws SQLException {
		String hql = "DELETE FROM Theme t WHERE t.theid=?";
		return getQuery(hql,theid).executeUpdate() == 1;
	}

	@Override
	public Serializable add(Theme vo) throws SQLException {
		return getSession().save(vo);
	}
}
