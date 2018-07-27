<%--
  Created by IntelliJ IDEA.
  User: hqs
  Date: 18-7-23
  Time: 上午10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@include file="header.jsp"%>
<title>修改个人信息</title>
<link rel="stylesheet" href="${path}/css/add.css">
<script src="${path}/js/edit.js"></script>
<style>
    .glyphicon{
        font-weight: bold;
    }
</style>
<h1 style="margin: 10px auto;text-align: center">修改个人信息</h1><h6 style="margin: 5px auto;text-align: center">(修改后重新登录生效)</h6>
<div class="form">
    <form action="${path}/user/editinfo" method="post" role="form">
        <div class="form-group">
            <label for="nickname"><span class="glyphicon glyphicon-user">昵称:</span></label><span id="name-error" style="color: #dc3545"></span>
            <input type="text" class="form-control" name="nickname" id="nickname" value="${sessionScope.user.nickname}(不可修改)" required maxlength="15" disabled="disabled" style="color: #1b1e21">
            <input type="hidden" id="oldname" value="${sessionScope.user.nickname}">
        </div>
        <div class="form-group">
            <label for="email"><span class="glyphicon glyphicon-envelope">邮箱:</span></label><span id="email-error" style="color: #dc3545"></span>
            <input  type="text" class="form-control text" name="email" id="email" value="${sessionScope.user.email}" required>
        </div>
        <div class="form-group">
            <label for="phone"><span class="glyphicon glyphicon-phone">电话:</span></label>
            <input  class="form-control" name="phone" id="phone" value="${sessionScope.user.phone}(不可修改)" required disabled="disabled"/>
        </div>
        <div class="form-group">
            <label for="grade"><span class="glyphicon glyphicon-stats">年级:</span></label>
            <input  type="text" class="form-control text" name="grade" id="grade" value="${sessionScope.user.grade}" required>
        </div>
        <div class="form-group">
            <label for="academy"><span class="glyphicon glyphicon-stats">学院:</span></label>
            <input  type="text" class="form-control text" name="academy" id="academy" value="${sessionScope.user.academy}" required>
        </div>
        <div class="form-group">
            <label for="contact"><span class="glyphicon glyphicon-send">其他联系方式:</span></label>
            <input  type="text" class="form-control text" name="contact" id="contact" value="${sessionScope.user.contact}" required>
        </div>
        <button type="submit" class="btn btn-success button">提交</button>
        <a class="btn btn-dark button" href="${path}/user/space">返回</a>
    </form>
</div>
<%@include file="footer.jsp"%>
