package com.nf.mall.controller.goods.synthesizeAdministration.vo;

import com.nf.mall.entity.goods.GoodsBrand;
import com.nf.mall.entity.goods.GoodsCategory;
import lombok.Data;

import java.util.List;

@Data
public class GoodsCategoryAndBrand {

    List<GoodsCategory> goodsCategories;
    List<GoodsBrand> goodsBrands;

    public GoodsCategoryAndBrand(List<GoodsCategory> goodsCategories, List<GoodsBrand> goodsBrands) {
        this.goodsCategories = goodsCategories;
        this.goodsBrands = goodsBrands;
    }
}
