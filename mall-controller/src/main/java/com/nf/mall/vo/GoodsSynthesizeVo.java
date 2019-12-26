package com.nf.mall.vo;

import com.github.pagehelper.PageInfo;
import com.nf.mall.entity.goods.GoodsBrand;
import com.nf.mall.entity.goods.GoodsCategory;
import com.nf.mall.entity.goods.GoodsSpuAndSpu;
import lombok.Data;

import java.util.List;
@Data
public class GoodsSynthesizeVo {

    List<GoodsBrand> goodsBrands;
    List<GoodsCategory> goodsCategories;
    PageInfo pageInfo;
    public GoodsSynthesizeVo(List<GoodsBrand> goodsBrands, List<GoodsCategory> goodsCategories, PageInfo pageInfo) {
        this.goodsBrands = goodsBrands;
        this.goodsCategories = goodsCategories;
        this.pageInfo = pageInfo;
    }
}
