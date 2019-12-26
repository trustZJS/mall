package com.nf.mall.vo;

import com.github.pagehelper.PageInfo;
import com.nf.mall.entity.goods.GoodsSpec;
import lombok.Data;

import java.util.List;
@Data
public class GoodsSpuSpecAndSpecVo {

    List<GoodsSpec> goodsSpecs;

    public GoodsSpuSpecAndSpecVo(List<GoodsSpec> goodsSpecs, PageInfo pageInfo) {
        this.goodsSpecs = goodsSpecs;
        this.pageInfo = pageInfo;
    }

    PageInfo pageInfo;
}
