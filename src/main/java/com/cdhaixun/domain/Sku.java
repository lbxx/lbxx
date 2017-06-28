package com.cdhaixun.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Sku  implements Serializable{
    private Integer id;

    private String name;

    private String sku;

    private Integer parentid;

    private Integer nuitid;

    private Integer merchandiseid;

    private Integer stock;

    private BigDecimal price;

    private Boolean isdiscount;

    private Integer point;

    private Boolean ispoint;

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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku == null ? null : sku.trim();
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getNuitid() {
        return nuitid;
    }

    public void setNuitid(Integer nuitid) {
        this.nuitid = nuitid;
    }

    public Integer getMerchandiseid() {
        return merchandiseid;
    }

    public void setMerchandiseid(Integer merchandiseid) {
        this.merchandiseid = merchandiseid;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getIsdiscount() {
        return isdiscount;
    }

    public void setIsdiscount(Boolean isdiscount) {
        this.isdiscount = isdiscount;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Boolean getIspoint() {
        return ispoint;
    }

    public void setIspoint(Boolean ispoint) {
        this.ispoint = ispoint;
    }
}