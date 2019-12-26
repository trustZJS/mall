package com.nf.mall.service.goods;

import com.nf.mall.entity.goods.GoodsBrand;

import java.util.List;

public interface GoodsBrandService {



    //    模糊查询和查询
    List<GoodsBrand> getSearch( int pageNum, int pageSize, String name);

    //删除
    int delete(int id);

    void insert(GoodsBrand goodsBrand);

    //修改
    int update(GoodsBrand goodsBrand);


    List<GoodsBrand> getAll();
}
