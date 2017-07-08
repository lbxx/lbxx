package com.cdhaixun.domain;

import java.util.Date;
import java.util.List;

public class Category {
    private Integer id;

    private String name;

    private Date createtime;

    private Boolean isdelete;
    private String  pic;
    private List<Business> businessList;

    private Integer enterStoreCount=0;//入驻商家
    private Integer nearbyStoreCount=0;//附近商家

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getEnterStoreCount() {
        return enterStoreCount;
    }

    public void setEnterStoreCount(Integer enterStoreCount) {
        this.enterStoreCount = enterStoreCount;
    }

    public Integer getNearbyStoreCount() {
        return nearbyStoreCount;
    }

    public void setNearbyStoreCount(Integer nearbyStoreCount) {
        this.nearbyStoreCount = nearbyStoreCount;
    }

    public List<Business> getBusinessList() {
        return businessList;
    }

    public void setBusinessList(List<Business> businessList) {
        this.businessList = businessList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Boolean getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Boolean isdelete) {
        this.isdelete = isdelete;
    }
}