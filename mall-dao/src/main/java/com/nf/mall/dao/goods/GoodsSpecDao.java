package com.nf.mall.dao.goods;

import com.nf.mall.entity.goods.GoodsSpec;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsSpecDao {

    List<GoodsSpec> getSearch(@Param("pageNum") int pageNum,@Param("pageSize") int pageSize ,@Param("name") String name);

    int delete(int id);

    int insert(@Param("spec") GoodsSpec goodsSpec);

    int update(@Param("spec") GoodsSpec goodsSpec);

    List<GoodsSpec> getAll();

}
