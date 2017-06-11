package com.cdhaixun.common.util;

/**
 * 后台接收前台传入的pager分页对象
 * @Author tanggm
 * @Date 2017/6/11 17:31
 */
public class Pager {
    /**
     * 每页显示条数
     */
    private int pSize;
    /**
     * 当前页
     */
    private int cPage;
    /**
     * 总页数
     */
    private int tPage;
    /**
     * 总记录数
     */
    private long tSize;

    public int getpSize() {
        return pSize;
    }

    public void setpSize(int pSize) {
        this.pSize = pSize;
    }

    public int getcPage() {
        return cPage;
    }

    public void setcPage(int cPage) {
        this.cPage = cPage;
    }

    public int gettPage() {
        return tPage;
    }

    public void settPage(int tPage) {
        this.tPage = tPage;
    }

    public long gettSize() {
        return tSize;
    }

    public void settSize(long tSize) {
        this.tSize = tSize;
    }
}
