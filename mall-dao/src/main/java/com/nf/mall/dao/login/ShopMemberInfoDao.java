package com.nf.mall.dao.login;

import com.nf.mall.entity.login.ShopMemberInfoEntity;
import org.apache.ibatis.annotations.Param;

public interface ShopMemberInfoDao {
    //登录
    int login(@Param("smi") ShopMemberInfoEntity shopMemberInfoEntity);




}
