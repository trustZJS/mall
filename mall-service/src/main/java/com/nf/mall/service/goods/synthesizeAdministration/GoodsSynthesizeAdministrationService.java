package com.nf.mall.service.goods.synthesizeAdministration;

import com.nf.mall.entity.goods.synthesizeAdministration.Goods;

import java.util.List;

public interface GoodsSynthesizeAdministrationService {

    boolean insert(Goods goods);

    boolean delete(int spuId,int skuId);

    List<Goods> queryEdit(int spuId);
}
