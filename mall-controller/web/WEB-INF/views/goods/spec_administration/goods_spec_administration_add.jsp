<%--
  Created by IntelliJ IDEA.
  User: 黑夜
  Date: 2019/12/22
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>商品规格添加选择</title>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="stylesheet" href="/static/css/font.css">
    <link rel="stylesheet" href="/static/css/xadmin.css">
    <script src="/static/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/static/js/xadmin.js"></script>
    <script type="text/javascript" src="/static/js/jquery.min.js"></script>
</head>
<body>
<!--刷新的 -->
<div class="x-nav">
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
</div>
<input type="hidden" id="state"  value="${state}">

<%--表--%>
<div class="layui-fluid" >

    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">

                <div class="layui-card-body ">
                    <%-- 模糊搜索的搜索的 --%>
                    <div class="layui-inline layui-show-xs-block">
                        <input type="text" id="search"  placeholder="搜索" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-inline layui-show-xs-block">
                        <button class="layui-btn"  lay-submit="" lay-filter="sreach" id="bit-list"><i class="layui-icon">&#xe615;</i></button>
                    </div>

                        <div class="layui-inline layui-show-xs-block">
                            <button class="layui-btn"  lay-submit="" lay-filter="sreach" id="btn-return">返回</button>
                        </div>
                </div>
                <%--                这个是表的--%>

                <div class="layui-card-body ">
                    <table class="layui-table layui-form">
                        <thead>
                        <tr>
                            <th>商品名称</th>
                            <th>商品标题</th>
                            <th style="display:none">spuId</th>
                            <th style="display:none">skuId</th>
                            <th>选择添加规格</th>
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
        var state = $("#state").val();
        alert(state);
        if (state=='规格'){
            location.href = "/goods/spec/administration/index";
        }else {
            location.href = "/goods/safeguard/administration/index";
        }


    })
    $(function () {
        var pagination = {};
        var goodsName ='';
        getIndex(goodsName,1);
        function getIndex(goodsName,pageNum){
            $.ajax({
                type: "POST",
                url: "/goods/spec/administration/addQuery",
                data: {goodsName:goodsName,pageNum:pageNum},
                dataType: "json",
                success: function(result){
                    var html="";
                    $.each(result.list,function (index,val) {

                        html+="<tr>";
                        html+="<td>"+val.goodsName+"</td>";
                        html+="<td>"+val.skuTitle+"</td>";
                        html+="<td style=\"display:none\">"+val.spuId+"</td>";
                        html+="<td style=\"display:none\">"+val.skuId+"</td>";
                        html+="<td  class=\"td-manage\">";
                        html+="<button class=\"layui-btn btn-xs select_id\"> <i class=\"layui-icon\">&#xe642;</i>选择</button>";
                        html+= "</td>";
                        html+="</tr>";
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
                        getIndex(goodsName,$(this).html());
                    })

                    $("#first").on("click",function () {
                        getIndex(goodsName,1)
                    })

                    $("#prev").on("click",function () {
                        getIndex(goodsName,pagination.prePage)
                    })

                    $("#next").on("click",function () {
                        getIndex(goodsName,pagination.nextPage)
                    })

                    $("#last").on("click",function () {
                        getIndex(goodsName,pagination.pages)
                    })


                    //选择 brandUpdate
                    $(".select_id").on("click",function(){
                        var spuId = $(this).parent().parent().find("td:eq(2)").html();
                        var skuId = $(this).parent().parent().find("td:eq(3)").html();
                        var goodsName = $(this).parent().parent().find("td:eq(0)").html();
                        var mgs = '添加';
                        var specName = '';
                        var specValue ='';
                        var gssvId = 0;
                        var gssId = 0;
                        var gsId = 0;
                        var safeguardName = '';
                        var state = $("#state").val();
                        if (state =='规格'){
                            window.location.href='/goods/spec/administration/edit?mgs='+mgs+'&specName='+specName+'&specValue='+specValue+'&goodsName='+goodsName+'&gssvId='+gssvId+'&gssId='+gssId+'&gsId='+gsId+'&spuId='+spuId+'&skuId='+skuId;
                        }else {
                            window.location.href='/goods/safeguard/administration/edit?mgs='+mgs+'&goodsName='+goodsName+'&gssId='+gssId+'&safeguardName='+safeguardName+'&skuId='+skuId;
                        }
                    })
                }
            })
        }
        //模糊查询
        $("#bit-list").click(function () {
            var goodsName = $("#search").val()
            getIndex(goodsName,1)
        })
    })
</script>


</body>
</html>
