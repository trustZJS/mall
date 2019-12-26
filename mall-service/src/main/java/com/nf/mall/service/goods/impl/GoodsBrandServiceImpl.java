package com.nf.mall.service.goods.impl;

import com.nf.mall.dao.goods.GoodsBrandDao;
import com.nf.mall.entity.goods.GoodsBrand;
import com.nf.mall.service.goods.GoodsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsBrandServiceImpl implements GoodsBrandService {
    @Autowired
    private GoodsBrandDao goodsBrandDao;

    @Override
    public List<GoodsBrand> getSearch(int pageNum, int pageSize, String name) {
        return goodsBrandDao.getSearch(pageNum,pageSize,name);
    }

    @Override
    public void insert(GoodsBrand goodsBrand) {
        goodsBrandDao.insert(goodsBrand);
    }


    @Override
    public int delete(int id) {
       return goodsBrandDao.delete(id);
    }

    @Override
    public int update(GoodsBrand goodsBrand) {
       return goodsBrandDao.update(goodsBrand);
    }

    @Override
    public List<GoodsBrand> getAll() {
        return goodsBrandDao.getAll();
    }
}
