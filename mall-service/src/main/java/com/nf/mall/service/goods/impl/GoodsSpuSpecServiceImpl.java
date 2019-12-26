package com.nf.mall.service.goods.impl;

import com.nf.mall.dao.goods.GoodsSpuSpecDao;
import com.nf.mall.entity.goods.GoodsSpuSpec;
import com.nf.mall.service.goods.GoodsSpuSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsSpuSpecServiceImpl implements GoodsSpuSpecService {
    @Autowired
    private GoodsSpuSpecDao dao;


    @Override
    public List<GoodsSpuSpec> getAll(int pageNum, int pageSize, int specId) {
        return dao.getAll(pageNum,pageSize,specId);
    }

    @Override
    public int delete(int id) {
        return dao.delete(id);
    }

    @Override
    public int update(int specId, int spuId, int id) {
        return dao.update(specId,spuId,id);
    }

    @Override
    public int insert(int specId, int spuId) {
        return dao.insert(specId,spuId);
    }

    @Override
    public List<GoodsSpuSpec> getGoodsName() {
        return dao.getGoodsName();
    }
}
