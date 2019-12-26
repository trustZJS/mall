package com.nf.mall.controller.cors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nf.mall.entity.goods.GoodsCategory;
import com.nf.mall.service.goods.GoodsCategoryService;
import com.nf.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/cors")
@CrossOrigin
public class HomeController {

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    @RequestMapping("/list")
    @CrossOrigin
    public List<GoodsCategory> list(){
        List<GoodsCategory> list = new ArrayList<>();
        list.add(new GoodsCategory(1,"bb"));
        list.add(new GoodsCategory(2,"bb"));
        return list;
    }

    @RequestMapping("/list2")
    @ResponseBody
    public void list2(HttpServletResponse response) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://3g.163.com/touch/reconstruct/article/list/BA8E6OEOwangning/0-5.html";
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        String result = entity.getBody();
        String newsType = "BA8E6OEOwangning";
        int newsTypeIndex = result.indexOf(newsType);

        String jsonResult = result.substring(newsTypeIndex + newsType.length() + 2,
                result.length() - 2);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonResult);
    }

    @PostMapping("/insert")
    @ResponseBody
    public ResponseVo insert(String username){
        ResponseVo responseVo = null;
        System.out.println("username = " + username);
        responseVo = new ResponseVo("200","ok","okk");
        return responseVo;
    }

    @GetMapping("/query")
    @ResponseBody
    public List<GoodsCategory> query(){
        List<GoodsCategory> result = goodsCategoryService.getAll();
        return result;
    }


    //
    @RequestMapping("/list3")
    @CrossOrigin
    @ResponseBody
    public ResponseVo requestNetEase(HttpServletResponse response) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://3g.163.com/touch/reconstruct/article/list/BA8E6OEOwangning/0-5.html";

        ResponseEntity<String> entity = restTemplate.getForEntity(url,String.class);
        String result = entity.getBody();
        String newsType = "BA8E6OEOwangning";
        int newsTypeIndex = result.indexOf(newsType);

        String jsonResult = result.substring(newsTypeIndex + newsType.length() + 2,
                result.length() - 2);

        ObjectMapper objectMapper = new ObjectMapper();

        List<NetEaseNews> jsonObject =
                objectMapper.readValue(jsonResult, new TypeReference<List<NetEaseNews>>() {});
            ResponseVo responseVo = new ResponseVo("200","od",jsonObject);
        return responseVo;
    }

    @RequestMapping("/sw")
    @ResponseBody
    @CrossOrigin(allowCredentials = "true")
    public ResponseVo sw(HttpSession session){
        session.setAttribute("s","111111");
        return new ResponseVo("200","ok","session write ok");
    }

    @RequestMapping("/sr")
    @ResponseBody
    @CrossOrigin(allowCredentials = "true")
    public ResponseVo sr(HttpSession session){
        String sessionDate = session.getAttribute("s").toString();
        return new ResponseVo("200","ok",sessionDate);


    }

}
