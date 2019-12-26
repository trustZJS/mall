package com.nf.mall.controller.goods.specAdministration;


import com.github.pagehelper.PageInfo;
import com.nf.mall.controller.BaseController;
import com.nf.mall.controller.goods.specAdministration.vo.GoodsSpecAdministrationEdit;
import com.nf.mall.controller.goods.specAdministration.vo.GoodsSpecAdministrationQuery;
import com.nf.mall.controller.goods.specAdministration.vo.GoodsSpecAdministrationVo;
import com.nf.mall.entity.goods.GoodsSpec;
import com.nf.mall.entity.goods.GoodsSpecValue;
import com.nf.mall.entity.goods.specAdministration.GoodsSpecAdministration;
import com.nf.mall.entity.goods.specAdministration.SpecAdministrationSpuAndSku;
import com.nf.mall.service.goods.GoodsSpecService;
import com.nf.mall.service.goods.GoodsSpecValueService;
import com.nf.mall.service.goods.specAdministration.GoodsSpecAdministrationService;
import com.nf.mall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/goods/spec/administration")
public class GoodsSpecAdministrationController extends BaseController {
    @Autowired
    private GoodsSpecAdministrationService goodsSpecAdministrationService;
    @Autowired
    private GoodsSpecService goodsSpecService;

    @Autowired
    private GoodsSpecValueService goodsSpecValueService;

    @RequestMapping("/index")
    public String index(){
        return "/goods/spec_administration/goods_spec_administration";
    }


    @RequestMapping("/query")
    @ResponseBody
    public GoodsSpecAdministrationQuery query(@RequestParam(value = "pageNum" ,required = false,defaultValue = "1") int pageNum,
                        @RequestParam(value = "pageSize" ,required = false,defaultValue = "8")int pageSize,
                        @RequestParam(value = "specId" ,required = false,defaultValue = "")  int specId,
                        @RequestParam(value = "goodsName" ,required = false,defaultValue = "")String goodsName){
            return goodsSpecAdministrationQuery(pageNum,pageSize,specId,goodsName);
    }

    @GetMapping("/delete")
    @ResponseBody
    public ResponseVo delete(@RequestParam(value = "gssvId" ,required = false,defaultValue = "") int gssvId){
        return deleteVo(goodsSpecAdministrationService.delete(gssvId));
    }

    @RequestMapping("/edit")
    @ResponseBody
    public ModelAndView edit(GoodsSpecAdministrationVo goodsSpecAdministrationVo){
        ModelAndView andView = new ModelAndView();
        System.out.println("goodsSpecAdministrationVo = " + goodsSpecAdministrationVo);
        andView.addObject("goodsSpecAdministrationVo",goodsSpecAdministrationVo);
        andView.setViewName("/goods/spec_administration/goods_spec_administration_edit");
        return andView;
    }


    @PostMapping("/editQuery")
    @ResponseBody
    public GoodsSpecAdministrationEdit editQuery(@RequestParam(value = "specId" ,required = false,defaultValue = "") int specId){
        return goodsSpecAdministrationEdit(specId);
    }


    @PostMapping("/update")
    @ResponseBody
    public ResponseVo update(@RequestParam(value = "gssvId" ,required = false,defaultValue = "")int gssvId,
                             @RequestParam(value = "gssId" ,required = false,defaultValue = "")int gssId,
                             @RequestParam(value = "specId" ,required = false,defaultValue = "") int specId,
                             @RequestParam(value = "specValueId" ,required = false,defaultValue = "") int specValueId){
        ResponseVo responseVo = null;
        boolean bool = goodsSpecAdministrationService.update(gssvId,gssId,specId,specValueId);
        if(bool){
            responseVo = new ResponseVo("200","修改成功","修改成功");
        }else {
            responseVo = new ResponseVo("500","修改失败","修改失败");
        }
        return responseVo;
    }



    @PostMapping("/insert")
    @ResponseBody
    public ResponseVo insert(@RequestParam(value = "specId" ,required = false,defaultValue = "")int specId,
                             @RequestParam(value = "specValueId" ,required = false,defaultValue = "")int specValueId,
                             @RequestParam(value = "spuId" ,required = false,defaultValue = "")int spuId,
                             @RequestParam(value = "skuId" ,required = false,defaultValue = "")int skuId){
        ResponseVo responseVo = null;
        boolean bool = goodsSpecAdministrationService.insert(spuId,skuId,specId,specValueId);
        if(bool){
            responseVo = new ResponseVo("200","添加成功","添加成功");
        }else {
            responseVo = new ResponseVo("500","添加失败","添加失败");
        }
        return responseVo;
    }
    @RequestMapping("/add")
    @ResponseBody
    public ModelAndView add(String state){
        ModelAndView andView = new ModelAndView();
        andView.addObject("state",state);
        andView.setViewName("/goods/spec_administration/goods_spec_administration_add");
        return andView;
    }

    @PostMapping("/addQuery")
    @ResponseBody
    public PageInfo addQuery(@RequestParam(value = "pageNum" ,required = false,defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize" ,required = false,defaultValue = "8")int pageSize,
                              @RequestParam(value = "goodsName" ,required = false,defaultValue = "")String goodsName){
            List<SpecAdministrationSpuAndSku> result = goodsSpecAdministrationService.getSpuAndSku(pageNum,pageSize,goodsName);
        System.out.println("====================================");
        System.out.println("result = " + result);
        PageInfo pageInfo = new PageInfo(result,3);
        return pageInfo;
    }




    public GoodsSpecAdministrationEdit goodsSpecAdministrationEdit(int specId){
        System.out.println("specId = " + specId);
        List<GoodsSpec> goodsSpecs = goodsSpecService.getAll();
        System.out.println("===================goodsSpecs = " + goodsSpecs);
        List<GoodsSpecValue> goodsSpecValues = goodsSpecValueService.getBySpecId(specId);
        System.out.println("========--------------goodsSpecValues = " + goodsSpecValues);
        GoodsSpecAdministrationEdit goodsSpecAdministrationEdit = new GoodsSpecAdministrationEdit(goodsSpecs,goodsSpecValues);
        return goodsSpecAdministrationEdit;
    }





    public GoodsSpecAdministrationQuery goodsSpecAdministrationQuery(int pageNum,int pageSize,int specId,String goodsName){
        System.out.println("pageNum = " + pageNum + ", pageSize = " + pageSize + ", specId = " + specId + ", goodsName = " + goodsName);
        List<GoodsSpec> goodsSpecs = goodsSpecService.getAll();
        System.out.println("goodsSpecs===============++++++++++++======"+goodsSpecs);
        List<GoodsSpecAdministration> goodsSpecAdministrations = goodsSpecAdministrationService.getSearch(pageNum,pageSize,specId,goodsName);
        System.out.println("===============++goodsSpecAdministrations++++++++++======"+goodsSpecAdministrations);
        PageInfo pageInfo = new PageInfo(goodsSpecAdministrations,3);
        GoodsSpecAdministrationQuery goodsSpecAdministrationQuery = new GoodsSpecAdministrationQuery(goodsSpecs,pageInfo);
        return goodsSpecAdministrationQuery;
    }
}
