package com.nf.mall.service.goods.impl;

import com.nf.mall.dao.goods.GoodsSpecValueDao;
import com.nf.mall.entity.goods.GoodsSpecValue;
import com.nf.mall.service.goods.GoodsSpecValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GoodsSpecValueServiceImpl implements GoodsSpecValueService {

    @Autowired
    private GoodsSpecValueDao dao;
    @Override
    public List<GoodsSpecValue> getAll(int pageNum, int pageSize,int specId) {
        return dao.getAll(pageNum,pageSize,specId);
    }

    @Override
    public int delete(int id) {
        return dao.delete(id);
    }

    @Override
    public int insert(GoodsSpecValue goodsSpecValue) {
        return dao.insert(goodsSpecValue);
    }

    @Override
    public int update(GoodsSpecValue goodsSpecValue) {
        return dao.update(goodsSpecValue);
    }

    @Override
    public List<GoodsSpecValue> getBySpecId(int specId) {
        return dao.getBySpecId(specId);
    }
}
