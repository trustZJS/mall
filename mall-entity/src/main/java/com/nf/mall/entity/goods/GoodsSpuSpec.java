package com.nf.mall.entity.goods;

import lombok.Data;

@Data
public class GoodsSpuSpec {



    private String goodsName;
    private String specName;
    private Integer specId;
    private Integer spuId;
    //这个id是用来删除用的
    private Integer id;
    private String mgs;
}
