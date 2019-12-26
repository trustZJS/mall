package com.nf.mall.dao.goods.safeguardAdministration;

import com.nf.mall.entity.goods.safeguardAdministration.GoodsSafeguardAdministration;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsSafeguardAdministrationDao {

    List<GoodsSafeguardAdministration> getAll(@Param("pageNum") int pageNum,
                                              @Param("pageSize") int pageSize,
                                             @Param("gName") String goodsName);


    int delete(@Param("gId") int gssId);

    int update(@Param("gId") int gssId,@Param("sId") int safeguardId);

    int insert(@Param("sId") int skuId,@Param("saId") int safeguardId);
}
