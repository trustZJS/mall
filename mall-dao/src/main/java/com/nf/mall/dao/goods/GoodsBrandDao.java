package com.nf.mall.dao.goods;

import com.nf.mall.entity.goods.GoodsBrand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//品牌表的查询
public interface GoodsBrandDao {

    //删除品牌  通过id 删除
    int delete(@Param("did") int id);

    //修改  ， 通过id修改
    int update(@Param("goodsBrand") GoodsBrand goodsBrand);

    //模糊查询
    List<GoodsBrand> getSearch(@Param("pageNum") int pageNum,@Param("pageSize") int pageSize,@Param("name") String name);

    //添加品牌
    void insert(@Param("goodsBrand") GoodsBrand goodsBrand);

    //查出id和品牌名称，
    List<GoodsBrand> getAll();

}
