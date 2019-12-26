package com.nf.mall.vo;

import lombok.Data;

import java.sql.Timestamp;


@Data
public class GoodsSpecValueVo {

    private Integer id;
    private Integer specId;
    private String specValue;
    private Timestamp gmtCreate;
    private Timestamp gmtUpdate;

    private String specName;
    private String mgs;
}
