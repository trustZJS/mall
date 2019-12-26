package com.nf.mall.vo;

//品牌表

import lombok.Data;

import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
public class GoodsBrandVo {
    private Integer id;
    @Size(max = 10,min = 2,message = "字符过长或者过短")
   // @BrandName
    private String brandName;
    private String imgPath;
    private Timestamp gmtCreate;
    private Timestamp gmtUpdate;
    private String mgs;

}
