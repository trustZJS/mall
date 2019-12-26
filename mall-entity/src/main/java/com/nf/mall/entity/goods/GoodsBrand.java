package com.nf.mall.entity.goods;

//品牌表

import lombok.Data;

import java.sql.Timestamp;

@Data
public class GoodsBrand {
    private Integer id;
    private String brandName;
    private String imgPath;
    private Timestamp gmtCreate;
    private Timestamp gmtUpdate;

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
