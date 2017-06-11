package com.cdhaixun.common.util;

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
	private long totals;
	/**
	 * 状态  success  error
	 */
	private String status;
	/**
	 * 列表数据集合
	 */
	private List data = new ArrayList();

	public PageData() {
	}

	public PageData(long totals, List data) {
		this.totals = totals;
		this.data = data;
	}

	public PageData(long totals, String status, List data) {
		this.totals = totals;
		this.status = status;
		this.data = data;
	}

	public long getTotals() {
		return totals;
	}

	public void setTotals(long totals) {
		this.totals = totals;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}
}
