package com.nf.mall.service.goods;

import com.nf.mall.entity.goods.GoodsCategory;

import java.util.List;

public interface GoodsCategoryService {
    List<GoodsCategory> getSearch(int pageNum,int pageSize,String name);
    int delete (int id);
    int update(GoodsCategory goodsCategory);
    int insert(GoodsCategory goodsCategory);
    List<GoodsCategory> getAll();
}
