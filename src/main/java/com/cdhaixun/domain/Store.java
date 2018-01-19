package com.cdhaixun.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Store {
    private Integer id;

    private String name;

    private String telephone;

    private String cellphone;

    private String openinghours;

    private String closinghours;
    
    private String printersn; //打印机编号(SN)

    private String pic;

    private String city;//城市名称

    private String location;//详细地址

    private String description;

    private Integer chainstoreid;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private Date createtime;
    
    private String appid;
    
    private String cusid;
    
    private String key;
    
    private String subappid;
    
    private String submchid;


    private Boolean isdelete;
    private List<Business> businessList;
    private String orderBy;

    public List<Business> getBusinessList() {
        return businessList;
    }

    public void setBusinessList(List<Business> businessList) {
        this.businessList = businessList;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderBy() {
        return orderBy;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone == null ? null : cellphone.trim();
    }

    public String getOpeninghours() {
        return openinghours;
    }

    public void setOpeninghours(String openinghours) {
        this.openinghours = openinghours;
    }

    public String getClosinghours() {
        return closinghours;
    }

    public void setClosinghours(String closinghours) {
        this.closinghours = closinghours;
    }
        
    public String getPrintersn() {
        return printersn;
    }

    public void setPrintersn(String printersn) {
        this.printersn = printersn;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getChainstoreid() {
        return chainstoreid;
    }

    public void setChainstoreid(Integer chainstoreid) {
        this.chainstoreid = chainstoreid;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
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

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getCusid() {
        return cusid;
    }

    public void setCusid(String cusid) {
        this.cusid = cusid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSubappid() {
        return subappid;
    }

    public void setSubappid(String subappid) {
        this.subappid = subappid;
    }

    public String getSubmchid() {
        return submchid;
    }

    public void setSubmchid(String submchid) {
        this.submchid = submchid;
    }
    
    
}