package com.nf.mall.dao.goods;

import com.nf.mall.entity.goods.GoodsSpuSpec;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsSpuSpecDao {

    List<GoodsSpuSpec> getAll(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize,@Param("sId") int specId);

    int delete(@Param("sid") int id);
//    spec_id = 1 where spu_id =5;

    int insert(@Param("specId") int specId,@Param("spuId") int spuId);


    int update(@Param("specId") int specId,@Param("spuId") int spuId,@Param("sid") int id);


    List<GoodsSpuSpec> getGoodsName();
}
