package com.nf.mall.entity.goods.commodityManagement;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CommodityManagementEntity {

    private Integer spuId;
    private Integer skuId;

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

    private Integer categoryId;
    private Integer brandId;

    private Integer shopId;

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }



    public void setSkuImgPath(String skuImgPath) {
        this.skuImgPath = skuImgPath;
    }
}
