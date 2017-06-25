package com.cdhaixun.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页列表数据封装
 * @Author tanggm
 * @Date 2017/6/11 17:31
 */
public class PageData {
	/**
	 * 总条数
	 */
	private long records;
	/**
	 * 总页数
	 */
	private int total;
	/**
	 * 当前页
	 */
	private int page;
	/**
	 * 列表数据集合
	 */
	private List rows = new ArrayList();

	public PageData() {
	}

	public PageData(long records, int total, int page, List rows) {
		this.records = records;
		this.total = total;
		this.page = page;
		this.rows = rows;
	}

	public long getRecords() {
		return records;
	}

	public void setRecords(long records) {
		this.records = records;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}
}
