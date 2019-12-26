package com.nf.mall.controller.goods;


import com.github.pagehelper.PageInfo;
import com.nf.mall.controller.BaseController;
import com.nf.mall.entity.goods.GoodsSafeguard;
import com.nf.mall.entity.goods.GoodsSpec;
import com.nf.mall.service.goods.GoodsSpecService;
import com.nf.mall.vo.GoodsSafeguardVo;
import com.nf.mall.vo.GoodsSpecVo;
import com.nf.mall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/goods/spec")
public class GoodsSpecController extends BaseController {
    @Autowired
    private GoodsSpecService goodsSpecService;

    @RequestMapping("/index")
    public String index(){
        return "/goods/goods_spec";
    }


    @RequestMapping("/edit")
    public ModelAndView edit(GoodsSpecVo goodsSpecVo){
        ModelAndView andView = new ModelAndView();
        System.out.println("==========");
        System.out.println(goodsSpecVo);
        andView.setViewName("/goods/goods_spec_edit");
        andView.addObject("goodsSpecVo",goodsSpecVo);

        return andView;
    }

    @PostMapping("/insert")
    @ResponseBody
    public ResponseVo insert(@Valid GoodsSpecVo goodsSpecVo, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){
        System.out.println("goodsSpecVo======insert========"+goodsSpecVo);
        ResponseVo responseVo = null;
        GoodsSpec goodsSpec = new GoodsSpec();
        BeanUtils.copyProperties(goodsSpecVo,goodsSpec);
        if(bindingResult.hasErrors()){
            responseVo = new ResponseVo("500","添加失败",bindingResult.getFieldErrors());
        }else {

            redirectAttributes.addFlashAttribute("goodsSpec",new GoodsSpec());
            System.out.println("开2");
            goodsSpecService.insert(goodsSpec);
            System.out.println("结");
            responseVo = new ResponseVo("200","添加成功","添加成功");
        }
        return responseVo;
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseVo update(@Valid GoodsSpecVo goodsSpecVo, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        System.out.println("goodsSpecVo======update========"+goodsSpecVo);
        ResponseVo responseVo = null;
        GoodsSpec goodsSpec = new GoodsSpec();
        BeanUtils.copyProperties(goodsSpecVo, goodsSpec);
        if (bindingResult.hasErrors()) {
            responseVo = new ResponseVo("500", "修改失败", bindingResult.getFieldErrors());
        } else {
            redirectAttributes.addFlashAttribute("goodsSpec", new GoodsSpec());

            System.out.println("开1");
            goodsSpecService.update(goodsSpec);
            System.out.println("结");

            responseVo = new ResponseVo("200", "修改成功", "修改成功");
        }
        return responseVo;
    }


    @RequestMapping("/query")
    @ResponseBody
    public PageInfo query(@RequestParam(value = "pageNum" ,required = false,defaultValue = "1") int pageNum,
                          @RequestParam(value = "pageSize" ,required = false,defaultValue = "10")int pageSize,
                          @RequestParam(value = "username" ,required = false,defaultValue = "") String name){
        List<GoodsSpec> goodsSpecs = goodsSpecService.getSearch(pageNum,pageSize,name);
        PageInfo pageInfo = new PageInfo(goodsSpecs,3);
        return pageInfo;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ResponseVo delete(int id){
        System.out.println(id);
        return  deleteVo(goodsSpecService.delete(id));
    }

}
