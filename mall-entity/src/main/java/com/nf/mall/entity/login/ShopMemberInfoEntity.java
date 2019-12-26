package com.nf.mall.entity.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopMemberInfoEntity {
    private Integer id;
    private String username;
    private String password;
    private String phone;
}
