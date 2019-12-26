package com.nf.mall.controller.goods;


import com.github.pagehelper.PageInfo;
import com.nf.mall.controller.BaseController;
import com.nf.mall.entity.goods.GoodsSafeguard;
import com.nf.mall.entity.goods.GoodsSpec;
import com.nf.mall.entity.goods.GoodsSpecValue;
import com.nf.mall.service.goods.GoodsSpecService;
import com.nf.mall.service.goods.GoodsSpecValueService;
import com.nf.mall.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

//由于规格值对应这每个规格名称，所有这里同时要查询两个表的值
@Controller
@RequestMapping("/goods/specValue")
public class GoodsSpecValueController extends BaseController {

    @Autowired
    private GoodsSpecService goodsSpecService;
    @Autowired
    private GoodsSpecValueService goodsSpecValueService;

    @RequestMapping("/index")
    public String index(){
        return "/goods/goods_specValue";
    }

    @RequestMapping("/query")
    @ResponseBody
    public GoodsSpecAndSpecValue query(@RequestParam(value = "pageNum" ,required = false,defaultValue = "1") int pageNum,
                                       @RequestParam(value = "pageSize" ,required = false,defaultValue = "9")int pageSize,
                                       @RequestParam(value = "specId" ,required = false,defaultValue = "")int specId){
        System.out.println("==========specId======================="+specId);
        GoodsSpecAndSpecValue goodsSpecAndSpecValue = goodsSpecAndSpecValue(pageNum,pageSize,specId);
        return goodsSpecAndSpecValue;
    }



    public GoodsSpecAndSpecValue goodsSpecAndSpecValue(int pageNum,int pageSize,int specId){
            List<GoodsSpec> goodsSpecs = goodsSpecService.getAll();
            System.out.println("goodsSpecs:"+goodsSpecs);
            List<GoodsSpecValue> goodsSpecValues = goodsSpecValueService.getAll(pageNum,pageSize,specId);
            System.out.println("======specId=============+++++++++++====="+specId);
            System.out.println("goodsSpecValues"+goodsSpecValues);
            PageInfo pageInfo = new PageInfo(goodsSpecValues,3);
            GoodsSpecAndSpecValue goodsSpecAndSpecValue = new GoodsSpecAndSpecValue(goodsSpecs,pageInfo);
            return goodsSpecAndSpecValue;
    }
    @GetMapping("/delete")
    @ResponseBody
    public ResponseVo delete(int id){
        return deleteVo(goodsSpecValueService.delete(id));
    }


    @RequestMapping("/edit")
    @ResponseBody
    public ModelAndView edit(GoodsSpecValueVo goodsSpecValueVo){
        System.out.println("==================333333333==============");
        System.out.println(goodsSpecValueVo);
        System.out.println("==================333333333==============");
        ModelAndView andView = new ModelAndView();
        List<GoodsSpec> goodsSpecs = goodsSpecService.getAll();
        andView.addObject("goodsSpecValueVo",goodsSpecValueVo);
        andView.addObject("goodsSpecs",goodsSpecs);
        andView.setViewName("/goods/goods_specValue_edit");
        return andView;
    }


    @RequestMapping("/insert")
    @ResponseBody
    public ResponseVo insert(@Valid GoodsSpecValueVo goodsSafeguardVo, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){
        ResponseVo responseVo = null;
        GoodsSpecValue goodsSpecValue = new GoodsSpecValue();
        BeanUtils.copyProperties(goodsSafeguardVo,goodsSpecValue);
        if(bindingResult.hasErrors()){
            responseVo = new ResponseVo("500","添加失败",bindingResult.getFieldErrors());
        }else {
            redirectAttributes.addFlashAttribute("goodsSpecValue",new GoodsSpecValue());
            goodsSpecValueService.insert(goodsSpecValue);
            responseVo = new ResponseVo("200","添加成功","添加成功");
        }
        return responseVo;
    }


    @RequestMapping("/update")
    @ResponseBody
    public ResponseVo update(@Valid GoodsSpecValueVo goodsSafeguardVo, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){
        ResponseVo responseVo = null;
        GoodsSpecValue goodsSpecValue = new GoodsSpecValue();
        BeanUtils.copyProperties(goodsSafeguardVo,goodsSpecValue);
        if(bindingResult.hasErrors()){
            responseVo = new ResponseVo("500","修改失败",bindingResult.getFieldErrors());
        }else {
            redirectAttributes.addFlashAttribute("goodsSpecValue",new GoodsSpecValue());
            System.out.println("==========goodsSpecValue===================");
            System.out.println(goodsSpecValue);
            goodsSpecValueService.update(goodsSpecValue);
            responseVo = new ResponseVo("200","修改成功","修改成功");
        }
        return responseVo;
    }
}
