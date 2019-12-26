package com.nf.mall.service.login.impl;

import com.nf.mall.dao.login.ShopMemberInfoDao;
import com.nf.mall.entity.login.ShopMemberInfoEntity;
import com.nf.mall.service.login.ShopMemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopMemberInfoServiceImpl implements ShopMemberInfoService {
    @Autowired
    private ShopMemberInfoDao dao;

    @Override
    public boolean login(ShopMemberInfoEntity shopMemberInfoEntity) {
        boolean boo = false;
        int count = dao.login(shopMemberInfoEntity);
        if (count>0){
            boo = true;
        }else {
            boo = false;
        }
        return boo;
    }
}
