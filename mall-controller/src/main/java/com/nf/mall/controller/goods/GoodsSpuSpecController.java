package com.nf.mall.controller.goods;

import com.github.pagehelper.PageInfo;
import com.nf.mall.controller.BaseController;
import com.nf.mall.dao.goods.GoodsSpecDao;
import com.nf.mall.dao.goods.GoodsSpuSpecDao;
import com.nf.mall.entity.goods.GoodsSpec;
import com.nf.mall.entity.goods.GoodsSpecValue;
import com.nf.mall.entity.goods.GoodsSpuSpec;
import com.nf.mall.service.goods.GoodsSpecService;
import com.nf.mall.service.goods.GoodsSpuSpecService;
import com.nf.mall.vo.GoodsSpecValueVo;
import com.nf.mall.vo.GoodsSpuSpecAndSpecVo;
import com.nf.mall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/goods/spu/spec")
public class GoodsSpuSpecController extends BaseController {
   @Autowired
   private GoodsSpuSpecService goodsSpuSpecService;
   @Autowired
   private GoodsSpecService goodsSpecService;

    @RequestMapping("/index")
    public String index(){
        return "/goods/goods_spu_spec";
    }


    @RequestMapping("/query")
    @ResponseBody
    public GoodsSpuSpecAndSpecVo query(@RequestParam(value = "pageNum" ,required = false,defaultValue = "1") int pageNum,
                                       @RequestParam(value = "pageSize" ,required = false,defaultValue = "8")int pageSize,
                                       @RequestParam(value = "specId" ,required = false,defaultValue = "")  int specId){
        return goodsSpuSpecAndSpecVo(pageNum,pageSize,specId);
    }

    @GetMapping("/delete")
    @ResponseBody
    public ResponseVo delete(int id){
        return deleteVo(goodsSpuSpecService.delete(id));
    }

    @RequestMapping("/edit")
    @ResponseBody
    public ModelAndView edit(GoodsSpuSpec goodsSpuSpec){
        ModelAndView andView = new ModelAndView();
        andView.addObject("goodsSpuSpec",goodsSpuSpec);
        List<GoodsSpec> goodsSpecs = goodsSpecService.getAll();
        andView.addObject("goodsSpecs",goodsSpecs);
        List<GoodsSpuSpec> goodsSpuSpecs = goodsSpuSpecService.getGoodsName();
        andView.addObject("goodsSpuSpecs",goodsSpuSpecs);
        andView.setViewName("/goods/goods_spu_spec_edit");
        return andView;
    }


    @PostMapping("/insert")
    @ResponseBody
    public ResponseVo insert(int specId,int spuId){
        ResponseVo responseVo = null;
        int count = goodsSpuSpecService.insert(specId,spuId);
        if (count>0){
            responseVo = new ResponseVo("200","添加成功","添加成功");
        }else {
            responseVo = new ResponseVo("500","添加失败","添加失败");
        }
        return responseVo;
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseVo insert(int specId,int spuId,int id){
        ResponseVo responseVo = null;
        int count = goodsSpuSpecService.update(specId,spuId,id);
        if (count>0){
            responseVo = new ResponseVo("200","xg成功","xg成功");
        }else {
            responseVo = new ResponseVo("500","修改失败","修改失败");
        }
        return responseVo;
    }



    public GoodsSpuSpecAndSpecVo goodsSpuSpecAndSpecVo(int pageNum,int pageSize,int specId){
        List<GoodsSpec> goodsSpecs = goodsSpecService.getAll();
        List<GoodsSpuSpec> goodsSpuSpecs = goodsSpuSpecService.getAll(pageNum,pageSize,specId);
        System.out.println(goodsSpuSpecs);
        PageInfo pageInfo = new PageInfo(goodsSpuSpecs,3);
        GoodsSpuSpecAndSpecVo goodsSpuSpecAndSpecVo = new GoodsSpuSpecAndSpecVo(goodsSpecs,pageInfo);
        return goodsSpuSpecAndSpecVo;
    }
}
