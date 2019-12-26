package com.nf.mall.controller.goods;

import com.github.pagehelper.PageInfo;
import com.nf.mall.controller.BaseController;
import com.nf.mall.entity.goods.GoodsCategory;
import com.nf.mall.entity.goods.GoodsSafeguard;
import com.nf.mall.service.goods.GoodsSafeguardService;
import com.nf.mall.vo.GoodsCategoryVo;
import com.nf.mall.vo.GoodsSafeguardVo;
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
@RequestMapping("/goods/safeguard")
public class GoodsSafeguardController extends BaseController {
    @Autowired
    private GoodsSafeguardService goodsSafeguardService;

    @RequestMapping("/index")
    public String list(){
        return "/goods/goods_safeguard";
    }

    @RequestMapping("/query")
    @ResponseBody
    public PageInfo query(@RequestParam(value = "pageNum" ,required = false,defaultValue = "1") int pageNum,
                          @RequestParam(value = "pageSize" ,required = false,defaultValue = "10")int pageSize,
                          @RequestParam(value = "username" ,required = false,defaultValue = "") String username){
        List<GoodsSafeguard> result = goodsSafeguardService.getAll(pageNum,pageSize,username);
        PageInfo pageInfo = new PageInfo(result,3);
        return pageInfo;
    }


    @GetMapping("/delete")
    @ResponseBody
    public ResponseVo delete(int id){
        System.out.println("id="+id);
        return deleteVo(goodsSafeguardService.delete(id));
    }

    @RequestMapping("/add")
    public ModelAndView add(GoodsSafeguardVo goodsSafeguardVo){
        System.out.println("=======================");
        System.out.println(goodsSafeguardVo);
        ModelAndView andView = new ModelAndView();
        andView.setViewName("/goods/goods_safeguard_edit");
        andView.addObject("goodsSafeguardVo",goodsSafeguardVo);
        return andView;
    }

    @RequestMapping("/insert")
    @ResponseBody
    public ResponseVo insert(@Valid GoodsSafeguardVo goodsSafeguardVo, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){
        ResponseVo responseVo = null;
        GoodsSafeguard goodsSafeguard = new GoodsSafeguard();
        BeanUtils.copyProperties(goodsSafeguardVo,goodsSafeguard);
        if(bindingResult.hasErrors()){
            responseVo = new ResponseVo("500","添加失败",bindingResult.getFieldErrors());
        }else {
            redirectAttributes.addFlashAttribute("goodsSafeguard",new GoodsSafeguard());
            goodsSafeguardService.insert(goodsSafeguard);
            responseVo = new ResponseVo("200","添加成功","添加成功");
        }

        return responseVo;
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResponseVo update(@Valid GoodsSafeguardVo goodsSafeguardVo, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){
        ResponseVo responseVo = null;
        GoodsSafeguard goodsSafeguard = new GoodsSafeguard();
        BeanUtils.copyProperties(goodsSafeguardVo,goodsSafeguard);
        if(bindingResult.hasErrors()){
            responseVo = new ResponseVo("500","修改失败",bindingResult.getFieldErrors());
        }else {
            redirectAttributes.addFlashAttribute("goodsSafeguard",new GoodsSafeguard());
            goodsSafeguardService.update(goodsSafeguard);
            responseVo = new ResponseVo("200","修改成功","修改成功");
        }
        return responseVo;
    }


}
