package com.nf.mall.controller.goods;

import com.github.pagehelper.PageInfo;
import com.nf.mall.controller.BaseController;
import com.nf.mall.entity.goods.GoodsBrand;
import com.nf.mall.entity.goods.GoodsCategory;
import com.nf.mall.service.goods.GoodsCategoryService;
import com.nf.mall.vo.GoodsCategoryVo;
import com.nf.mall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/goods/category")
public class GoodsCategoryController extends BaseController {
    @Autowired
    private GoodsCategoryService goodsCategoryService;

    @RequestMapping("list")
    private String list(){
        return "/goods/goods_category";
    }

    @RequestMapping("/query")
    @ResponseBody
    public PageInfo list(@RequestParam(value = "pageNum" ,required = false,defaultValue = "1") int pageNum,
                       @RequestParam(value = "pageSize" ,required = false,defaultValue = "10")int pageSize,
                       @RequestParam(value = "username" ,required = false,defaultValue = "") String username){
        List<GoodsCategory> result = goodsCategoryService.getSearch(pageNum,pageSize,username);
        PageInfo pageInfo = new PageInfo(result,3);
        return pageInfo;
    }

    @GetMapping("/delete")
    @ResponseBody
    public ResponseVo delete(int id){
        return deleteVo(goodsCategoryService.delete(id));
    }

    @PostMapping("/insert")
    @ResponseBody
    public ResponseVo insert(@Valid GoodsCategoryVo goodsCategoryVo, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){
        ResponseVo responseVo = null;
        GoodsCategory goodsCategory = new GoodsCategory();
        BeanUtils.copyProperties(goodsCategoryVo,goodsCategory);
        if(bindingResult.hasErrors()){
            responseVo = new ResponseVo("500","添加失败",bindingResult.getFieldErrors());
        }else {
            redirectAttributes.addFlashAttribute("goodsCategory",new GoodsCategory());
            System.out.println("======================================");
            System.out.println(goodsCategory);
            goodsCategoryService.insert(goodsCategory);
            responseVo = new ResponseVo("200","添加成功","添加成功");
        }
        return responseVo;
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseVo update(@Valid GoodsCategoryVo goodsCategoryVo, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){

        ResponseVo responseVo = null;
        GoodsCategory goodsCategory = new GoodsCategory();
        BeanUtils.copyProperties(goodsCategoryVo,goodsCategory);
        if(bindingResult.hasErrors()){
            responseVo = new ResponseVo("500","修改失败",bindingResult.getFieldErrors());
        }else {
            redirectAttributes.addFlashAttribute("goodsCategory",new GoodsCategory());
            goodsCategoryService.update(goodsCategory);
            responseVo = new ResponseVo("200","修改成功","修改成功");
        }
        return responseVo;
    }

}
