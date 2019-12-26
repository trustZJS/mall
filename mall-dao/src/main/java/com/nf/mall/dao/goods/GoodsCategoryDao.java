package com.nf.mall.dao.goods;

import com.nf.mall.entity.goods.GoodsCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsCategoryDao {


    List<GoodsCategory> getSearch(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize, @Param("name") String name);

    int delete(int id);

    int update(@Param("category") GoodsCategory goodsCategory);

    int insert(@Param("category") GoodsCategory goodsCategory);


    List<GoodsCategory> getAll();

}
