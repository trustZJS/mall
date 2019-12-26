package com.nf.mall.controller.goods.specAdministration.vo;

import com.nf.mall.entity.goods.GoodsSpec;
import com.nf.mall.entity.goods.GoodsSpecValue;
import lombok.Data;

import java.util.List;

@Data
public class GoodsSpecAdministrationEdit {
    public GoodsSpecAdministrationEdit(List<GoodsSpec> goodsSpecs, List<GoodsSpecValue> goodsSpecValues) {
        this.goodsSpecs = goodsSpecs;
        this.goodsSpecValues = goodsSpecValues;
    }

    List<GoodsSpec> goodsSpecs;
    List<GoodsSpecValue> goodsSpecValues;

}
