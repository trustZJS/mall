<%--
  Created by IntelliJ IDEA.
  User: 黑夜
  Date: 2019/12/19
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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


<div class="x-nav">
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
</div>



<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">

                <div class="layui-card-body ">
                    <label class="layui-label">规格选择:</label>
                    <div id="specHtml" >

                    </div>
                </div>

            </div>
            <div class="layui-card-body ">
                <table class="layui-table layui-form">
                    <thead>
                    <tr>
                        <th>商品名称</th>
                        <th>商品规格</th>
                        <th style="display:none">id</th>
                        <th>操作</th>
                    </thead>
                    <tbody id="tbody">

                    </tbody>
                </table>
            </div>

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

        getIndex(1,specId);

        function getIndex(pageNum,specId){
            $.ajax({
                type: "POST",
                url: "/goods/spu/spec/query",
                data: {pageNum:pageNum,specId:specId},
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
                        html+="<tr>";
                        html+="<td>"+val.goodsName+"</td>";
                        html+="<td>"+val.specName+"</td>";
                        html+="<td style=\"display:none\">"+val.id+"</td>";

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
                        getIndex($(this).html(),specId);
                    })

                    $("#first").on("click",function () {
                        getIndex(1,specId);
                    })

                    $("#prev").on("click",function () {
                        getIndex(pagination.prePage,specId);
                    })

                    $("#next").on("click",function () {
                        getIndex(pagination.nextPage,specId);
                    })

                    $("#last").on("click",function () {
                        getIndex(pagination.pages,specId);
                    })

                    $("#select-spec").change(function () {
                        specId = $(this).find("option:selected").val();
                        getIndex(1,specId);
                    })

                    //修改
                    $(".specUpdate").on("click",function(){
                        var id = $(this).parent().parent().find("td:eq(2)").html();
                        var specName = $(this).parent().parent().find("td:eq(1)").html();
                        var goodsName = $(this).parent().parent().find("td:eq(0)").html();
                        var mgs = '修改';

                        window.location.href='/goods/spu/spec/edit?mgs='+mgs+'&id='+id+'&specName='+specName+'&goodsName='+goodsName;
                    })
                    //添加
                    $(".specInsert").on("click",function(){
                        var id = $(this).parent().parent().find("td:eq(2)").html();
                        var specName = $(this).parent().parent().find("td:eq(1)").html();
                        var goodsName = $(this).parent().parent().find("td:eq(0)").html();
                        var mgs = '添加';
                        window.location.href='/goods/spu/spec/edit?mgs='+mgs+'&id='+id+'&specName='+specName+'&goodsName='+goodsName;
                    })





                    //删除
                    $(".specDelete").on("click",function(){
                        var id = $(this).parent().parent().find("td:eq(2)").html();
                        alert(id)
                        layer.confirm('确认删除',{icon:3,title:"系统提示"},function(index){
                            $.get(
                                "/goods/spu/spec/delete",
                                {"id":id},
                                function(result){
                                    if(result.code=="200"){
                                        layer.msg("删除成功",{icon:1,time:1000},function(){
                                            getIndex(1,specId);
                                        })
                                    }else{
                                        layer.msg("添加失败",{icon:2,time:1000})
                                    }
                                })
                        })
                    })


                }  //这个括号是Ajax返回的结果集括号
            })
        }

    })
</script>

</body>
</html>
