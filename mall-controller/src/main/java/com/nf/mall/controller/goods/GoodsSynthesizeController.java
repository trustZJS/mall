package com.nf.mall.controller.goods;

import com.github.pagehelper.PageInfo;
import com.nf.mall.controller.BaseController;
import com.nf.mall.entity.goods.GoodsBrand;
import com.nf.mall.entity.goods.GoodsCategory;
import com.nf.mall.entity.goods.GoodsSpuAndSpu;
import com.nf.mall.entity.goods.synthesizeAdministration.Goods;
import com.nf.mall.service.goods.GoodsBrandService;
import com.nf.mall.service.goods.GoodsCategoryService;
import com.nf.mall.service.goods.GoodsSpuAndSpuService;
import com.nf.mall.service.goods.synthesizeAdministration.GoodsSynthesizeAdministrationService;
import com.nf.mall.vo.GoodsSpuAndSkuVo;
import com.nf.mall.vo.GoodsSynthesizeVo;
import com.nf.mall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/goods/synthesize")
public class GoodsSynthesizeController extends BaseController {

    @Autowired
    private GoodsSpuAndSpuService goodsSpuAndSpuService;
    @Autowired
    private GoodsCategoryService goodsCategoryService;
    @Autowired
    private GoodsBrandService goodsBrandService;

    @Autowired
    private GoodsSynthesizeAdministrationService goodsSynthesizeAdministrationService;

    @RequestMapping("/index")
    public String index(){
        return "/goods/goods_synthesize";
    }

    @RequestMapping("/query")
    @ResponseBody
    public GoodsSynthesizeVo query(@RequestParam(value = "pageNum" ,required = false,defaultValue = "1") int pageNum,
                          @RequestParam(value = "pageSize" ,required = false,defaultValue = "8")int pageSize,
                          @RequestParam(value = "categoryId" ,required = false,defaultValue = "")  int categoryId,
                           @RequestParam(value = "brandId" ,required = false,defaultValue = "")int brandId,
                           @RequestParam(value = "spuId" ,required = false,defaultValue = "") int spuId){

        GoodsSynthesizeVo goodsSynthesizeVo = goodsSynthesizeVo(pageNum,pageSize,categoryId,brandId,spuId);
        System.out.println(goodsSynthesizeVo);
        return goodsSynthesizeVo;
    }
    //这个类是把所有信息查询放到 总览这个类里面的
    public GoodsSynthesizeVo goodsSynthesizeVo(int pageNum,int pageSize,int categoryId, int brandId, int spuId){
        List<GoodsBrand> goodsBrands = goodsBrandService.getAll();
        List<GoodsCategory> goodsCategories = goodsCategoryService.getAll();
        List<GoodsSpuAndSpu> goodsSpuAndSpuList = goodsSpuAndSpuService.getSpuAndSkuPage(pageNum,pageSize,categoryId,brandId,spuId);
        PageInfo pageInfo = new PageInfo(goodsSpuAndSpuList,3);
        GoodsSynthesizeVo goodsSynthesizeVo = new GoodsSynthesizeVo(goodsBrands,goodsCategories,pageInfo);
        return goodsSynthesizeVo;
    }


    @GetMapping("/delete")
    @ResponseBody
    public ResponseVo delete(@RequestParam(value = "spuId" ,required = false,defaultValue = "1") int spuId,
                             @RequestParam(value = "skuId" ,required = false,defaultValue = "1") int skuId){
        System.out.println("spuId = " + spuId + ", skuId = " + skuId);
        boolean bool = goodsSynthesizeAdministrationService.delete(spuId,skuId);
        ResponseVo responseVo = null;
        if (bool){
            responseVo = new ResponseVo("200","删除成功","删除成功");
        }else {
            responseVo = new ResponseVo("500","删除sb","删除sb");
        }
        return responseVo;
    }


    @RequestMapping("/edit")
    @ResponseBody
    public ModelAndView edit(@RequestParam(value = "spuId" ,required = false,defaultValue = "1") int spuId,
                             @RequestParam(value = "skuId" ,required = false,defaultValue = "1") int skuId){

        ModelAndView andView = new ModelAndView();
        andView.addObject("spuId",spuId);
        List<Goods> goods = goodsSynthesizeAdministrationService.queryEdit(spuId);
        System.out.println("++++++++++++++======================goods = " + goods);
        andView.addObject("goods",goods);
        List<GoodsBrand> goodsBrands = goodsBrandService.getAll();
        System.out.println("4545s333333w)))))))))))))0goodsBrands = " + goodsBrands);
        andView.addObject("goodsBrands",goodsBrands);
        List<GoodsCategory> goodsCategories = goodsCategoryService.getAll();
        System.out.println("5#################554^^^^^^^goodsCategories = " + goodsCategories);
        andView.addObject("goodsCategories",goodsCategories);
        andView.setViewName("/goods/synthesizeAdministration/synthesize_edit");
        return andView;
    }



}
