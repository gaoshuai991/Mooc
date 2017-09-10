package cn.gss.mooc.service.impl;

import cn.gss.mooc.dao.ThemeDao;
import cn.gss.mooc.pojo.Theme;
import cn.gss.mooc.service.ThemeService;
import cn.gss.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.sql.SQLException;

@Service
public class ThemeServiceImpl implements ThemeService {
	@Autowired
	private ThemeDao themeDao;

	@Override
	public Serializable insert(Theme theme) {
		try {
			return themeDao.insert(theme);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Page<Theme> findByAdmin(Page<Theme> page) {
		try {
			page.setData(themeDao.listSplit(page.getCurPage(),page.getPageNumber(),page.getColumn(),page.getKeyword()));
			page.setRows(themeDao.getAllCount(page.getColumn(), page.getKeyword()));
			page.setTotalPage((page.getRows() + page.getPageNumber() - 1) / page.getPageNumber());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return page;
	}

	@Override
	public boolean delete(int theid) {
		try {
			return themeDao.delete(theid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
