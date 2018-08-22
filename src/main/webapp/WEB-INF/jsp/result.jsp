<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>搜索结果</title>

    <link type="text/css" rel="stylesheet" href="/static/css/result.css">
</head>
<body>
<div class="searchbox">
    <div class="logo">
        <a href="/"><img alt="文件检索" src="/static/images/logo.png"></a>
    </div>
    <div class="searchform">
        <form action="/search/list" method="get">
            <input type="text" name="keyword" value=""> <input type="submit" value="搜索">
        </form>
    </div>
</div>
<div class="result">
    <h4>
        共搜到<span style="color: red; font-weight: bold;">5</span>条结果
    </h4>

    <div class="item" id="vmMain">
        <c:forEach items="${fileModelList }" var="fileModel">
            <div class="itemtop" v-for="fileModel in view">
                <h4>
                    <img alt="pdf" src="/static/images/${fileModel.fileType}.png" class="doclogo" >
                   ${fileModel.title}
                </h4>
                <h3>
                    <a href="/static/files/${fileModel.title}">点击下载</a>
                </h3>
            </div>
            <div class="itembuttom">
               ${fileModel.summary}
            </div>
            <hr class="itemline">
        </c:forEach>
    </div>

</div>

<div class="footer">
    <p> Lucene项目案例</p>
    <p>扣钉博客 All rights Reserved</p>
</div>


</body>
</html>