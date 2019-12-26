package com.nf.mall.controller.goods.commodityManagement.vo;

import com.github.pagehelper.PageInfo;
import com.nf.mall.entity.goods.GoodsBrand;
import com.nf.mall.entity.goods.GoodsCategory;
import lombok.Data;

import java.util.List;

@Data
public class CommodityManagementVo {

    List<GoodsBrand> goodsBrands ;
    List<GoodsCategory> categories;
    PageInfo pageInfo;

    public CommodityManagementVo(List<GoodsBrand> goodsBrands, List<GoodsCategory> categories, PageInfo pageInfo) {
        this.goodsBrands = goodsBrands;
        this.categories = categories;
        this.pageInfo = pageInfo;
    }
}
