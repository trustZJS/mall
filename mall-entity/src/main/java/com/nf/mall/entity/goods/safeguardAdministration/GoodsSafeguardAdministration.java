package com.nf.mall.entity.goods.safeguardAdministration;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsSafeguardAdministration {

    private String goodsName;
    private String skuTitle;
    private String safeguardName;

    private BigDecimal price;
    private Integer gssId;
    private Integer skuId;

}
