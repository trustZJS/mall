<%--
  Created by IntelliJ IDEA.
  User: 黑夜
  Date: 2019/12/12
  Time: 22:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品总览</title>
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


    <div class="layui-inline layui-show-xs-block">
        <input type="button" id="btn-insert" class="layui-btn"  value="添加" />
    </div>

</div>



<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">

                <div class="layui-card-body ">

                    <label class="layui-label">选择类型:</label>
                    <div id="categoryHtml" >
                    </div>

                    <label class="layui-label">选择品牌:</label>
                    <div id="brandHtml">

                    </div>


                </div>

            </div>
            <div class="layui-card-body ">
                <table class="layui-table layui-form">
                    <thead>
                    <tr>

                        <th>商品名称</th>
                        <th>进货价格</th>
                        <th>售卖价格</th>
                        <th>库存</th>
                        <th>品牌</th>
                        <th>类型</th>
                        <th>商品标题</th>
                        <th>商品图片</th>
                        <th>操作</th>
                        <th  style="display:none">spuId</th>
                        <th  style="display:none">skuId</th>
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


<script type="text/javascript">

    $("#btn-insert").on("click",function(){
        var mgs = '添加';
        window.location.href="/goods/synthesize/administration/add/index?mgs="+mgs;
    })


    $(function () {
        var pagination = {};

        var spuId = 1;
        var categoryId =0;
        var brandId = 0;
        getIndex(1,categoryId,brandId,spuId);

        function getIndex(pageNum,categoryId,brandId,spuId){
            $.ajax({
                type: "POST",
                url: "/goods/synthesize/query",
                data: {categoryId:categoryId,brandId:brandId,spuId:spuId,pageNum:pageNum},
                dataType: "json",
                success: function(result){
                    //品牌下拉框
                    var brandHtml = "";
                    brandHtml+=" <select name=\"brandName\" lay-filter=\"aihao\" id=\"select-brandName\">";
                    brandHtml += "<option value=\"0\">请选择</option>";
                    $.each(result.goodsBrands,function (index,val) {
                        if (val.id==brandId){
                            brandHtml+="<option selected value="+val.id+">";
                            brandHtml+=val.brandName;
                            brandHtml+="</option>";
                        }else {
                            brandHtml+="<option value="+val.id+">";
                            brandHtml+=val.brandName;
                            brandHtml+="</option>";
                        }

                    })
                    brandHtml+="</select>";
                    $("#brandHtml").html(brandHtml);

                    //类型下拉框
                    var categoryHtml = "";
                    categoryHtml+=" <select name=\"categoryName\" lay-filter=\"aihao\" id=\"select-category\">";
                    categoryHtml+="<option value=\"0\">请选择</option>";
                    $.each(result.goodsCategories,function (index,val) {
                        if (val.id==categoryId){
                            categoryHtml+="<option selected value="+val.id+" >";
                            categoryHtml+=val.categoryName;
                            categoryHtml+="</option>";
                        }else {
                            categoryHtml+="<option value="+val.id+">";
                            categoryHtml+=val.categoryName;
                            categoryHtml+="</option>";
                        }
                    })
                    categoryHtml+="</select>";
                    $("#categoryHtml").html(categoryHtml);
                    pagination = result.pageInfo;
                    var html="";
                    $.each(pagination.list,function (index,val) {
                        html+="<tr>";

                        html+="<td>"+val.goodsName+"</td>";
                        html+="<td>"+val.lowPrice+"</td>";
                        html+="<td>"+val.price+"</td>";
                        html+="<td>"+val.stock+"</td>";
                        html+="<td>"+val.brandName+"</td>";
                        html+="<td>"+val.categoryName+"</td>";
                        html+="<td>"+val.skuTitle+"</td>";
                        html+="<td>"
                        html+="<img class='img' src="+val.skuImgPath+" />";
                        html+="</td>"
                        html+="<td  class=\"td-manage\">";
                        html+="<button class=\"layui-btn btn-xs Update\"> <i class=\"layui-icon\">&#xe642;</i>编辑</button>";
                        html+="<button class=\"layui-btn layui-btn-normal btn-xs Delete\" > <i class=\"layui-icon\">&#xe640;</i>删除</button>";
                        html+= "</td>";

                        html+="<td style=\"display:none\" >"+val.spuId+"</td>";
                        html+="<td style=\"display:none\" >"+val.skuId+"</td>";
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
                        getIndex($(this).html(),categoryId,brandId,spuId);
                    })

                    $("#first").on("click",function () {
                        getIndex(1,categoryId,brandId,spuId);
                    })

                    $("#prev").on("click",function () {
                        getIndex(pagination.prePage,categoryId,brandId,spuId);
                    })

                    $("#next").on("click",function () {
                        getIndex(pagination.nextPage,categoryId,brandId,spuId);
                    })

                    $("#last").on("click",function () {
                        getIndex(pagination.pages,categoryId,brandId,spuId);
                    })
                    $("#select-category").change(function () {
                        categoryId = $(this).find("option:selected").val();
                        getIndex(1,categoryId,brandId,spuId);
                    })
                    $("#select-brandName").change(function () {
                        brandId = $(this).find("option:selected").val();
                        getIndex(1,categoryId,brandId,spuId);
                    })


                    $(".Delete").on("click",function(){
                        var spuId = $(this).parent().parent().find("td:eq(9)").html();
                        var skuId = $(this).parent().parent().find("td:eq(10)").html();
                        alert(spuId+'spuId');
                        alert(skuId)
                        layer.confirm('确认删除',{icon:3,title:"系统提示"},function(index){
                            $.get(
                                "/goods/synthesize/delete",
                                {spuId:spuId,skuId:skuId},
                                function(result){
                                    if(result.code=="200"){
                                        layer.msg("删除成功",{icon:1,time:1000},function(){
                                            getIndex(1,categoryId,brandId,spuId);
                                        })
                                    }else{
                                        layer.msg("删除失败",{icon:2,time:1000})
                                    }
                                })
                        })
                    })



                    //修改 safeguardUpdate
                    $(".Update").on("click",function(){
                        var spuId = $(this).parent().parent().find("td:eq(9)").html();
                        var skuId = $(this).parent().parent().find("td:eq(10)").html();
                        window.location.href='/goods/synthesize/edit?spuId='+spuId+'&skuId='+skuId;
                    })



                }  //这个括号是Ajax返回的结果集括号
            })
        }

    })
</script>
</body>
</html>
