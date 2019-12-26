package com.nf.mall.controller;

import com.nf.mall.vo.ResponseVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public abstract class BaseController {
    public static final String FILE_DIRECTORY = "C:\\Users\\黑夜\\Desktop\\mall-parent\\mall-controller\\src\\main\\resources";

    public static final String FILE_NAME="/static/images/";

    //这个方法是用来拼接完整路径的 ，然后返回给他
    public String generateFileName(MultipartFile myfile) {

        String filename = myfile.getOriginalFilename();
        if(myfile.getSize()==0||filename==null) {
            return "";
        }
        //获取后缀名
        String fileExt = filename.substring(filename.lastIndexOf("."));
        //文件完整名称
        String name = UUID.randomUUID().toString()+fileExt;
        //拼接完整路径
        String pathName=  FILE_NAME+name;
        //这个是图片访问路径
        System.out.println("图片完整路径"+pathName);
        return pathName;
    }

    public void putTheFile(String pathName,MultipartFile myfile){
        System.out.println("=======================");
        pathName = pathName.replaceAll("/","\\\\");
        System.out.println(pathName);
        String full=FILE_DIRECTORY+pathName;
        System.out.println("完整路径full:"+full);
        File file = new File(full);
        try {
            myfile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //删除的
    public ResponseVo deleteVo(int count){
        ResponseVo responseVo = null;
        if (count>0){
            responseVo = new ResponseVo("200","删除成功","删除成功");
        }else {
            responseVo = new ResponseVo("500","删除失败","删除失败");
        }
        return responseVo;
    }




}
