package com.nf.mall.vo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class GoodsCategoryVo {
    private Integer id;
    private String categoryName;
    private Timestamp gmtCreate;
    private Timestamp gmtUpdate;
}
