package com.nf.mall.service.goods.commodityManagement.impl;

import com.nf.mall.dao.goods.commodityManagement.CommodityManagementDao;
import com.nf.mall.entity.goods.commodityManagement.CommodityManagementEntity;
import com.nf.mall.service.goods.commodityManagement.CommodityManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityManagementServiceImpl implements CommodityManagementService {
    @Autowired
    private CommodityManagementDao dao;

    @Override
    public List<CommodityManagementEntity> getAll(int pageNum, int pageSize, int categoryId, int brandId, String goodsName) {
        return dao.getAll(pageNum,pageSize,categoryId,brandId,goodsName);
    }

    @Override
    public CommodityManagementEntity getById(int spuId) {
        return dao.getById(spuId);
    }


    @Override
    public boolean update(CommodityManagementEntity commodityManagementEntity) {
        boolean bool = false;
       int count1 = dao.updateSpu(commodityManagementEntity);
       int count2 =  dao.updateSku(commodityManagementEntity);
        if (count1>0|| count2>0){
            bool =true;
        }else {
            bool = false;
        }

        return bool;
    }
}
