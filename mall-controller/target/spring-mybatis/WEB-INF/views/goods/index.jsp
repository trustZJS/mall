<%--
  Created by IntelliJ IDEA.
  User: 黑夜
  Date: 2019/12/5
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>商城管理系统</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="stylesheet" href="/static/css/font.css">
    <link rel="stylesheet" href="/static/css/xadmin.css">
    <!-- <link rel="stylesheet" href="./css/theme5.css"> -->
    <script src="/static/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/static/js/xadmin.js"></script>



    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script>
        // 是否开启刷新记忆tab功能
        // var is_remember = false;
    </script>
</head>

<body class="index">
<!-- 顶部开始 -->
<div class="container">
    <div class="logo">
        <a href="./index.html">商家管理系统</a></div>
    <div class="left_open">
        <a><i title="展开左侧栏" class="iconfont">&#xe699;</i></a>
    </div>

    <ul class="layui-nav right" lay-filter="">
        <li class="layui-nav-item">
            <a href="javascript:;">admin</a>
            <dl class="layui-nav-child">
                <!-- 二级菜单 -->
                <dd>
                    <a onclick="xadmin.open('个人信息','http://www.baidu.com')">个人信息</a></dd>
                <dd>
                    <a onclick="xadmin.open('切换帐号','http://www.baidu.com')">切换帐号</a></dd>
                <dd>
                    <a href="./login.html">退出</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item to-index">
            <a href="/">前台首页</a>
        </li>
    </ul>
