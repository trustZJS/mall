package com.nf.mall.vo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class GoodsSpecVo {

    private Integer id;
    private String specNo;
    private String specName;
    private Timestamp gmtCreate;
    private Timestamp gmtUpdate;

    private String mgs;



}
