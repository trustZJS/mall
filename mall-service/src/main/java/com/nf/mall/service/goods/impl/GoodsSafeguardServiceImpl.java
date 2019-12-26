package com.nf.mall.service.goods.impl;

import com.nf.mall.dao.goods.GoodsSafeguardDao;
import com.nf.mall.entity.goods.GoodsSafeguard;
import com.nf.mall.service.goods.GoodsSafeguardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GoodsSafeguardServiceImpl implements GoodsSafeguardService {
    @Autowired
    private GoodsSafeguardDao dao;

    @Override
    public List<GoodsSafeguard> getAll(int pageNum, int pageSize, String name) {
        return dao.getAll(pageNum,pageSize,name);
    }
    @Override
    public int delete(int id) {
        return dao.delete(id);
    }

    @Override
    public int insert(GoodsSafeguard goodsSafeguard) {
        return dao.insert(goodsSafeguard);
    }

    @Override
    public int update(GoodsSafeguard goodsSafeguard) {
        return dao.update(goodsSafeguard);
    }

}
