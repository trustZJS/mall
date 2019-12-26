package com.nf.mall.entity.goods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data

public class GoodsCategory {
    private Integer id;
    private String categoryName;
    private Timestamp gmtCreate;
    private Timestamp gmtUpdate;


    public GoodsCategory() {
    }

    public GoodsCategory(Integer id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }
}
