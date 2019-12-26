<%--
  Created by IntelliJ IDEA.
  User: 黑夜
  Date: 2019/12/25
  Time: 23:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>商品中心添加</title>

    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/static/lib/layui/css/layui.css" media="all">
    <script src="/static/lib/layui/layui.js" charset="utf-8"></script>
    <script src="/static/js/jquery.min.js" charset="utf-8"></script>
    <link rel="stylesheet" href="/static/css/font.css">
    <link rel="stylesheet" href="/static/css/xadmin.css">
    <script type="text/javascript" src="/static/js/xadmin.js"></script>
</head>
<body>

<!--刷新的 -->
<div class="x-nav">
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
</div>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
    <legend>商品添加</legend>
</fieldset>
<form class="layui-form layui-form-pane" action="" lay-filter="example" >
    <div class="layui-form-item">
        <label class="layui-form-label">商品编号</label>
        <div class="layui-input-inline">
            <input type="text" name="spuNo" id="spuNo"  lay-verify="required" placeholder="请输入编号" autocomplete="off" class="layui-input">
        </div>
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label">商品名称</label>
        <div class="layui-input-inline">
            <input type="text" name="goodsName" id="goodsName"  lay-verify="required" placeholder="请输入商品名称" autocomplete="off" class="layui-input">
        </div>
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label">进货价格</label>
        <div class="layui-input-inline">
            <input type="text" name="lowPrice" id="lowPrice" lay-verify="required" placeholder="请输入进货价格" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">售卖价格</label>
        <div class="layui-input-inline">
            <input type="text" name="price" id="price"  lay-verify="required" placeholder="请输入售卖价格" autocomplete="off" class="layui-input">
        </div>
    </div>



    <div class="layui-form-item">
        <label class="layui-form-label">商品数量</label>
        <div class="layui-input-inline">
            <input type="text" name="stock" id="stock" lay-verify="required" placeholder="请输入商品数量" autocomplete="off" class="layui-input">
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-upload">
            <div class="layui-upload-list">
                <input type="file" name="myFile" id="myFile" multiple="multiple" /><br>
                <img src="" id="img0" style="width: 10rem;height: 10rem;">
            </div>
        </div>
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label">选择品牌</label>
        <div class="layui-input-inline" id="brandHtml">


        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">选择类型</label>
        <div class="layui-input-inline" id="categoryHtml">

        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">标题</label>
        <div class="layui-input-block">
            <input type="text" name="skuTitle" id="skuTitle"  autocomplete="off" placeholder="请输入售卖标题" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <input type="button" id="btn-edit" value="添加"  class="layui-btn"/>
        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
</form>

<script>
    $(function () {
        getIndex();
        function getIndex(){
            $.ajax({
                type: "GET",
                url: "/goods/synthesize/administration/add/query",
                dataType: "json",
                success: function(result){
                    //品牌下拉框
                    var brandHtml = "";
                    brandHtml+=" <select  id=\"brandId\">";
                    brandHtml += "<option selected value=\"0\">请选择</option>";
                    $.each(result.goodsBrands,function (index,val) {
                        brandHtml+="<option  value="+val.id+">";
                        brandHtml+=val.brandName;
                        brandHtml+="</option>";
                    })
                    brandHtml+="</select>";
                    $("#brandHtml").html(brandHtml);

                    //类型下拉框
                    var categoryHtml = "";
                    categoryHtml+=" <select name=\"categoryName\" lay-filter=\"aihao\" id=\"categoryId\">";
                    categoryHtml+="<option selected value=\"0\">请选择</option>";
                    $.each(result.goodsCategories,function (index,val) {
                        categoryHtml+="<option  value="+val.id+" >";
                        categoryHtml+=val.categoryName;
                        categoryHtml+="</option>";

                    })
                    categoryHtml+="</select>";
                    $("#categoryHtml").html(categoryHtml);

                }  //这个括号是Ajax返回的结果集括号
            })
        }
    })

    //这是点击添加的时候,添加的
    $("#btn-edit").click(function () {
        var formDate = new FormData();
        var brandId = $("#brandId").find("option:selected").val();
        var categoryId = $("#categoryId").find("option:selected").val();
        formDate.append("spuNo",$("#spuNo").val());
        formDate.append("goodsName",$("#goodsName").val());
        formDate.append("lowPrice",$("#lowPrice").val());
        formDate.append("price",$("#price").val());
        formDate.append("stock",$("#stock").val());
        formDate.append("skuTitle",$("#skuTitle").val());
        formDate.append("brandId",brandId);
        formDate.append("categoryId",categoryId);
        //商品图片路径：skuImgPath  图片路径在后台弄  这里是保存图片编码
        formDate.append("myFile",$("#myFile").get(0).files[0]);
        $.ajax({
            url:"/goods/synthesize/administration/add/insert"
            , type:"post"
            ,dataType:"json"
            //告诉jQuery不要去处理发送的数据。默认值true，默认情况下，以对象形式上传的数据，将被转换为查询字符串
            ,processData: false
            //告诉jQuery不要去设置Content-Type请求头。form表单的默认值为false，而FormData的默认值为multipart/form-data,所以不用画蛇添足的去修改请求头
            ,contentType: false
            ,data: formDate,
            success:function (result) {
                if (result.code=="200"){
                    layer.msg("添加成功",{icon:1,time:1000})
                }else{
                    alert('失败');
                }
            }
        });
    });








    //下面的不用管
    $("#myFile").change(function(){
        var objUrl = getObjectURL(this.files[0]) ;//获取文件信息
        console.log("objUrl = "+objUrl);
        if (objUrl) {
            $("#img0").attr("src", objUrl);
        }
    }) ;
    function getObjectURL(file) {
        var url = null;
        if (window.createObjectURL!=undefined) {
            url = window.createObjectURL(file) ;
        } else if (window.URL!=undefined) { // mozilla(firefox)
            url = window.URL.createObjectURL(file) ;
        } else if (window.webkitURL!=undefined) { // webkit or chrome
            url = window.webkitURL.createObjectURL(file) ;
        }
        return url ;
    }
    layui.use(['form', 'layedit', 'laydate'], function(){

    });
</script>



</body>
</html>
