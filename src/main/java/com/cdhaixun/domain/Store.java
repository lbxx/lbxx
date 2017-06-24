package com.cdhaixun.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Store {
    private Integer id;

    private String name;

    private String telephone;

    private String cellphone;

    private Date openinghours;

    private Date closinghours;

    private String pic;

    private String location;

    private String description;

    private Integer chainstoreid;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private Date createtime;

    private Boolean isdelete;

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

    public Date getOpeninghours() {
        return openinghours;
    }

    public void setOpeninghours(Date openinghours) {
        this.openinghours = openinghours;
    }

    public Date getClosinghours() {
        return closinghours;
    }

    public void setClosinghours(Date closinghours) {
        this.closinghours = closinghours;
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
}