package com.nf.mall.service.goods.specAdministration.impl;

import com.nf.mall.dao.goods.specAdministration.GoodsSpecAdministrationDao;
import com.nf.mall.entity.goods.specAdministration.GoodsSpecAdministration;
import com.nf.mall.entity.goods.specAdministration.SpecAdministrationSpuAndSku;
import com.nf.mall.service.goods.specAdministration.GoodsSpecAdministrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class GoodsSpecAdministrationServiceImpl implements GoodsSpecAdministrationService {
    @Autowired
    private GoodsSpecAdministrationDao dao;

    @Override
    public List<GoodsSpecAdministration> getSearch(int pageNum, int pageSize, int specId, String goodsName) {
        return dao.getSearch(pageNum,pageSize,specId,goodsName);
    }


    @Override
    public int delete(int gssvId) {
     return dao.deleteGssvId(gssvId);
    }

    @Transactional
    @Override
    public boolean update(int gssvId, int gssId, int specId, int specValueId) {
        boolean bool = false;
        int count1 = dao.updateGssId(gssId,specId);
        int count2 = dao.updateGssvId(gssvId, specValueId);
        if (count1>0 && count2>0){
            bool = true;
        }
        return bool;
    }

    @Override
    public boolean insert(int spuId, int skuId, int specId, int specValueId) {
        boolean bool = false;
        int count1 = dao.insertGssId(spuId,specId);
        int count2 = dao.insertGssvId(skuId,specValueId);
        if (count1>0 && count2>0){
            bool = true;
        }
        return bool;
    }

    @Override
    public List<SpecAdministrationSpuAndSku> getSpuAndSku(int pageNum, int pageSize, String goodsName) {
        return dao.getSpuAndSku(pageNum,pageSize,goodsName);
    }
}
