package com.cdhaixun.common.appVo;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017-07-01.
 */
public class LonAndLat {
    private BigDecimal longitude;//精度
    private BigDecimal latitude;//纬度

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
}
