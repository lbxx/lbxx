package com.cdhaixun.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product  implements Serializable{
    private Integer id;

    private Integer orderid;

    private Integer quantity;

    private String sku;

    private BigDecimal price;

    private BigDecimal payment;

    private BigDecimal privilege;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku == null ? null : sku.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public BigDecimal getPrivilege() {
        return privilege;
    }

    public void setPrivilege(BigDecimal privilege) {
        this.privilege = privilege;
    }
}