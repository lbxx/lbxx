package com.cdhaixun.domain;

import com.mysql.jdbc.PacketTooBigException;

import java.math.BigDecimal;
import java.util.Date;

public class StorePotion {
    private Integer id;

    private Integer storeid;

    private Integer potionid;

    private Date createtime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoreid() {
        return storeid;
    }

    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }

    public Integer getPotionid() {
        return potionid;
    }

    public void setPotionid(Integer potionid) {
        this.potionid = potionid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}