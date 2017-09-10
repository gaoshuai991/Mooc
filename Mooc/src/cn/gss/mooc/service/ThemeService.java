package cn.gss.mooc.service;

import cn.gss.mooc.pojo.Theme;
import cn.gss.util.Page;

import java.io.Serializable;

public interface ThemeService {
	Serializable insert(Theme theme);

	Page<Theme> findByAdmin(Page<Theme> page);

	boolean delete(int theid);
}
