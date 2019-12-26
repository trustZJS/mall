package com.nf.mall.dao.goods;


import com.nf.mall.entity.goods.GoodsSpecValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsSpecValueDao {
    List<GoodsSpecValue> getAll(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize,@Param("id") int specId);

    int delete(int id);

    int insert(@Param("specValues") GoodsSpecValue goodsSpecValue);

    int update(@Param("specValues") GoodsSpecValue goodsSpecValue);

    //这个是根据  规格来查询规格值的
    List<GoodsSpecValue> getBySpecId(@Param("sId") int specId);
}
