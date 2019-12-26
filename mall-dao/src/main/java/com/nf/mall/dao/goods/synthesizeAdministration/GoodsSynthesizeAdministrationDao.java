package com.nf.mall.dao.goods.synthesizeAdministration;

import com.nf.mall.entity.goods.synthesizeAdministration.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsSynthesizeAdministrationDao {

    int insertSpu(@Param("gs") Goods goods);

    int insertSku(@Param("gs") Goods goods);


    int deleteSpu(@Param("sId") int spuId);

    int deleteSku(@Param("sId") int skuId);

    int deleteSpec (@Param("sId") int spuId);

    int deleteSpecValue(@Param("sId") int skuId);

    int deleteSafeguard(@Param("sId") int skuId);



    List<Goods> queryEdit(@Param("sId") int spuId);



}
