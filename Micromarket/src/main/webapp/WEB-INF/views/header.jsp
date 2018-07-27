<%--
  Created by IntelliJ IDEA.
  User: hqs
  Date: 18-7-16
  Time: 下午2:20
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" scope="session"><%=request.getContextPath()%></c:set>
<link rel="stylesheet" href="${path}/css/style.css">
<link rel="stylesheet" href="${path}/css/bootstrap.min.css">
<script src="${path}/js/jquery-3.3.1.min.js" type="text/javascript"></script>
<script src="${path}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${path}/js/base.js" type="text/javascript"></script>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<%@include file="nav.jsp"%>
<div class="ibody">


