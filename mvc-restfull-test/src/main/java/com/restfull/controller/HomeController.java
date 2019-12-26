package com.restfull.controller;

import com.nf.mall.entity.goods.GoodsCategory;
import com.nf.mall.service.goods.GoodsCategoryService;
import com.restfull.dto.ResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Api(tags = "category表格操作")
public class HomeController {

    @Autowired
    private GoodsCategoryService goodsCategoryService;
    @GetMapping("/categorys")
    @ApiOperation("获得所有商品类型")
    public ResponseDTO listAllCategory(){
        List<GoodsCategory> result = goodsCategoryService.getAll();
        return new ResponseDTO("200","ok",result);
    }


    @GetMapping("/category/{id}")
    @ApiOperation("根据id查询用户信息")
    public ResponseDTO getById(@PathVariable int id){
        System.out.println("id = " + id);
        return new ResponseDTO("200","ok","这个操作懒得写了");
    }

    @DeleteMapping("/category/{id}")
    @ApiOperation("通过id，删除该类型")
    public ResponseDTO deleteById(@PathVariable int id){
        int count = goodsCategoryService.delete(id);
        if (count >0){
            return new ResponseDTO("200","删除成功",true);
        }else {
            return new ResponseDTO("500","删除失败",false);
        }
    }


    @PutMapping("/category/{id}")
    @ApiOperation("通过id修改信息")
    public ResponseDTO updateById(@PathVariable int id,GoodsCategory category){
       int count = goodsCategoryService.update(category);

        if (count >0){
            return new ResponseDTO("200","xg成功",true);
        }else {
            return new ResponseDTO("500","xg失败",false);
        }
    }

    @PostMapping("/category")
    @ApiOperation("添加类型")
    public ResponseDTO insert(GoodsCategory category){
        int count = goodsCategoryService.insert(category);
        if (count >0){
            return new ResponseDTO("200","tj成功",true);
        }else {
            return new ResponseDTO("500","tj失败",false);
        }

    }


}
