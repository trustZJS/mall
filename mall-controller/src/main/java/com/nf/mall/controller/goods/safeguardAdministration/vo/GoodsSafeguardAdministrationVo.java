package com.nf.mall.controller.goods.safeguardAdministration.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsSafeguardAdministrationVo {
    private String goodsName;
    private String skuTitle;
    private String safeguardName;

    private BigDecimal price;
    private Integer gssId;
    private Integer skuId;

    private String mgs;
}
