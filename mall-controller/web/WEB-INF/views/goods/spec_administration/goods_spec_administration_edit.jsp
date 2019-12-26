<%--
  Created by IntelliJ IDEA.
  User: 黑夜
  Date: 2019/12/21
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>商品规格值管理</title>

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

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
    <legend id="state_selection">${goodsSpecAdministrationVo.mgs}</legend>
</fieldset>


<%--var id = $("#specValue_id").val();--%>
<%--规格名称--%>
<input type="hidden" id="specName"  value="${goodsSpecAdministrationVo.specName}">
<%--规格值--%>
<input type="hidden" id="specValue"  value="${goodsSpecAdministrationVo.specValue}">
<input type="hidden" id="gssvId"  value="${goodsSpecAdministrationVo.gssvId}">
<input type="hidden" id="gssId"  value="${goodsSpecAdministrationVo.gssId}">
<input type="hidden" id="gsId"  value="${goodsSpecAdministrationVo.gsId}">

<input type="hidden" id="spuId"  value="${goodsSpecAdministrationVo.spuId}">
<input type="hidden" id="skuId"  value="${goodsSpecAdministrationVo.skuId}">



<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <label class="layui-label">商品名称:${goodsSpecAdministrationVo.goodsName}</label>
                <div class="layui-card-body ">

                    <label class="layui-label">版本选择:</label>
                    <div id="specHtml" >

                    </div>

                    <label class="layui-label">规格值选择:</label>
                    <div id="specValueHtml">

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>




<%--这个是修改和添加的按--%>
<input type="button" id="btn-edit" value="${goodsSpecAdministrationVo.mgs}"  class="layui-btn"/>
<button type="button" id="btn-return" class="layui-btn layui-btn-primary">返回</button>

<script type="text/javascript">
    // ======这是用Ajax 来显示的
    $(function () {
        var specName =  $("#specName").val();
        var specValue =  $("#specValue").val();
        var gsId = $("#gsId").val();

        getIndex(specName,specValue,gsId);
        function getIndex(specName,specValue,specId){
            $.ajax({
                type: "POST",
                url: "/goods/spec/administration/editQuery",
                data: {specId:specId},
                dataType: "json",
                success: function(result){
                    var specHtml = "";
                    specHtml+=" <select lay-filter=\"aihao\" id=\"select-spec\">";
                    specHtml += "<option value=\"0\">请选择</option>";
                    $.each(result.goodsSpecs,function (index,val) {
                        if (val.specName==specName){
                            specHtml+="<option selected value="+val.id+">";
                            specHtml+=val.specName;
                            specHtml+="</option>";
                        }else {
                            specHtml+="<option value="+val.id+">";
                            specHtml+=val.specName;
                            specHtml+="</option>";
                        }
                    })
                    specHtml+="</select>";
                    $("#specHtml").html(specHtml);

                    var specValueHtml = "";
                    specValueHtml+=" <select  lay-filter=\"aihao\" id=\"select-specValue\">";
                    specValueHtml += "<option value=\"0\">请选择</option>";
                    $.each(result.goodsSpecValues,function (index,val) {
                        if (val.specValue==specValue){
                            specValueHtml+="<option selected value="+val.id+">";
                            specValueHtml+=val.specValue;
                            specValueHtml+="</option>";
                        }else {
                            specValueHtml+="<option value="+val.id+">";
                            specValueHtml+=val.specValue;
                            specValueHtml+="</option>";
                        }
                    })
                    specValueHtml+="</select>";
                    $("#specValueHtml").html(specValueHtml);
                    $("#select-spec").change(function () {
                        specId = $(this).find("option:selected").val();
                        specName =$(this).find("option:selected").text();
                        alert(specName);
                        getIndex(specName,specValue,specId);
                    })
                }
            })
        }

    })





    $("#btn-edit").click(function () {
        var state_selection = $(this).val();
        var gssvId = $("#gssvId").val();
        var gssId = $("#gssId").val();
        var specId = $("#select-spec").find("option:selected").val();
        var specValueId = $("#select-specValue").find("option:selected").val();

        var spuId = $("#spuId").val();
        var skuId= $("#skuId").val();

        if (state_selection=='添加'){
            $.ajax({
                url:"/goods/spec/administration/insert"
                , type:"post"
                ,dataType:"json"
                ,data: {specId:specId,specValueId:specValueId,spuId:spuId,skuId:skuId},
                success:function (result) {
                    if (result.code=="200"){
                        layer.msg("添加成功",{icon:1,time:1000})
                        location.href = "/goods/spec/administration/index";
                    }else{
                        alert('失败');
                    }
                }
            });
        }else {

            $.ajax({
                url:"/goods/spec/administration/update"
                , type:"post"
                ,dataType:"json"
                ,data:{gssvId:gssvId,gssId:gssId,specId:specId,specValueId:specValueId},
                success:function (result) {
                    if (result.code=="200"){
                        layer.msg("修改成功",{icon:1,time:1000})
                        location.href = "/goods/spec/administration/index";
                    }else{
                        alert('修改失败');
                    }
                }
            });
        }
    });
    //这是返回界面的
    $("#btn-return").click(function () {
        location.href = "/goods/spec/administration/index";
    })
</script>

</body>
</html>
