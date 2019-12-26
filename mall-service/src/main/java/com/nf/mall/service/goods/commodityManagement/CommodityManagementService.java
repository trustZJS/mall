package com.nf.mall.service.goods.commodityManagement;

import com.nf.mall.entity.goods.commodityManagement.CommodityManagementEntity;

import java.util.List;

public interface CommodityManagementService {

    List<CommodityManagementEntity> getAll(int pageNum,int pageSize,int categoryId,int brandId,String goodsName);

    CommodityManagementEntity getById(int spuId);

    boolean update(CommodityManagementEntity commodityManagementEntity);
}
