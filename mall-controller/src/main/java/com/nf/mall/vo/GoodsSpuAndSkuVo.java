package com.nf.mall.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsSpuAndSkuVo {

    private Integer id;
    private String spuNo;
    private String goodsName;
    private BigDecimal lowPrice;
    private BigDecimal price;
    private Integer stock;
    private String brandName;
    private String categoryName;
    private String skuTitle;
    private String skuImgPath;
    private String mgs;
    private Integer categoryId;
    private Integer brandId;

}
