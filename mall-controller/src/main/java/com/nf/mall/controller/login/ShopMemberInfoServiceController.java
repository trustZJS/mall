package com.nf.mall.controller.login;

import com.nf.mall.entity.login.ShopMemberInfoEntity;
import com.nf.mall.service.login.ShopMemberInfoService;
import com.nf.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/shop")
@Controller
public class ShopMemberInfoServiceController {
    @Autowired
    private ShopMemberInfoService shopMemberInfoService;

    @RequestMapping("/login")
    public String login(){
        return "/login/login_index";
    }

    @RequestMapping("/verify")
    @ResponseBody
    public ResponseVo verify(ShopMemberInfoEntity shopMemberInfoEntity){
        ResponseVo responseVo = null;
        boolean boo = shopMemberInfoService.login(shopMemberInfoEntity);
        if (boo){
            responseVo = new ResponseVo("200","cg","cg");
        }else {
            responseVo = new ResponseVo("500","sb","sb");
        }

        return responseVo;
    }

}
