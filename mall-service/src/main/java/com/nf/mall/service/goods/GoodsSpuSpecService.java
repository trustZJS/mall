package com.nf.mall.service.goods;

import com.nf.mall.entity.goods.GoodsSpuSpec;

import java.util.List;

public interface GoodsSpuSpecService {

    List<GoodsSpuSpec> getAll(int pageNum,int pageSize,int specId);

    int delete(int id);

    int update(int specId,int spuId,int id);

    int insert(int specId,int spuId);

    List<GoodsSpuSpec> getGoodsName();
}
