package com.nf.mall.controller.goods.synthesizeAdministration.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsVo {

    private String spuNo;
    private String goodsName;
    private Integer stock;
    private String skuTitle;
    private Integer brandId;
    private Integer categoryId;
    private BigDecimal lowPrice;
    private BigDecimal price;
    //图片路径
    private String skuImgPath;

    public void setSkuImgPath(String skuImgPath) {
        this.skuImgPath = skuImgPath;
    }
}
