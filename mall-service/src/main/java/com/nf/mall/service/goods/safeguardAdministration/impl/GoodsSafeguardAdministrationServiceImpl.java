package com.nf.mall.service.goods.safeguardAdministration.impl;

import com.nf.mall.dao.goods.safeguardAdministration.GoodsSafeguardAdministrationDao;
import com.nf.mall.entity.goods.safeguardAdministration.GoodsSafeguardAdministration;
import com.nf.mall.service.goods.safeguardAdministration.GoodsSafeguardAdministrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsSafeguardAdministrationServiceImpl implements GoodsSafeguardAdministrationService {
    @Autowired
    private GoodsSafeguardAdministrationDao dao;
    @Override
    public List<GoodsSafeguardAdministration> getAll(int pageNum, int pageSize, String goodsName) {
        return dao.getAll(pageNum,pageSize,goodsName);
    }

    @Override
    public int delete(int gssId) {
        return dao.delete(gssId);
    }

    @Override
    public int update(int gssId, int safeguardId) {
        return dao.update(gssId,safeguardId);
    }

    @Override
    public int insert(int skuId, int safeguardId) {
        return dao.insert(skuId,safeguardId);
    }
}
