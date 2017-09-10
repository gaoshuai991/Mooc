package cn.gss.mooc.dao.impl;

import cn.gss.mooc.dao.UserDao;
import cn.gss.mooc.pojo.Message;
import cn.gss.mooc.pojo.User;
import cn.gss.util.Page;
import cn.gss.util.dao.AbstractDAOImpl;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Component
public class UserDaoImpl extends AbstractDAOImpl implements UserDao {
	@Override
	public User login(User user) throws SQLException {
		String hql = "From User AS u WHERE u.username=? AND u.password=?";
		return (User) getQuery(hql, user.getUsername(), user.getPassword()).uniqueResult();
	}

	@Override
	public boolean updatePwd(int userid, String newpwd) throws SQLException {
		String hql = "UPDATE User u SET u.password=? WHERE u.userid=?";
		return getQuery(hql, newpwd, userid).executeUpdate() == 1;
	}

	@Override
	public boolean changeState(int id, int state) throws SQLException {
		return changeState("User", "userid", id, state);
	}

	@Override
	public Page<User> findByAdmin(Page<User> page) throws SQLException {
		String hql = "FROM User u WHERE u.username LIKE ?";
		Query<User> query = getQuery(hql,User.class,"%" + page.getKeyword() + "%");
		query.setFirstResult((page.getCurPage() - 1) * page.getPageNumber());
		query.setMaxResults(page.getPageNumber());
		page.setData(query.list());
		return page;
	}

	@Override
	public int getAllCountByAdmin(Page<Message> page) throws SQLException {
		String hql = "SELECT COUNT(*) FROM User u WHERE u.username LIKE ?";
		Query<Long> query = getQuery(hql,Long.class,"%" + page.getKeyword() + "%");
		return query.uniqueResult().intValue();
	}

	@Override
	public boolean insert(User vo) throws SQLException {
		vo.setCreatetime(new Timestamp(System.currentTimeMillis()));
		return getSession().save(vo) != null;
	}

	@Override
	public boolean update(User vo) throws SQLException {
		String hql = "UPDATE User u SET u.realname=?,sex=?,hobbys=?,birthday=?,city=?,email=?,qq=? WHERE userid=?";
		return getQuery(hql, vo.getRealname(), vo.getSex(), vo.getHobbys(), vo.getBirthday(), vo.getCity(), vo.getEmail(), vo.getQq(), vo.getUserid()).executeUpdate() == 1;
	}

	@Override
	public boolean deleteBatch(Set<Integer> ids) throws SQLException {
		return false;
	}

	@Override
	public User get(Integer id) throws SQLException {
		return getSession().get(User.class, id);
	}

	@Override
	public List<User> list() throws SQLException {
		return list(User.class);
	}

	@Override
	public List<User> listSplit(Integer cp, Integer ls, String col, String kw) throws SQLException {
		return listSplit(User.class, cp, ls, col, kw);
	}

	@Override
	public Integer getAllCount(String col, String kw) throws SQLException {
		return super.getAllCount("User", col, kw);
	}
}
