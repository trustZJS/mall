package com.nf.mall.entity.goods;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
@Data
public class GoodsSafeguard {

    private Integer id;
    private String safeguardName;
    private BigDecimal price;
    private Timestamp gmtCreate;
    private Timestamp gmtUpdate;
}
