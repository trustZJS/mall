package com.nf.mall.service.goods.synthesizeAdministration.impl;

import com.nf.mall.dao.goods.synthesizeAdministration.GoodsSynthesizeAdministrationDao;
import com.nf.mall.entity.goods.synthesizeAdministration.Goods;
import com.nf.mall.service.goods.synthesizeAdministration.GoodsSynthesizeAdministrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodsSynthesizeAdministrationServiceImpl implements GoodsSynthesizeAdministrationService {
    @Autowired
    private GoodsSynthesizeAdministrationDao dao;


    @Transactional
    @Override
    public boolean insert(Goods goods) {
        boolean bool = false;
        int count1 = dao.insertSpu(goods);
        System.out.println("===========%%%%%%%%%%%##############===========================%%%%%%");
        System.out.println("goods = " + goods);
        int count2 = dao.insertSku(goods);
        if (count1>0&&count2>0){
            bool = true;
        }
        return bool;
    }

    @Transactional
    @Override
    public boolean delete(int spuId, int skuId) {
        boolean bool = false;
        int count1 = dao.deleteSpu(spuId);
        int count2 = dao.deleteSku(skuId);
        dao.deleteSpec(spuId);
        dao.deleteSpecValue(skuId);
        dao.deleteSafeguard(skuId);
        if (count1>0&&count2>0){
            bool = true;
        }
        return bool;
    }

    @Override
    public List<Goods> queryEdit(int spuId) {
        return dao.queryEdit(spuId);
    }
}
