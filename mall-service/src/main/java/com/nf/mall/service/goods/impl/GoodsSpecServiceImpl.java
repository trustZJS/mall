package com.nf.mall.service.goods.impl;

import com.nf.mall.dao.goods.GoodsSpecDao;
import com.nf.mall.entity.goods.GoodsSpec;
import com.nf.mall.service.goods.GoodsSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class GoodsSpecServiceImpl implements GoodsSpecService {
    @Autowired
    private GoodsSpecDao dao;

    @Override
    public List<GoodsSpec> getSearch(int pageNum, int pageSize, String name) {
        return dao.getSearch(pageNum,pageSize,name);
    }

    @Override
    public int delete(int id) {
        return dao.delete(id);
    }

    @Override
    public int update(GoodsSpec goodsSpec) {
        return dao.update(goodsSpec);
    }

    @Override
    public int insert(GoodsSpec goodsSpec) {
        return dao.insert(goodsSpec);
    }

    @Override
    public List<GoodsSpec> getAll() {
        return dao.getAll();
    }
}
