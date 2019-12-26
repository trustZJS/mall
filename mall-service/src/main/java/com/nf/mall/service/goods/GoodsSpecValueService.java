package com.nf.mall.service.goods;

import com.nf.mall.entity.goods.GoodsSpecValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsSpecValueService {
    List<GoodsSpecValue> getAll(int pageNum,int pageSize ,int specId);

    int delete(int id);

    int insert(GoodsSpecValue goodsSpecValue);

    int update(GoodsSpecValue goodsSpecValue);

    List<GoodsSpecValue> getBySpecId(int specId);

}
