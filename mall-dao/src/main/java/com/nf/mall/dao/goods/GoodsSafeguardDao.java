package com.nf.mall.dao.goods;

import com.nf.mall.entity.goods.GoodsSafeguard;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//这个是增值表
public interface GoodsSafeguardDao {

    List<GoodsSafeguard> getAll(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize, @Param("name") String name);

    int delete(int id);



    //添加

    int insert (@Param("safeguard") GoodsSafeguard goodsSafeguard);

    int update(@Param("safeguard") GoodsSafeguard goodsSafeguard);


}
