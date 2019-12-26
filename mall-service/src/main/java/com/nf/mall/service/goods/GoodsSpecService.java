package com.nf.mall.service.goods;

import com.nf.mall.entity.goods.GoodsSpec;

import java.util.List;

public interface GoodsSpecService {

    List<GoodsSpec> getSearch(int pageNum,int pageSize,String name);

    int delete (int id);

    int update(GoodsSpec goodsSpec);

    int insert(GoodsSpec goodsSpec);

    List<GoodsSpec> getAll();

}
