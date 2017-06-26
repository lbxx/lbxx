package com.cdhaixun.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Supply  implements Serializable{
    private Integer id;

    private Integer suppierid;

    private String sku;

    private BigDecimal cost;

    private Integer stock;

    private Integer increment;

    private Date supplytime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSuppierid() {
        return suppierid;
    }

    public void setSuppierid(Integer suppierid) {
        this.suppierid = suppierid;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku == null ? null : sku.trim();
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getIncrement() {
        return increment;
    }

    public void setIncrement(Integer increment) {
        this.increment = increment;
    }

    public Date getSupplytime() {
        return supplytime;
    }

    public void setSupplytime(Date supplytime) {
        this.supplytime = supplytime;
    }
}