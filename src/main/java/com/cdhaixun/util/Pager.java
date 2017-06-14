package com.cdhaixun.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 后台接收前台传入的pager分页对象
 * @Author tanggm
 * @Date 2017/6/11 17:31
 */
public class Pager {
    /**
     * 每页显示条数
     */
    private int pageSize;
    /**
     * 当前页
     */
    private int pageNum;
    /**
     * 总页数
     */
    private int pages;
    /**
     * 总记录数
     */
    private long total;
    private List result = new ArrayList();

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getResult() {
        return result;
    }

    public void setResult(List result) {
        this.result = result;
    }
}
