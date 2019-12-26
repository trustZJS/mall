<%--
  Created by IntelliJ IDEA.
  User: 黑夜
  Date: 2019/12/22
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>商品增值编辑</title>
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
    <legend id="state_selection">${goodsSafeguardAdministrationVo.mgs}</legend>
</fieldset>


<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
  <legend id="goodsName">商品名称:${goodsSafeguardAdministrationVo.goodsName}</legend>
</fieldset>

<input type="hidden" id="gssId"  value="${goodsSafeguardAdministrationVo.gssId}">
<input type="hidden" id="safeguardName"  value="${goodsSafeguardAdministrationVo.safeguardName}">
<input type="hidden" id="mgs"  value="${goodsSafeguardAdministrationVo.mgs}">
<input type="hidden" id="skuId"  value="${goodsSafeguardAdministrationVo.skuId}">

<%--表--%>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">

                <div class="layui-card-body ">
                        <%-- 模糊搜索的搜索的 --%>
                    <div class="layui-inline layui-show-xs-block">
                        <input type="text" name="username" id="search"  placeholder="搜索" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-inline layui-show-xs-block">
                        <button class="layui-btn"  lay-submit="" lay-filter="sreach" id="bit-list"><i class="layui-icon">&#xe615;</i></button>
                    </div>
                            <div>
                                <button type="button" id="btn-return" class="layui-btn layui-btn-primary">返回</button>
                            </div>

                </div>
                <%--                这个是表的--%>
                <div class="layui-card-body ">
                    <table class="layui-table layui-form">
                        <thead>
                        <tr>
                            <th style="display:none">编号</th>
                            <th>保障名称</th>
                            <th>保障价格</th>
                            <th>选择</th>
                        </thead>
                        <tbody id="tbody">

                        </tbody>
                    </table>
                </div>
                <%--分页的--%>
                <div class="layui-card-body ">
                    <div class="page">
                        <div id="navigatePageNums">

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>




<script type="text/javascript">

    //这是返回界面的
    $("#btn-return").click(function () {
        location.href = "/goods/safeguard/administration/index";
    })


    $(function () {
        var pagination = {};
        var username ='';
        var safeguardName = $("#safeguardName").val();

        getIndex(username,1,safeguardName);

        function getIndex(username,pageNum,safeguardName){
            $.ajax({
                type: "POST",
                url: "/goods/safeguard/query",
                data: {username:username,pageNum:pageNum},
                dataType: "json",
                success: function(result){
                    var html="";
                    $.each(result.list,function (index,val) {
                        if (safeguardName !=val.safeguardName){
                            html+="<tr>";
                            html+="<td style=\"display:none\">"+val.id+"</td>";
                            html+="<td>"+val.safeguardName+"</td>";
                            html+="<td>"+val.price+"</td>"
                            html+="<td  class=\"td-manage\">";
                            html+="<button class=\"layui-btn btn-xs btn-edit\"> <i class=\"layui-icon\">&#xe642;</i>选择</button>";
                            html+= "</td>";
                            html+="</tr>";
                        }else {
                            html+="<tr style='color: #F34743'>";
                            html+="<td style=\"display:none\">"+val.id+"</td>";
                            html+="<td>"+val.safeguardName+"</td>";
                            html+="<td>"+val.price+"</td>"
                            html+="<td  class=\"td-manage\">";
                            html+="<button class=\"layui-btn btn-xs btn-edit\"> <i class=\"layui-icon\">&#xe642;</i>选择</button>";
                            html+= "</td>";
                            html+="</tr>";
                        }
                    })
                    $("#tbody").html(html);
                    pagination =result;
                    var pageNumber ="";
                    pageNumber +="<a id='first'>首页</a>";
                    pageNumber +="<a id='prev'>上一页</a>";
                    $.each(pagination.navigatepageNums,function (index,val) {
                        pageNumber +="<a class='pageNumber'>";
                        pageNumber +=val;
                        pageNumber +="</a>";
                    })
                    pageNumber +="<a id='next'>下一页</a>";
                    pageNumber +="<a id='last'>尾页</a>";

                    $("#navigatePageNums").html(pageNumber);

                    $(".pageNumber").on("click",function () {
                        getIndex(username,$(this).html(),safeguardName);

                    })

                    $("#first").on("click",function () {
                        getIndex(username,1,safeguardName);

                    })

                    $("#prev").on("click",function () {
                        getIndex(username,pagination.prePage,safeguardName);

                    })

                    $("#next").on("click",function () {
                        getIndex(username,pagination.nextPage,safeguardName);


                    })

                    $("#last").on("click",function () {
                        getIndex(username,pagination.pages,safeguardName);


                    })

                    //编辑
                    $(".btn-edit").click(function () {
                        var gssId =$("#gssId").val();
                        var safeguardId = $(this).parent().parent().find("td:eq(0)").html();
                        var mgs = $("#mgs").val();
                        var skuId = $("#skuId").val();

                        if (mgs=='添加'){
                            alert(skuId);
                            $.ajax({
                                url:"/goods/safeguard/administration/insert"
                                , type:"post"
                                ,dataType:"json"
                                ,data: {safeguardId:safeguardId,skuId:skuId},
                                success:function (result) {
                                    if (result.code=="200"){
                                        layer.msg("添加成功",{icon:1,time:1000})
                                        location.href = "/goods/safeguard/administration/index";
                                    }else{
                                        alert('失败');
                                    }
                                }
                            });
                        }else {
                            $.ajax({
                                url:"/goods/safeguard/administration/update"
                                , type:"post"
                                ,dataType:"json"
                                ,data:{safeguardId:safeguardId,gssId:gssId},
                                success:function (result) {
                                    if (result.code=="200"){
                                        layer.msg("修改成功",{icon:1,time:1000})
                                        location.href = "/goods/safeguard/administration/index";
                                    }else{
                                        alert('修改失败');
                                    }
                                }
                            });
                        }
                    });

                }
            })
        }
        //模糊查询
        $("#bit-list").click(function () {
            var username = $("#search").val()
            getIndex(username,1,safeguardName);
        })

    })
</script>

</body>
</html>
