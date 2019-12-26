<%--
  Created by IntelliJ IDEA.
  User: 黑夜
  Date: 2019/12/15
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>增值编辑</title>
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
    <legend id="state_selection">增值${goodsSafeguardVo.mgs}</legend>
</fieldset>
<form class="layui-form layui-form-pane"  lay-filter="example" id="userForm" >

    <div class="layui-form-item">
        <label class="layui-form-label">增值名称</label>
        <div class="layui-input-inline">
            <input type="text" name="safeguardName" lay-verify="required" placeholder="请输入增值名称" autocomplete="off" class="layui-input" value="${goodsSafeguardVo.safeguardName}">
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-input-inline">
            <input type="hidden" name="id" lay-verify="required"  autocomplete="off" class="layui-input" value="${goodsSafeguardVo.id}">
        </div>
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label">增值价格</label>
        <div class="layui-input-inline">
            <input type="text" name="price" lay-verify="required" placeholder="请输入增值价格" autocomplete="off" class="layui-input" value="${goodsSafeguardVo.price}">
        </div>
    </div>



    <div class="layui-form-item">
        <input type="button" id="btn-insert" value="${goodsSafeguardVo.mgs}"  class="layui-btn"/>
        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        <button type="button" id="btn-return" class="layui-btn layui-btn-primary">返回</button>
    </div>
</form>


<script type="text/javascript">

    $("#btn-return").click(function () {
        location.href = "/goods/safeguard/index";
    })

    $("#btn-insert").click(function () {
        var state_selection = $(this).val();
        if (state_selection=='添加'){
            $.ajax({
                url:"/goods/safeguard/insert"
                , type:"post"
                ,dataType:"json"
                ,data:$("#userForm").serialize(),
                success:function (result) {

                    if (result.code=="200"){
                        layer.msg("添加成功",{icon:1,time:1000})
                        location.href = "/goods/safeguard/index";
                    }else{
                        alert('失败');
                    }
                }
            });
        }else {
            $.ajax({
                url:"/goods/safeguard/update"
                , type:"post"
                ,dataType:"json"
                ,data:$("#userForm").serialize(),
                success:function (result) {

                    if (result.code=="200"){
                        layer.msg("修改成功",{icon:1,time:1000})
                        location.href = "/goods/safeguard/index";
                    }else{
                        alert('修改失败');
                    }
                }
            });
        }
    });

</script>

</body>
</html>
