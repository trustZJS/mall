<%--
  Created by IntelliJ IDEA.
  User: 黑夜
  Date: 2019/12/22
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>商品增值管理</title>
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
<%--表--%>
<div class="layui-fluid" >
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body ">

                    <%-- 模糊搜索的搜索的 --%>
                    <div class="layui-inline layui-show-xs-block">
                        <input type="text"  id="search"  placeholder="商品名称搜索" autocomplete="off" class="layui-input">
                    </div>

                    <div class="layui-inline layui-show-xs-block">
                        <button class="layui-btn"  lay-submit="" lay-filter="sreach" id="bit-list"><i class="layui-icon">&#xe615;</i></button>
                    </div>


                    <%--添加的--%>

                    <div class="layui-inline layui-show-xs-block">
                        <input type="button" id="btn-insert" class="layui-btn"  value="添加" />
                    </div>

                    <div class="layui-inline layui-show-xs-block">
                        <input type="button" id="btn-all-delete" class="layui-btn"  value="批量删除" />
                    </div>
                </div>
                <%--                这个是表的--%>

                <div class="layui-card-body ">
                    <table class="layui-table layui-form">
                        <thead>
                        <tr>
                            <th>选择</th>
                            <th>商品名称</th>
                            <th>售卖标题</th>
                            <th>增值名称</th>
                            <th>增值价格</th>
                            <th style="display:none">skuId</th>
                            <th style="display:none">gssId</th>
                            <th>操作</th>
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
    $(function () {
        var pagination = {};
        var goodsName ='';
        getIndex(goodsName,1);
        function getIndex(goodsName,pageNum){
            $.ajax({
                type: "POST",
                url: "/goods/safeguard/administration/query",
                data: {goodsName:goodsName,pageNum:pageNum},
                dataType: "json",
                success: function(result){
                    var html="";
                    $.each(result.list,function (index,val) {
                        html+="<tr>";
                        html+="<td>";
                        html+=" <input type=\"checkbox\" class='checkbox' lay-skin=\"primary\">";
                        html+="</td>"

                        html+="<td>"+val.goodsName+"</td>";
                        html+="<td>"+val.skuTitle+"</td>";
                        html+="<td>"+val.safeguardName+"</td>";
                        html+="<td>"+val.price+"</td>";

                        html+="<td  style=\"display:none\">"+val.skuId+"</td>";
                        html+="<td  style=\"display:none\">"+val.gssId+"</td>";


                        html+="<td  class=\"td-manage\">";
                        html+="<button class=\"layui-btn btn-xs safeguardUpdate\"> <i class=\"layui-icon\">&#xe642;</i>编辑</button>";
                        html+="<button class=\"layui-btn btn-xs safeguardInsert\"> <i class=\"layui-icon\">&#xe642;</i>添加</button>";
                        html+="<button class=\"layui-btn layui-btn-normal btn-xs safeguardDelete\"  > <i class=\"layui-icon\">&#xe640;</i>删除</button>";
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
                        getIndex(goodsName,1);
                    })

                    $("#prev").on("click",function () {
                        getIndex(goodsName,pagination.prePage);

                    })

                    $("#next").on("click",function () {
                        getIndex(goodsName,pagination.nextPage)
                    })

                    $("#last").on("click",function () {
                        getIndex(goodsName,pagination.pages)
                    })


                    //删除
                    $(".safeguardDelete").on("click",function(){
                        var gssId = $(this).parent().parent().find("td:eq(6)").html();
                        layer.confirm('确认删除',{icon:3,title:"系统提示"},function(index){
                            $.get(
                                "/goods/safeguard/administration/delete",
                                {gssId:gssId},
                                function(result){
                                    if(result.code=="200"){
                                        layer.msg("删除成功",{icon:1,time:1000},function(){
                                            getIndex(goodsName,1)
                                        })
                                    }else{
                                        layer.msg("删除失败",{icon:2,time:1000})
                                    }
                                })

                        })
                    })

                    //修改
                    $(".safeguardUpdate").on("click",function(){
                        var goodsName =  $(this).parent().parent().find("td:eq(1)").html();
                        var gssId = $(this).parent().parent().find("td:eq(6)").html();
                        var safeguardName = $(this).parent().parent().find("td:eq(3)").html();
                        var skuId =  $(this).parent().parent().find("td:eq(5)").html();
                        var mgs = '修改';
                        window.location.href='/goods/safeguard/administration/edit?mgs='+mgs+'&goodsName='+goodsName+'&gssId='+gssId+'&safeguardName='+safeguardName+'&skuId'+skuId;
                    })

                    //添加
                    $(".safeguardInsert").on("click",function(){
                        var goodsName =  $(this).parent().parent().find("td:eq(1)").html();
                        var gssId = $(this).parent().parent().find("td:eq(6)").html();
                        var safeguardName = $(this).parent().parent().find("td:eq(3)").html();
                        var skuId =  $(this).parent().parent().find("td:eq(5)").html();
                        alert(skuId);
                        var mgs = '添加';
                        window.location.href='/goods/safeguard/administration/edit?mgs='+mgs+'&goodsName='+goodsName+'&gssId='+gssId+'&safeguardName='+safeguardName+'&skuId='+skuId
                    })


                }
            })
        }
        //模糊查询
        $("#bit-list").click(function () {
            var goodsName = $("#search").val()
            getIndex(goodsName,1)
        })



        //添加
        $("#btn-insert").click(function () {
            var state = '增值';
            window.location.href='/goods/spec/administration/add?state='+state;
        })


    })
    //这个时批量删除时
    layui.use(['laydate','form'], function(){

    });
</script>

</body>
</html>
