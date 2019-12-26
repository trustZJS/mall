<%--
  Created by IntelliJ IDEA.
  User: 黑夜
  Date: 2019/12/20
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>商品规格管理</title>

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

                    <div class="layui-inline layui-show-xs-block">
                        <label class="layui-label">选择规格:</label>
                        <div id="specHtml" >

                        </div>
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
                            <th>规格</th>
                            <th>规格值</th>

                            <th style="display:none">gssvId</th>
                            <th style="display:none">gssId</th>
                            <th style="display:none">spuId</th>
                            <th style="display:none">skuId</th>
                            <th style="display:none">gsId</th>
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
        var specId = 0;
        var goodsName = "";
        getIndex(1,specId,goodsName);
        function getIndex(pageNum,specId,goodsName){
            $.ajax({
                type: "POST",
                url: "/goods/spec/administration/query",
                data: {pageNum:pageNum,specId:specId,goodsName:goodsName},
                dataType: "json",
                success: function(result){
                    //类型下拉框
                    var specHtml = "";
                    specHtml+=" <select  lay-filter=\"aihao\" id=\"select-spec\">";
                    specHtml+="<option value=\"0\">请选择</option>";
                    $.each(result.goodsSpecs,function (index,val) {
                        if (val.id==specId){
                            specHtml+="<option selected value="+val.id+" >";
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




                    pagination = result.pageInfo;
                    var html="";
                    $.each(pagination.list,function (index,val) {
                        html+="<tr>";//<td> </td>
                        html+="<td>";
                        html+="<input type=\"checkbox\" lay-skin=\"primary\">";
                        html+="</td>";
                        html+="<td>"+val.goodsName+"</td>";
                        html+="<td>"+val.specName+"</td>";
                        html+="<td>"+val.specValue+"</td>";


                        html+="<td style=\"display:none\">"+val.gssvId+"</td>";
                        html+="<td style=\"display:none\">"+val.gssId+"</td>";
                        html+="<td style=\"display:none\">"+val.spuId+"</td>";
                        html+="<td style=\"display:none\">"+val.skuId+"</td>";
                        html+="<td style=\"display:none\">"+val.gsId+"</td>";

                        html+="<td  class=\"td-manage\">";
                        html+="<button class=\"layui-btn btn-xs specUpdate\"> <i class=\"layui-icon\">&#xe642;</i>编辑</button>";
                        html+="<button class=\"layui-btn btn-xs specInsert\"> <i class=\"layui-icon\">&#xe642;</i>添加</button>";
                        html+="<button class=\"layui-btn layui-btn-normal btn-xs specDelete\" > <i class=\"layui-icon\">&#xe640;</i>删除</button>";
                        html+= "</td>";
                        html+="</tr>";
                    })

                    $("#tbody").html(html);
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
                        getIndex($(this).html(),specId,goodsName);
                    })

                    $("#first").on("click",function () {
                        getIndex(1,specId,goodsName);
                    })

                    $("#prev").on("click",function () {
                        getIndex(pagination.prePage,specId,goodsName);
                    })

                    $("#next").on("click",function () {
                        getIndex(pagination.nextPage,specId,goodsName);
                    })

                    $("#last").on("click",function () {
                        getIndex(pagination.pages,specId,goodsName);
                    })

                    $("#select-spec").change(function () {
                        specId = $(this).find("option:selected").val();
                        getIndex(1,specId,goodsName);
                    })

                    //删除
                    $(".specDelete").on("click",function(){
                        var gssvId = $(this).parent().parent().find("td:eq(4)").html();
                        layer.confirm('确认删除',{icon:3,title:"系统提示"},function(index){
                            $.get(
                                "/goods/spec/administration/delete",
                                {gssvId:gssvId},
                                function(result){
                                    if(result.code=="200"){
                                        layer.msg("删除成功",{icon:1,time:1000},function(){
                                            getIndex(1,specId,goodsName);
                                        })
                                    }else{
                                        layer.msg("添加失败",{icon:2,time:1000})
                                    }
                                })
                        })
                    })
                    //添加
                    $(".specInsert").on("click",function(){
                        var goodsName = $(this).parent().parent().find("td:eq(1)").html();
                        var specName = $(this).parent().parent().find("td:eq(2)").html();
                        var specValue = $(this).parent().parent().find("td:eq(3)").html();
                        var gssvId = $(this).parent().parent().find("td:eq(4)").html();
                        var gssId = $(this).parent().parent().find("td:eq(5)").html();
                        var gsId = $(this).parent().parent().find("td:eq(8)").html();
                        var spuId =  $(this).parent().parent().find("td:eq(6)").html();
                        var skuId =  $(this).parent().parent().find("td:eq(7)").html();
                        var mgs = '添加';
                        window.location.href='/goods/spec/administration/edit?mgs='+mgs+'&specName='+specName+'&specValue='+specValue+'&goodsName='+goodsName+'&gssvId='+gssvId+'&gssId='+gssId+'&gsId='+gsId+'&spuId='+spuId+'&skuId='+skuId;
                    })
                    
                    //修改
                    $(".specUpdate").on("click",function(){
                        var goodsName = $(this).parent().parent().find("td:eq(1)").html();
                        var specName = $(this).parent().parent().find("td:eq(2)").html();
                        var specValue = $(this).parent().parent().find("td:eq(3)").html();
                        var gssvId = $(this).parent().parent().find("td:eq(4)").html();
                        var gssId = $(this).parent().parent().find("td:eq(5)").html();
                        var gsId = $(this).parent().parent().find("td:eq(8)").html();
                        var spuId =  $(this).parent().parent().find("td:eq(6)").html();
                        var skuId =  $(this).parent().parent().find("td:eq(7)").html();;
                        var mgs = '修改';
                        window.location.href='/goods/spec/administration/edit?mgs='+mgs+'&specName='+specName+'&specValue='+specValue+'&goodsName='+goodsName+'&gssvId='+gssvId+'&gssId='+gssId+'&gsId='+gsId+'&spuId='+spuId+'&skuId='+skuId;
                    })
                    
                    


                    //    模糊商品搜索
                    $("#bit-list").click(function () {
                        var goodsName = $("#search").val()
                        getIndex(1,specId,goodsName);
                    })
                    
                    $("#btn-insert").click(function () {
                        var state = '规格';
                        window.location.href='/goods/spec/administration/add?state='+state;
                    })
                    

                }  //这个括号是Ajax返回的结果集括号
            })
        }
    })

    //这个时批量删除时
    layui.use(['laydate','form'], function(){

    });
</script>

</body>
</html>
