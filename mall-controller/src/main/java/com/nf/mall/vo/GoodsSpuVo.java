package com.nf.mall.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class GoodsSpuVo {

    private Integer id;
    private String spuNo;
    private String goodsName;
    private BigDecimal lowPrice;
    private Integer categoryId;
    private Integer brandId;

    private Timestamp gmtCreate;
    private Timestamp gmtUpdate;

    private String brandName;
    private String categoryName;
}
