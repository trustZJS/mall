package com.nf.mall.controller.goods.specAdministration.vo;

import com.github.pagehelper.PageInfo;
import com.nf.mall.entity.goods.GoodsSpec;
import lombok.Data;

import java.util.List;
@Data
public class GoodsSpecAdministrationQuery {
    public GoodsSpecAdministrationQuery(List<GoodsSpec> goodsSpecs, PageInfo pageInfo) {
        this.goodsSpecs = goodsSpecs;
        this.pageInfo = pageInfo;
    }

    List<GoodsSpec> goodsSpecs;
    PageInfo pageInfo;
}
