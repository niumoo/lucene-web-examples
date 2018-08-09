<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>lucene文件检索</title>
    <link type="text/css" rel="stylesheet" href="/static/css/index.css">
    <!-- 开发环境版本，包含了有帮助的命令行警告 -->
    <script src="https://cdn.bootcss.com/vue/2.5.17-beta.0/vue.js"></script>
</head>
<body>
<div class="indexbox">
    <div class="logo">
        <a href="index.jsp">
            <img alt="文件检索" src="/static/images/logo.png">
        </a>
    </div>
    <div class="searchform">
        <form action="/search/list" method="get">
            <input type="text" name="keyword"> <input type="submit" value="搜索">
        </form>
    </div>
    <div class="info" id="vmFooter">
        <p>基于Lucene的文件检索系统</p>
        <br/>
        <p><span>{{nowTime}}</span>
            扣钉博客 All rights Reserved</p>
    </div>
</div>
</body>
<script>
    var vmMain = new Vue({
        el: '#vmMaim',
        data: {
            nowTime: new Date().toLocaleString()
        }
    })

    var vmFooter = new Vue({
        el: '#vmFooter',
        data: {
            nowTime: new Date().toLocaleString()
        }
    })
</script>
</html>
