<%--
  Created by IntelliJ IDEA.
  User: 黑夜
  Date: 2019/12/17
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>规格值编辑</title>


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
    <legend id="state_selection">${goodsSpecValueVo.mgs}</legend>
</fieldset>
<%--goodsSpecValueVo.specName--%>
    <label class="layui-label">选择规格名称:</label>
    <select name="specName" lay-filter="aihao" id="select-specId">
        <option value=\"0\">请选择</option>
      <c:forEach items="${goodsSpecs}" var="gs">
          <c:if test="${gs.specName != goodsSpecValueVo.specName}">
              <option value="${gs.id}">
                      ${gs.specName}
              </option>
          </c:if>
          <c:if test="${gs.specName == goodsSpecValueVo.specName}">
              <option selected value="${gs.id}">
                      ${gs.specName}
              </option>
          </c:if>
      </c:forEach>
    </select></br>

    <label class="layui-label">规格值:</label>
    <input type="text" id="specValue" name="specNo" lay-verify="required" placeholder="请输入规格值" autocomplete="off" class="layui-input" value="${goodsSpecValueVo.specValue}">

    <input type="hidden" id="specValue_id"  value="${goodsSpecValueVo.id}">


<input type="button" id="btn-edit" value="${goodsSpecValueVo.mgs}"  class="layui-btn"/>
<button type="button" id="btn-return" class="layui-btn layui-btn-primary">返回</button>








<script type="text/javascript">

    $("#btn-return").click(function () {
        location.href = "/goods/specValue/index";
    })

    $("#btn-edit").click(function () {
        var state_selection = $(this).val();
        var specId = $("#select-specId").find("option:selected").val();
        var specValue = $("#specValue").val();
        var id = $("#specValue_id").val();


        if (state_selection=='添加'){
            $.ajax({
                url:"/goods/specValue/insert"
                , type:"post"
                ,dataType:"json"
                ,data: {specId:specId,specValue:specValue},
                success:function (result) {
                    if (result.code=="200"){
                        layer.msg("添加成功",{icon:1,time:1000})
                        location.href = "/goods/specValue/index";
                    }else{
                        alert('失败');
                    }
                }
            });
        }else {
            $.ajax({
                url:"/goods/specValue/update"
                , type:"post"
                ,dataType:"json"
                ,data:{specId:specId,specValue:specValue,id:id},
                success:function (result) {
                    if (result.code=="200"){
                        layer.msg("修改成功",{icon:1,time:1000})
                        location.href = "/goods/specValue/index";
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
