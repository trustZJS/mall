package com.nf.mall.vo;

import com.github.pagehelper.PageInfo;
import com.nf.mall.entity.goods.GoodsSpec;
import lombok.Data;

import java.util.List;

@Data
public class GoodsSpecAndSpecValue {
    List<GoodsSpec> goodsSpecs;
    PageInfo pageInfo;
    public GoodsSpecAndSpecValue(List<GoodsSpec> goodsSpecs, PageInfo pageInfo) {
        this.goodsSpecs = goodsSpecs;
        this.pageInfo = pageInfo;
    }
}
