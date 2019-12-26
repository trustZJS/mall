package com.nf.mall.controller.goods;

import com.github.pagehelper.PageInfo;
import com.nf.mall.controller.BaseController;
import com.nf.mall.entity.goods.GoodsBrand;

import com.nf.mall.service.goods.GoodsBrandService;
import com.nf.mall.vo.GoodsBrandVo;
import com.nf.mall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.validation.Valid;
import java.io.File;

import java.util.List;

//品牌表操作
@Controller
@RequestMapping("/goods/brand")
public class GoodsBrandController extends BaseController {


    @Autowired
    private GoodsBrandService goodsBrandService;

    @RequestMapping("/index")
    public String index(){
        return "/goods/goods_brand";
    }


    @RequestMapping("/query")
    @ResponseBody
    public PageInfo query(@RequestParam(value = "pageNum" ,required = false,defaultValue = "1") int pageNum,
                          @RequestParam(value = "pageSize" ,required = false,defaultValue = "10")int pageSize,
                          @RequestParam(value = "username" ,required = false,defaultValue = "") String username){
        List<GoodsBrand> result = goodsBrandService.getSearch(pageNum,pageSize,username);
        PageInfo pageInfo = new PageInfo(result,3);
        System.out.println(pageInfo);
        return pageInfo;
    }
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseVo delete(int id,String imgPath){
        int count = goodsBrandService.delete(id);
        ResponseVo responseVo = null;
        if (count>0){
            System.out.println("开始执行，图片路径为"+imgPath);
            //要转换字符
            imgPath = imgPath.replaceAll("/","\\\\");
            //完整路径
            String PathName =FILE_DIRECTORY+ imgPath;
            System.out.println("完整路径"+PathName);
            File file = new File(PathName);
            //判断文件是否存在
            if (file.exists() == true){
                System.out.println("图片存在，可执行删除操作");
                Boolean flag = file.delete();
                if (flag){
                    System.out.println("成功删除图片"+file.getName());
                }else {
                    System.out.println("删除失败");
                }
            }else {
                System.out.println("图片不存在，终止操作");
            }
            responseVo = new ResponseVo("200","删除成功","删除成功");
        }else {
            responseVo = new ResponseVo("500","删除失败","删除失败");
        }
        return responseVo;
    }

    //这个是跳到修改和添加的界面
    @RequestMapping("/add")
    public ModelAndView add(GoodsBrandVo goodsBrandVo){
        ModelAndView andView = new ModelAndView();

        System.out.println("=============:"+goodsBrandVo);
        andView.addObject("goodsBrand",goodsBrandVo);
        andView.setViewName("/goods/goods_brand_edit");
        return andView;
    }
    @PostMapping("/insert")
    @ResponseBody
    public ResponseVo insert(@Valid GoodsBrandVo goodsBrandVo, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,MultipartFile myfile){
        ResponseVo responseVo = null;
        GoodsBrand goodsBrand = new GoodsBrand();
        BeanUtils.copyProperties(goodsBrandVo,goodsBrand);
        //图片的完整路径
        String pathName = generateFileName(myfile);
        //这个是判断校验的
        if(bindingResult.hasErrors()){
            responseVo = new ResponseVo("500","添加失败",bindingResult.getFieldErrors());
        }else {
            //把文件放到指定路径
            putTheFile(pathName,myfile);
            //放到指定路径之后，添加信息
            redirectAttributes.addFlashAttribute("goodsBrand",new GoodsBrand());
            goodsBrand.setImgPath(pathName);
            goodsBrandService.insert(goodsBrand);
            responseVo = new ResponseVo("200","添加成功","添加成功");
        }
        return responseVo;
    }



    @PostMapping("/update")
    @ResponseBody
    public ResponseVo update(@Valid GoodsBrandVo goodsBrandVo, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,MultipartFile myfile){
        ResponseVo responseVo = null;
        GoodsBrand goodsBrand = new GoodsBrand();
        BeanUtils.copyProperties(goodsBrandVo,goodsBrand);
        //图片的完整路径
        String pathName = generateFileName(myfile);
        //这个是判断校验的
        if(bindingResult.hasErrors()){
            responseVo = new ResponseVo("500","修改失败",bindingResult.getFieldErrors());
        }else {
            //把文件放到指定路径
            putTheFile(pathName,myfile);
            //放到指定路径之后，添加信息
            redirectAttributes.addFlashAttribute("goodsBrand",new GoodsBrand());
            goodsBrand.setImgPath(pathName);
            goodsBrandService.update(goodsBrand);
            //都修改完之后 ，还有进行之前的图片给删除了，这个操作，在之后优化代码，在做
            responseVo = new ResponseVo("200","修改成功","修改成功");
        }
        return responseVo;
    }


}
