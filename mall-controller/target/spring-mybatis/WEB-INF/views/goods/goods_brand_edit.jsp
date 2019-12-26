<%--
  Created by IntelliJ IDEA.
  User: 黑夜
  Date: 2019/12/9
  Time: 23:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/static/css/font.css">
    <link rel="stylesheet" href="/static/css/xadmin.css">
    <link rel="stylesheet" href="/static/lib/layui/css/layui.css">
    <script src="/static/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/static/js/xadmin.js"></script>
    <script type="text/javascript" src="/static/js/jquery.min.js"></script>
</head>
<%--<body>--%>
<%--<h1>${goodsBrand.mgs}</h1>--%>
<%--<form  id="brandForm" method="post" enctype="multipart/form-data">--%>
<%--&lt;%&ndash;修改品牌id&ndash;%&gt;--%>
<%--    <input type="hidden" id="oldImage" name="id" value="${goodsBrand.id}"/>--%>
<%--    品牌名称:<input type="text" name="brandName" id="brandName" value="${goodsBrand.brandName}"/></br>--%>
<%--    logo:<img src="${goodsBrand.imgPath}" width="50" height="50"></br>--%>
<%--    <input type="file"  id="myfile" name="myfile" />--%>
<%--    <input type="button" id="SubmitBtn" value="${goodsBrand.mgs}" />--%>
<%--</form>--%>
<h1>${goodsBrand.mgs}</h1>
    <%--修改品牌id--%>
<form class="layui-form" id="brandForm" method="post" enctype="multipart/form-data">
    <input type="hidden" id="oldImage" name="id" value="${goodsBrand.id}"/>
    <div class="layui-form-item">
        <label class="layui-form-label">品牌名称</label>
        <div class="layui-input-inline">
            <input type="text"  name="brandName" id="brandName"  required lay-verify="required" placeholder="请输入品牌名称" autocomplete="off" class="layui-input" value="${goodsBrand.brandName}">
        </div>

    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">logo</label>
        <img src="${goodsBrand.imgPath}" width="50" height="50"></br>
        <input type="file"  id="myfile" name="myfile" />
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <input type="button" id="SubmitBtn" value="${goodsBrand.mgs}"  class="layui-btn"/>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            <button type="button" id="btn-return" class="layui-btn layui-btn-primary">返回</button>
        </div>
    </div>
</form>

<script type="text/javascript">

    $("#btn-return").click(function () {
        location.href = "/goods/brand/index";
    })

    $("#SubmitBtn").click(function () {
        var formDate = new FormData();
        if ($("#myfile").get(0).files[0]!=null){
            formDate.append("myfile",$("#myfile").get(0).files[0])
        }
        formDate.append("brandName",$("#brandName").val())
        formDate.append("id",$("#oldImage").val())
        var state_selection = $(this).val();
        if (state_selection=='添加'){
            $.ajax({
                url:"/goods/brand/insert"
                , type:"post"
                ,dataType:"json"
                //告诉jQuery不要去处理发送的数据。默认值true，默认情况下，以对象形式上传的数据，将被转换为查询字符串
                ,processData: false
                //告诉jQuery不要去设置Content-Type请求头。form表单的默认值为false，而FormData的默认值为multipart/form-data,所以不用画蛇添足的去修改请求头
                ,contentType: false
                ,data:formDate,
                success:function (result) {
                    if (result.code=="200"){
                        layer.msg("添加成功",{icon:1,time:1000})
                        location.href = "/goods/brand/index";
                    }else{
                        alert('失败');
                    }
                }
            });
        }else {

            $.ajax({
                url:"/goods/brand/update"
                , type:"post"
                ,dataType:"json"
                //告诉jQuery不要去处理发送的数据。默认值true，默认情况下，以对象形式上传的数据，将被转换为查询字符串
                ,processData: false
                //告诉jQuery不要去设置Content-Type请求头。form表单的默认值为false，而FormData的默认值为multipart/form-data,所以不用画蛇添足的去修改请求头
                ,contentType: false
                ,data:formDate,
                success:function (result) {
                    if (result.code=="200"){
                        layer.msg("修改成功",{icon:1,time:1000})
                        location.href = "/goods/brand/index";
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
