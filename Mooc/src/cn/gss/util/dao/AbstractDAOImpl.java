package cn.gss.util.dao;

import cn.gss.util.Page;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 这个类作为所有DAO子类的公共父类，目的是加强代码的可重用性
 */
public abstract class AbstractDAOImpl {
	@Resource
	private SessionFactory factory;

	/**
	 * 设置SessionFactory类对象，在子类构造方法注入的时候完成调用
	 *
	 * @param factory
	 */
	public void setSessionFactory(SessionFactory factory) {
		this.factory = factory;
	}

	/**
	 * 返回SessionFactory类对象，一般只有在操作二级缓存的时候才调用
	 *
	 * @return
	 */
	public SessionFactory getFactory() {
		return factory;
	}

	public Session getSession() {
		return factory.getCurrentSession();
	}

	/**
	 * 利用hql语句创建Query对象
	 *
	 * @param hql
	 * @return
	 */
	protected Query getQuery(String hql, Object... params) {
		Query query = getSession().createQuery(hql);
		for (int x = 0; x < params.length; x++) {
			query.setParameter(x, params[x]);
		}
		return query;
	}

	protected <T> Query<T> getQuery(String hql, Class<T> cls, Object... params) {
		Query<T> query = getSession().createQuery(hql, cls);
		for (int x = 0; x < params.length; x++) {
			query.setParameter(x, params[x]);
		}
		return query;
	}

	/**
	 * 利用sql语句创建SQLQuery对象
	 *
	 * @param sql
	 * @return
	 */
	protected Query getSQLQuery(String sql, Object... params) {
		Query query = getSession().createNativeQuery(sql);
		for (int x = 0; x < params.length; x++) {
			query.setParameter(x, params[x]);
		}
		return query;
	}

	/**
	 * 利用hql语句创建Criteria对象
	 *
	 * @param cls
	 * @return
	 */
	protected Criteria getCriteria(Class<?> cls) {
		return getSession().createCriteria(cls);
	}

	/**
	 * 处理公共的获取数据总数操作
	 *
	 * @param pojoName
	 * @param column
	 * @param keyword
	 * @return
	 */
	protected int getAllCount(String pojoName, String column, String keyword) {
		String hql = "SELECT COUNT(*) FROM " + pojoName + " WHERE " + column + " LIKE ?";
		return ((Long) getQuery(hql, "%" + keyword + "%").uniqueResult()).intValue();
	}

	protected int getAllCount(String pojoName, Page<?> page) {
		String hql = "SELECT COUNT(*) FROM " + pojoName + " WHERE " + page.getColumn() + " LIKE ?";
		return ((Long) getQuery(hql, "%" + page.getKeyword() + "%").uniqueResult()).intValue();
	}

	/**
	 * 处理公共的分页操作
	 *
	 * @param cls
	 * @param currentPage
	 * @param lineSize
	 * @param column
	 * @param keyword
	 * @return
	 */
	protected List listSplit(Class<?> cls, Integer currentPage, Integer lineSize, String column, String keyword) {
		Criteria criteria = getCriteria(cls);
		criteria.add(Restrictions.like(column, "%" + keyword + "%"));
		criteria.setFirstResult((currentPage - 1) * lineSize);
		criteria.setMaxResults(lineSize);
		return criteria.list();
	}

	protected <T> List<T> listSplit(Class<?> cls, Page<T> page) {
		Criteria criteria = getCriteria(cls);
		criteria.add(Restrictions.like(page.getColumn(), "%" + page.getKeyword() + "%"));
		criteria.setFirstResult((page.getCurPage() - 1) * page.getPageNumber());
		criteria.setMaxResults(page.getPageNumber());
		return criteria.list();
	}

	protected List list(Class<?> cls) {
		return getCriteria(cls).list();
	}

	/**
	 * 负责处理数据的删除操作
	 *
	 * @param ids    包含了所有要删除数据的id
	 * @param pojo   要删除数据的POJO名称
	 * @param idName 主键列的名称
	 * @return
	 */
	public boolean handleDeleteBatch(String pojo, String idName, Set<?> ids) {
		StringBuffer buf = new StringBuffer();
		buf.append("DELETE FROM ").append(pojo).append(" WHERE ")
				.append(idName).append(" IN (");
		Iterator<?> iter = ids.iterator();
		while (iter.hasNext()) {
			buf.append(iter.next()).append(",");
		}
		buf.delete(buf.length() - 1, buf.length()).append(")");
		Query query = this.getQuery(buf.toString());
		return query.executeUpdate() > 0;
	}

	public boolean changeState(String pojo, String col, int id, int state) {
		String hql = "UPDATE " + pojo + " p SET p.state=? WHERE p." + col + "=?";
		return getQuery(hql, state, id).executeUpdate() == 1;
	}
}