</div>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<!-- 左侧菜单开始 -->
<div class="left-nav">
    <div id="side-nav">
        <ul id="nav">

            <!-- 商品管理 -->
            <li>
                <a href="javascript:;">
                    <i class="iconfont left-nav-li" lay-tips="商品管理">&#xe828</i>
                    <cite>品牌与类型管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i></a>
                <ul class="sub-menu">
                    <li>
                        <a onclick="xadmin.add_tab('品牌管理','/goods/brand/index')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>品牌管理</cite>
                        </a>
                    </li>
                    <li>
                        <a onclick="xadmin.add_tab('类型管理','/goods/category/list')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>类型管理</cite></a>
                    </li>

                </ul>
            </li>



            <!-- 增值管理 -->
            <li>
                <a href="javascript:;">
                    <i class="iconfont left-nav-li" lay-tips="增值管理">&#xe723;</i>
                    <cite>增值管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i></a>
                <ul class="sub-menu">
                    <li>
                        <a onclick="xadmin.add_tab('增值列表','/goods/safeguard/index')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>增值列表</cite></a>
                    </li>
                </ul>
            </li>




        <%--            商品总览--%>
            <li>
                <a href="javascript:;">
                    <i class="iconfont left-nav-li" lay-tips="商品总览">&#xe723;</i>
                    <cite>商品总览</cite>
                    <i class="iconfont nav_right">&#xe697;</i></a>
                <ul class="sub-menu">
                    <li>
                        <a onclick="xadmin.add_tab('商品总览','/goods/synthesize/index')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>商品总览</cite></a>
                    </li>
                </ul>
            </li>



            <!-- 规格管理 -->
            <li>
                <a href="javascript:;">
                    <i class="iconfont left-nav-li" lay-tips="商品规格管理">&#xe6b2</i>
                    <cite>商品规格管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i></a>
                <ul class="sub-menu">
                    <li>
                        <a onclick="xadmin.add_tab('规格管理','/goods/spec/index')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>规格管理</cite>
                        </a>
                    </li>
                    <li>
                        <a onclick="xadmin.add_tab('规格值管理','/goods/specValue/index')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>规格值管理</cite></a>
                    </li>





                </ul>
            </li>



            <!-- 订单管理 -->
            <li>
                <a href="javascript:;">
                    <i class="iconfont left-nav-li" lay-tips="订单管理">&#xe723;</i>
                    <cite>订单管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i></a>
                <ul class="sub-menu">
                    <li>
                        <a onclick="xadmin.add_tab('订单列表','order-list.html')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>订单列表</cite></a>
                    </li>
                </ul>
            </li>


            <!-- 库存管理 -->
            <li>
                <a href="javascript:;">
                    <i class="iconfont left-nav-li" lay-tips="库存管理">&#xe6cb;</i>
                    <cite>商品管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i></a>
                <ul class="sub-menu">

                    <li>
                        <a onclick="xadmin.add_tab('商品规格管理','/goods/synthesize/administration/add/index')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>商品添加</cite></a>
                    </li>

                    <li>
                        <a onclick="xadmin.add_tab('商品规格管理','/goods/synthesize/administration/add/index')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>商品修改</cite></a>
                    </li>


                    <li>
                        <a onclick="xadmin.add_tab('商品规格管理','/goods/spu/spec/index')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>商品规格管理</cite></a>
                    </li>
                    <li>
                        <a onclick="xadmin.add_tab('商品规格值管理','/goods/spec/administration/index')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>商品规格值管理</cite></a>
                    </li>

                    <li>
                        <a onclick="xadmin.add_tab('商品增值管理','/goods/safeguard/administration/index')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>商品增值管理</cite></a>
                    </li>


                </ul>
            </li>

            <!-- 营销管理 -->
            <li>
                <a href="javascript:;">
                    <i class="iconfont left-nav-li" lay-tips="营销管理">&#xe758;</i>
                    <cite>营销管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i></a>
                <ul class="sub-menu">
                    <li>
                        <a onclick="xadmin.add_tab('财务总览','cate.html')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>财务总览</cite></a>
                    </li>

                    <li>
                        <a onclick="xadmin.add_tab('销量统计','cate.html')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>销量统计</cite></a>
                    </li>
                </ul>
            </li>

            <!-- 个人账号管理 -->
            <li>
                <a href="javascript:;">
                    <i class="iconfont left-nav-li" lay-tips="账号管理">&#xe6b8;</i>
                    <cite>账号管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i></a>
                <ul class="sub-menu">
                    <li>
                        <a onclick="xadmin.add_tab('个人信息修改','order-list.html')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>个人信息修改</cite></a>
                    </li>
                    <li>
                        <a onclick="xadmin.add_tab('密码修改','order-list1.html')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>密码修改</cite></a>
                    </li>
                </ul>
            </li>



            <!-- 管理员管理 -->
            <li>
                <a href="javascript:;">
                    <i class="iconfont left-nav-li" lay-tips="管理员管理">&#xe726;</i>
                    <cite>管理员管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i></a>
                <ul class="sub-menu">
                    <li>
                        <a onclick="xadmin.add_tab('管理员列表','admin-list.html')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>管理员列表</cite></a>
                    </li>
                    <li>
                        <a onclick="xadmin.add_tab('角色管理','admin-role.html')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>角色管理</cite></a>
                    </li>
                    <li>
                        <a onclick="xadmin.add_tab('权限分类','admin-cate.html')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>权限分类</cite></a>
                    </li>
                    <li>
                        <a onclick="xadmin.add_tab('权限管理','admin-rule.html')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>权限管理</cite></a>
                    </li>
                </ul>
            </li>

        </ul>
    </div>
</div>
<!-- <div class="x-slide_left"></div> -->
<!-- 左侧菜单结束 -->
<!-- 右侧主体开始 -->

<div class="page-content">
    <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
        <ul class="layui-tab-title">
            <li class="home">
                <i class="layui-icon">&#xe68e;</i>我的桌面</li></ul>
        <div class="layui-unselect layui-form-select layui-form-selected" id="tab_right">
            <dl>
                <dd data-type="this">关闭当前</dd>
                <dd data-type="other">关闭其它</dd>
                <dd data-type="all">关闭全部</dd></dl>
        </div>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <iframe src='./welcome.html' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
            </div>
        </div>
        <div id="tab_show"></div>
    </div>
</div>



</body>

</html>
