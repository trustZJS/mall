<%--
  Created by IntelliJ IDEA.
  User: 黑夜
  Date: 2019/12/19
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>商品规格编辑</title>
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
    <legend id="state_selection">${goodsSpuSpec.mgs}</legend>
</fieldset>
<%--goodsSpecValueVo.specName--%>
<label class="layui-label">选择规格名称:</label>
<select name="specName" lay-filter="aihao" id="select-specId">
    <option value=\"0\">请选择</option>
    <c:forEach items="${goodsSpecs}" var="gs">
        <c:if test="${gs.specName != goodsSpuSpec.specName}">
            <option value="${gs.id}">
                    ${gs.specName}
            </option>
        </c:if>
        <c:if test="${gs.specName == goodsSpuSpec.specName}">
            <option selected value="${gs.id}">
                    ${gs.specName}
            </option>
        </c:if>
    </c:forEach>
</select></br>

<label class="layui-label">商品名称选择:</label>
<select name="specName" lay-filter="aihao" id="select-spuId">
    <option value=\"0\">请选择</option>
    <c:forEach items="${goodsSpuSpecs}" var="gs">
        <c:if test="${gs.goodsName != goodsSpuSpec.goodsName}">
            <option value="${gs.id}">
                    ${gs.goodsName}
            </option>
        </c:if>
        <c:if test="${gs.goodsName == goodsSpuSpec.goodsName}">
            <option selected value="${gs.id}">
                    ${gs.goodsName}
            </option>
        </c:if>
    </c:forEach>
</select></br>
<%--这个id是修改需要用到的,如果不修改是用不到的--%>
<input type="hidden" id="specValue_id"  value="${goodsSpuSpec.id}">
<input type="button" id="btn-edit" value="${goodsSpuSpec.mgs}"  class="layui-btn"/>
<button type="button" id="btn-return" class="layui-btn layui-btn-primary">返回</button>


<script type="text/javascript">

    $("#btn-return").click(function () {
        location.href = "/goods/spu/spec/index";
    })

    $("#btn-edit").click(function () {
        var state_selection = $(this).val();
        var specId = $("#select-specId").find("option:selected").val();
        var spuId = $("#select-spuId").find("option:selected").val();
        var id = $("#specValue_id").val();
        if (state_selection=='添加'){
            $.ajax({
                url:"/goods/spu/spec/insert"
                , type:"post"
                ,dataType:"json"
                ,data: {specId:specId,spuId:spuId},
                success:function (result) {
                    if (result.code=="200"){
                        layer.msg("添加成功",{icon:1,time:1000})
                        location.href = "/goods/spu/spec/index";
                    }else{
                        alert('失败');
                    }
                }
            });
        }else {
            $.ajax({
                url:"/goods/spu/spec/update"
                , type:"post"
                ,dataType:"json"
                ,data:{specId:specId,id:id,spuId:spuId},
                success:function (result) {
                    if (result.code=="200"){
                        layer.msg("修改成功",{icon:1,time:1000})
                        location.href = "/goods/spu/spec/index";
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
