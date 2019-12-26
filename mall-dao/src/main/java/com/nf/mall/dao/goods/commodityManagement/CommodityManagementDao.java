package com.nf.mall.dao.goods.commodityManagement;

import com.nf.mall.entity.goods.commodityManagement.CommodityManagementEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityManagementDao {

    List<CommodityManagementEntity> getAll(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize, @Param("cid") int categoryId, @Param("bid") int brandId, @Param("name") String GoodsName);

    CommodityManagementEntity getById(@Param("sId") int spuId);

    int updateSpu(@Param("comm") CommodityManagementEntity commodityManagementEntity);
    int updateSku(@Param("comm") CommodityManagementEntity commodityManagementEntity);

}
