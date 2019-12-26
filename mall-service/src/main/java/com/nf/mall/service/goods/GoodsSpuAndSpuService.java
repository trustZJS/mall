package com.nf.mall.service.goods;

import com.nf.mall.entity.goods.GoodsSpuAndSpu;

import java.util.List;

public interface GoodsSpuAndSpuService {
    List<GoodsSpuAndSpu> getSpuAndSku(int categoryId, int brandId, int spuId);
    
    List<GoodsSpuAndSpu> getSpuAndSkuPage(int pageNum,int pageSize,int categoryId, int brandId, int spuId);


    int delete(int id);

    boolean insertSpuAndSku(GoodsSpuAndSpu goodsSpuAndSpu);


    boolean updateSpuAndSku(GoodsSpuAndSpu goodsSpuAndSpu);




}
