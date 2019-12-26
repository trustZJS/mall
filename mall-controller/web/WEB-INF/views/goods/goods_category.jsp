<%--
  Created by IntelliJ IDEA.
  User: 黑夜
  Date: 2019/12/8
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>类型查询</title>
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

                        <%--添加的--%>
                        <div class="layui-inline layui-show-xs-block">
                            <input type="text"  id="categoryName" placeholder="分类添加" autocomplete="off" class="layui-input">
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <input type="button" id="btn-insert" class="layui-btn"  lay-submit="" lay-filter="sreach" value="添加" />
                        </div>


                </div>
                <%--                这个是表的--%>
                <div class="layui-card-body ">
                    <table class="layui-table layui-form">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>类型名称</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
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
        var username ='';
        getIndex(username,1);


        function getIndex(username,pageNum){
            $.ajax({
                type: "POST",
                url: "/goods/category/query",
                data: {username:username,pageNum:pageNum},
                dataType: "json",
                success: function(result){
                    var html="";
                    $.each(result.list,function (index,val) {

                        html+="<tr>";
                        html+="<td>"+val.id+"</td>";
                        html+="<td>"+val.categoryName+"</td>";
                        html+="<td>"+val.gmtCreate+"</td>";
                        html+="<td>"+val.gmtUpdate+"</td>";
                        html+="<td  class=\"td-manage\">";
                        html+="<button class=\"layui-btn btn-xs categoryUpdate\"> <i class=\"layui-icon\">&#xe642;</i>编辑</button>";
                        html+="<button class=\"layui-btn layui-btn-normal btn-xs categoryDelete\" value=\"${g.id}\" > <i class=\"layui-icon\">&#xe640;</i>删除</button>";
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
                        getIndex(username,$(this).html());
                    })

                    $("#first").on("click",function () {
                        getIndex(username,1)
                    })

                    $("#prev").on("click",function () {
                        getIndex(username,pagination.prePage)
                    })

                    $("#next").on("click",function () {
                        getIndex(username,pagination.nextPage)
                    })

                    $("#last").on("click",function () {
                        getIndex(username,pagination.pages)
                    })

                    //    修改
                    $(".categoryUpdate").on("click",function () {
                        var id = $(this).parent().parent().find("td:eq(0)").html();
                        var categoryName = $(this).parent().parent().find("td:eq(1)").html();
                        layer.prompt({
                                formType: 0,
                                title: '修改类型',
                                value:categoryName,
                                id:"text"
                            },
                            function(value, index, elem){
                                $.post(
                                    "/goods/category/update",
                                    {"categoryName":value,"id":id},
                                    function(result){
                                        if(result.code=="200"){
                                            layer.msg("修改成功",{icon:1,time:1000},function(){
                                                layer.close(index);
                                                getIndex(username,1)
                                            })
                                        }else{
                                            layer.msg(result.data,{icon:2,time:1000})
                                        }
                                    })
                            });
                    })

                    //删除
                    $(".categoryDelete").on("click",function(){
                        var id = $(this).parent().parent().find("td:eq(0)").html();
                        alert(id);
                        layer.confirm('确认删除',{icon:3,title:"系统提示"},function(index){

                            $.get(
                                "/goods/category/delete",
                                {"id":id},
                                function(result){
                                if(result.code=="200"){
                                    layer.msg("删除成功",{icon:1,time:1000},function(){
                                        getIndex(username,1)
                                    })
                                }else{
                                    layer.msg("添加失败",{icon:2,time:1000})
                                }
                            })

                        })
                    })


                }
            })
        }
        //模糊查询
        $("#bit-list").click(function () {
            var username = $("#search").val()
            getIndex(username,1)
        })

        /* 添加*/
        $("#btn-insert").on("click",function(){
            var categoryName = $("#categoryName").val();

            $.post(
                "/goods/category/insert",
                {"categoryName":categoryName},
                function(result){
                    if(result.code=="200"){
                        layer.msg("添加成功",{icon:1,time:1000},function(){
                            getIndex(username,1)
                        })
                    }else{
                        layer.msg("添加失败",{icon:2,time:1000})
                    }
                })
        })

    })
</script>



</body>
</html>
