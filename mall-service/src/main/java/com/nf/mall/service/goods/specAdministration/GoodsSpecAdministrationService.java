package com.nf.mall.service.goods.specAdministration;

import com.nf.mall.entity.goods.specAdministration.GoodsSpecAdministration;
import com.nf.mall.entity.goods.specAdministration.SpecAdministrationSpuAndSku;

import java.util.List;

public interface GoodsSpecAdministrationService {

    List<GoodsSpecAdministration> getSearch(int pageNum,int pageSize,int specId,String goodsName);

    int delete(int gssvId);


    boolean update(int gssvId,int gssId ,int specId,int specValueId);


    boolean insert(int spuId,int skuId,int specId,int specValueId);

    List<SpecAdministrationSpuAndSku> getSpuAndSku(int pageNum,int pageSize,String goodsName);

}
