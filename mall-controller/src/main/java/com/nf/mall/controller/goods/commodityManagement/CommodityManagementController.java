package com.nf.mall.controller.goods.commodityManagement;

import com.github.pagehelper.PageInfo;
import com.nf.mall.controller.BaseController;
import com.nf.mall.controller.goods.commodityManagement.vo.CommodityManagementVo;
import com.nf.mall.controller.goods.synthesizeAdministration.vo.GoodsVo;
import com.nf.mall.entity.goods.GoodsBrand;
import com.nf.mall.entity.goods.GoodsCategory;
import com.nf.mall.entity.goods.commodityManagement.CommodityManagementEntity;
import com.nf.mall.entity.goods.synthesizeAdministration.Goods;
import com.nf.mall.service.goods.GoodsBrandService;
import com.nf.mall.service.goods.GoodsCategoryService;
import com.nf.mall.service.goods.commodityManagement.CommodityManagementService;
import com.nf.mall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/Commodity/Management")
public class CommodityManagementController extends BaseController {

    @Autowired
    private CommodityManagementService commodityManagementService;
    @Autowired
    private GoodsCategoryService goodsCategoryService;
    @Autowired
    private GoodsBrandService goodsBrandService;

    @RequestMapping("/index")
    public String index(){
        return "/goods/commodity_management/goods_commodity_management";
    }


    @RequestMapping("/query")
    @ResponseBody
    public CommodityManagementVo query(@RequestParam(value = "pageNum" ,required = false,defaultValue = "1") int pageNum,
                                       @RequestParam(value = "pageSize" ,required = false,defaultValue = "8")int pageSize,
                                       @RequestParam(value = "categoryId" ,required = false,defaultValue = "")int categoryId,
                                       @RequestParam(value = "brandId" ,required = false,defaultValue = "")int brandId,
                                       @RequestParam(value = "goodsName" ,required = false,defaultValue = "")String goodsName){
        return commodityManagementVo(pageNum,pageSize,categoryId,brandId,goodsName);
    }

    public CommodityManagementVo commodityManagementVo(int pageNum,int pageSize,int categoryId ,int brandId,String goodsName){
        List<GoodsCategory> goodsCategories = goodsCategoryService.getAll();
        List<GoodsBrand> goodsBrands = goodsBrandService.getAll();
        List<CommodityManagementEntity> result = commodityManagementService.getAll(pageNum,pageSize,categoryId,brandId,goodsName);
        PageInfo pageInfo = new PageInfo(result,3);
        CommodityManagementVo commodityManagementVo = new CommodityManagementVo(goodsBrands,goodsCategories,pageInfo);
        return commodityManagementVo;
    }

    @RequestMapping("/insert")
    public String insert(){
        return "/goods/commodity_management/goods_commodity_management_insert";
    }

    @RequestMapping("/edit")
    public ModelAndView update(int spuId,int skuId){
        ModelAndView andView = new ModelAndView();
        System.out.println("spuId = " + spuId + ", skuId = " + skuId);
        List<GoodsCategory> goodsCategories = goodsCategoryService.getAll();
        List<GoodsBrand> goodsBrands = goodsBrandService.getAll();
        CommodityManagementEntity commodityManagementEntity = commodityManagementService.getById(spuId);
        andView.addObject("goodsCategories",goodsCategories);
        andView.addObject("goodsBrands",goodsBrands);
        andView.addObject("commodityManagementEntity",commodityManagementEntity);
        andView.setViewName("/goods/commodity_management/goods_commodity_management_update");
        return andView;
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseVo insert(@Valid CommodityManagementEntity commodityManagementEntity, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, MultipartFile myFile){

        ResponseVo responseVo = null;

        BeanUtils.copyProperties(commodityManagementEntity,commodityManagementEntity);
        //图片的完整路径
        String pathName = generateFileName(myFile);
        System.out.println("88888888********************pathName = " + pathName);
        //这个是判断校验的
        if(bindingResult.hasErrors()){
            responseVo = new ResponseVo("500","修改失败",bindingResult.getFieldErrors());
        }else {
            //把文件放到指定路径
            putTheFile(pathName,myFile);
            //放到指定路径之后，添加信息
            redirectAttributes.addFlashAttribute("commodityManagementEntity",new CommodityManagementEntity());
            commodityManagementEntity.setSkuImgPath(pathName);

            commodityManagementService.update(commodityManagementEntity);
            responseVo = new ResponseVo("200","添加成功","添加成功");
        }
        return responseVo;
    }


}
