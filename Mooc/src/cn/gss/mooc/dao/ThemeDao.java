package cn.gss.mooc.dao;

import cn.gss.mooc.pojo.Theme;
import cn.gss.util.dao.IDAO;

import java.io.Serializable;
import java.sql.SQLException;

public interface ThemeDao extends IDAO<Integer,Theme>{
	boolean delete(int theid) throws SQLException;

	Serializable add(Theme vo) throws SQLException;
}
