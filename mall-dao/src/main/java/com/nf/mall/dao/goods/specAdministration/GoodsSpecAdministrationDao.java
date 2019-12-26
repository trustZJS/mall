package com.nf.mall.dao.goods.specAdministration;

import com.nf.mall.entity.goods.specAdministration.GoodsSpecAdministration;
import com.nf.mall.entity.goods.specAdministration.SpecAdministrationSpuAndSku;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsSpecAdministrationDao {

    List<GoodsSpecAdministration> getSearch(@Param("pageNum") int pageNum,
                                            @Param("pageSize") int pageSize,
                                           @Param("sId") int specId,
                                            @Param("gName") String goodsName);


    //规格删除 需要删除两个表的数据,goods_spu_spec,goods_sku_spec_value
    int deleteGssId(int gssId);

    int deleteGssvId(int gssvId);


    int updateGssId(@Param("gId") int gssId,@Param("sId") int specId);


    int updateGssvId(@Param("gId") int gssvId,@Param("svId") int specValueId);

    int insertGssId(@Param("sId") int spuId,@Param("spId") int specId);

    int insertGssvId(@Param("sId") int skuId,@Param("svId") int specValueId);



    List<SpecAdministrationSpuAndSku> getSpuAndSku(@Param("pageNum") int pageNum,@Param("pageSize") int pageSize,@Param("gName") String goodsName);

}
