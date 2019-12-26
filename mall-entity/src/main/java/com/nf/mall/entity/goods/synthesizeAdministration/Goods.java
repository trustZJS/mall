package com.nf.mall.entity.goods.synthesizeAdministration;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class Goods {


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

    private Integer spuId;
    public void setSkuImgPath(String skuImgPath) {
        this.skuImgPath = skuImgPath;
    }
}
