package com.nf.mall.service.goods.safeguardAdministration;

import com.nf.mall.entity.goods.safeguardAdministration.GoodsSafeguardAdministration;

import java.util.List;

public interface GoodsSafeguardAdministrationService {

    List<GoodsSafeguardAdministration> getAll(int pageNum,int pageSize,String goodsName);


    int delete(int gssId);

    int update(int gssId,int safeguardId);

    int insert(int skuId,int safeguardId);
}
