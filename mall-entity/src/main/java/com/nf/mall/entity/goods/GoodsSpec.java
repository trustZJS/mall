package com.nf.mall.entity.goods;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class GoodsSpec {
    private Integer id;
    private String specNo;
    private String specName;
    private Timestamp gmtCreate;
    private Timestamp gmtUpdate;
}
