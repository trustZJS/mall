package com.nf.mall.dao.goods;

import com.nf.mall.entity.goods.GoodsSpuAndSpu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsSpuAndSpuDao {

    //还有一个是商家id,因为每个商家都有自己买的产品，
    //这个是商品总览查询,也可以在上面的下画框,选择类型 品牌，获得id ，来查询 ，
    //通过Ajax来显示
    List<GoodsSpuAndSpu> getSpuAndSkuPage(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize,@Param("cid") int categoryId,@Param("bid") int brandId,@Param("sid") int spuId);


    List<GoodsSpuAndSpu> getSpuAndSku(@Param("cid") int categoryId,@Param("bid") int brandId,@Param("sid") int spuId);


    int deleteSpu(int id);

    int deleteSku(int spuId);
    //录入信息是要返回当前的id
    int insertSpu(@Param("spuAndSpu") GoodsSpuAndSpu goodsSpuAndSpu);
    int insertSku(@Param("skuAndSpu") GoodsSpuAndSpu goodsSpuAndSpu);


    int updateSpu(@Param("spuAndSpu") GoodsSpuAndSpu goodsSpuAndSpu);
    int updateSku(@Param("spuAndSpu") GoodsSpuAndSpu goodsSpuAndSpu);
}
