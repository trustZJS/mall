package com.nf.mall.service.goods.impl;

import com.nf.mall.dao.goods.GoodsCategoryDao;
import com.nf.mall.entity.goods.GoodsCategory;
import com.nf.mall.service.goods.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService {
    @Autowired
    private GoodsCategoryDao dao;
    @Override
    public List<GoodsCategory> getSearch(int pageNum, int pageSize, String name) {
        return dao.getSearch(pageNum,pageSize,name);
    }

    @Override
    public int delete(int id) {
        return dao.delete(id);
    }

    @Override
    public int update(GoodsCategory goodsCategory) {
        return dao.update(goodsCategory);
    }

    @Override
    public int insert(GoodsCategory goodsCategory) {
        return dao.insert(goodsCategory);
    }

    @Override
    public List<GoodsCategory> getAll() {
        return dao.getAll();
    }
}
