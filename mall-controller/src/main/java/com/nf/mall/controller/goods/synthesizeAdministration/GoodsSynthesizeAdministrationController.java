package com.nf.mall.controller.goods.synthesizeAdministration;

import com.nf.mall.controller.BaseController;
import com.nf.mall.controller.goods.synthesizeAdministration.vo.GoodsCategoryAndBrand;
import com.nf.mall.controller.goods.synthesizeAdministration.vo.GoodsVo;
import com.nf.mall.entity.goods.GoodsBrand;
import com.nf.mall.entity.goods.GoodsCategory;
import com.nf.mall.entity.goods.synthesizeAdministration.Goods;
import com.nf.mall.service.goods.GoodsBrandService;
import com.nf.mall.service.goods.GoodsCategoryService;
import com.nf.mall.service.goods.synthesizeAdministration.GoodsSynthesizeAdministrationService;
import com.nf.mall.vo.GoodsBrandVo;
import com.nf.mall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/goods/synthesize/administration/add")
public class GoodsSynthesizeAdministrationController extends BaseController {

    @Autowired
    private GoodsCategoryService goodsCategoryService;
    @Autowired
    private GoodsBrandService goodsBrandService;

    @Autowired
    private GoodsSynthesizeAdministrationService goodsSynthesizeAdministrationService;

    @RequestMapping("/index")
    public ModelAndView index(String mgs){
        ModelAndView andView = new ModelAndView();
        andView.addObject("mgs",mgs);
        andView.setViewName("/goods/synthesizeAdministration/synthesize_administration");
        return andView;
    }


    @GetMapping("/query")
    @ResponseBody
    public GoodsCategoryAndBrand query(){
        return goodsCategoryAndBrand();
    }
    public GoodsCategoryAndBrand goodsCategoryAndBrand(){
        List<GoodsCategory> goodsCategories = goodsCategoryService.getAll();
        List<GoodsBrand> goodsBrands = goodsBrandService.getAll();
        GoodsCategoryAndBrand goodsCategoryAndBrand = new GoodsCategoryAndBrand(goodsCategories,goodsBrands);
        return goodsCategoryAndBrand;
    }


    @PostMapping("/insert")
    @ResponseBody
    public ResponseVo insert(@Valid GoodsVo goodsVo, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, MultipartFile myFile){

        System.out.println("goodsVo===@@$$@$@%$46"+goodsVo);
        ResponseVo responseVo = null;
        Goods goods = new Goods();
        BeanUtils.copyProperties(goodsVo,goods);
        //图片的完整路径
        String pathName = generateFileName(myFile);
        System.out.println("88888888********************pathName = " + pathName);
        //这个是判断校验的
        if(bindingResult.hasErrors()){
            responseVo = new ResponseVo("500","添加失败",bindingResult.getFieldErrors());
        }else {
            //把文件放到指定路径
            putTheFile(pathName,myFile);
            //放到指定路径之后，添加信息
            redirectAttributes.addFlashAttribute("goods",new Goods());
            goods.setSkuImgPath(pathName);
            goodsSynthesizeAdministrationService.insert(goods);
            responseVo = new ResponseVo("200","添加成功","添加成功");
        }
        return responseVo;
    }

}
