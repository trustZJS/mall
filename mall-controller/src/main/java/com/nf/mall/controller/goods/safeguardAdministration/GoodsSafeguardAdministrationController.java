package com.nf.mall.controller.goods.safeguardAdministration;

import com.github.pagehelper.PageInfo;
import com.nf.mall.controller.BaseController;
import com.nf.mall.controller.goods.safeguardAdministration.vo.GoodsSafeguardAdministrationVo;
import com.nf.mall.entity.goods.safeguardAdministration.GoodsSafeguardAdministration;
import com.nf.mall.service.goods.safeguardAdministration.GoodsSafeguardAdministrationService;
import com.nf.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/goods/safeguard/administration")
public class GoodsSafeguardAdministrationController extends BaseController {

    @Autowired
    private GoodsSafeguardAdministrationService goodsSafeguardAdministrationService;

    @RequestMapping("/index")
    public String index(){
        return "/goods/safeguard_administration/goods_safeguard_administration";
    }

    @PostMapping("/query")
    @ResponseBody
    public PageInfo query(@RequestParam(value = "pageNum" ,required = false,defaultValue = "1") int pageNum,
                          @RequestParam(value = "pageSize" ,required = false,defaultValue = "8")int pageSize,
                          @RequestParam(value = "goodsName" ,required = false,defaultValue = "")  String  goodsName){
        List<GoodsSafeguardAdministration> result = goodsSafeguardAdministrationService.getAll(pageNum,pageSize,goodsName);
        System.out.println("=======================result = " + result);
        PageInfo pageInfo = new PageInfo(result,3);
        return pageInfo;
    }

    @GetMapping("/delete")
    @ResponseBody
    public ResponseVo delete(@RequestParam(value = "gssId" ,required = false,defaultValue = "")int gssId){
        return deleteVo(goodsSafeguardAdministrationService.delete(gssId));
    }

    @RequestMapping("/edit")
    @ResponseBody
    public ModelAndView edit(GoodsSafeguardAdministrationVo goodsSafeguardAdministrationVo){
        ModelAndView andView = new ModelAndView();
        System.out.println("======================================1_)))))************");
        System.out.println("goodsSafeguardAdministrationVo = " + goodsSafeguardAdministrationVo);
        andView.setViewName("/goods/safeguard_administration/goods_safeguard_administration_edit");
        andView.addObject("goodsSafeguardAdministrationVo",goodsSafeguardAdministrationVo);
        return andView;
    }


    @PostMapping("/update")
    @ResponseBody
    public ResponseVo update(@RequestParam(value = "gssId" ,required = false,defaultValue = "")int gssId,
                      @RequestParam(value = "safeguardId" ,required = false,defaultValue = "")int safeguardId){
            ResponseVo responseVo = null;
            int count = goodsSafeguardAdministrationService.update(gssId,safeguardId);
            if (count>0){
                responseVo = new ResponseVo("200","修改成功","修改成功");
            }else {
                responseVo = new ResponseVo("500","修改失败","修改失败");
            }
        return responseVo;
    }

    @PostMapping("/insert")
    @ResponseBody
    public ResponseVo insert(@RequestParam(value = "skuId" ,required = false,defaultValue = "")int skuId,
                             @RequestParam(value = "safeguardId" ,required = false,defaultValue = "")int safeguardId){
        ResponseVo responseVo = null;
        System.out.println("skuId = " + skuId + ", safeguardId = " + safeguardId);
        int count = goodsSafeguardAdministrationService.insert(skuId,safeguardId);
        if (count>0){
            responseVo = new ResponseVo("200","添加成功","添加成功");
        }else {
            responseVo = new ResponseVo("500","添加失败","添加失败");
        }
        return responseVo;
    }





}
