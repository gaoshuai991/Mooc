package cn.gss.util;


import java.util.ArrayList;
import java.util.List;

/**
 * 分页类，封装分页基本信息。首页默认1
 *
 * @author Administrator
 * @version v1.0
 */
public class Page<T> {
	//当前页
	private int curPage = 1;
	//总页数
	private int totalPage;
	//数据库记录数
	private int rows;
	//每页数据量
	private int pageNumber = 5;
	//要展示的List数据
	private List<T> data = new ArrayList<>();

	private String column;
	private String keyword;

	public Page() {
	}

	public Page(int curPage, int pageNumber, String column, String keyword) {
		this.curPage = curPage;
		this.pageNumber = pageNumber;
		this.column = column;
		this.keyword = keyword;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
