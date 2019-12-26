package com.nf.mall.entity.goods;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class GoodsSpecValue {

    private Integer id;
    private Integer specId;
    private String specValue;
    private Timestamp gmtCreate;
    private Timestamp gmtUpdate;

    private String specName;
}
