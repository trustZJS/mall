package com.nf.mall.service.goods;

import com.nf.mall.entity.goods.GoodsSafeguard;

import java.util.List;

public interface GoodsSafeguardService {
    List<GoodsSafeguard> getAll(int pageNum,int pageSize,String name);

    int delete(int id);

    int insert(GoodsSafeguard goodsSafeguard);

    int update(GoodsSafeguard goodsSafeguard);

}
