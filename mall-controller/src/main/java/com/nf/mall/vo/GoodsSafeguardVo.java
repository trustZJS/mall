package com.nf.mall.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
@Data
public class GoodsSafeguardVo {

    private Integer id;
    private String safeguardName;
    private BigDecimal price;
    private Timestamp gmtCreate;
    private Timestamp gmtUpdate;

    private String mgs;

}
