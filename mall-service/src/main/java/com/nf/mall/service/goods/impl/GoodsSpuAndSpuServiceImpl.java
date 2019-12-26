package com.nf.mall.service.goods.impl;

import com.nf.mall.dao.goods.GoodsSpuAndSpuDao;
import com.nf.mall.entity.goods.GoodsSpuAndSpu;
import com.nf.mall.service.goods.GoodsSpuAndSpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodsSpuAndSpuServiceImpl implements GoodsSpuAndSpuService {

    @Autowired
    private GoodsSpuAndSpuDao dao;


    @Override
    public List<GoodsSpuAndSpu> getSpuAndSku(int categoryId, int brandId, int spuId) {
        return dao.getSpuAndSku(categoryId,brandId,spuId);
    }

    @Override
    public List<GoodsSpuAndSpu> getSpuAndSkuPage(int pageNum, int pageSize, int categoryId, int brandId, int spuId) {
        return dao.getSpuAndSkuPage(pageNum,pageSize,categoryId,brandId,spuId);
    }
//    @Transactional
    @Override
    public int delete(int id) {
       int sku = dao.deleteSku(id);
       int spu = dao.deleteSpu(id);
       return spu;
    }

    @Transactional
    @Override
    public boolean insertSpuAndSku(GoodsSpuAndSpu goodsSpuAndSpu) {
        boolean bool = false;
        int spu = dao.insertSpu(goodsSpuAndSpu);
        System.out.println("-------@@执行完spu"+goodsSpuAndSpu);
        int sku = dao.insertSku(goodsSpuAndSpu);
        if (spu>0 && sku > 0){
            bool = true;
        }
       return bool;
    }


    @Transactional
    @Override
    public boolean updateSpuAndSku(GoodsSpuAndSpu goodsSpuAndSpu) {
        boolean bool = false;
        int spu = dao.updateSpu(goodsSpuAndSpu);
        int sku = dao.updateSku(goodsSpuAndSpu);
        if (spu>0 && sku > 0){
            bool = true;
        }
        return bool;
    }


}
