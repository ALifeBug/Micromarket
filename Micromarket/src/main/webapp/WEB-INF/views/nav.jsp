<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<nav class="navbar navbar-expand-sm  navbar-dark" style="background-color: #1b1e21">
    <a class="navbar-brand" href="${path}/">
        <img src="${path}/img/logo.jpeg" alt="logo" class="logo">
    </a>
    <a class="navbar-brand" href="${path}/" style="font-size: 30px">贝壳二手交易网</a>
    <div class="collapse navbar-collapse justify-content-end" style="margin-right: 50px;">
    <ul class="navbar-nav banner">
        <li class="nav-item">
            <a class="nav-link" href="${path}/"><span class="glyphicon glyphicon-home">首页</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${path}/shoppingcart"><span class="glyphicon glyphicon-shopping-cart">购物车</span></a>
        </li>
        <c:if test="${!empty sessionScope.user}">
            <li class="nav-item">
                <a class="nav-link" href="${path}/user/space"><span class="glyphicon glyphicon-user">我的</span></a>
            </li>
        </c:if>
        <c:if test="${empty sessionScope.user}">
            <li class="nav-item">
                <a class="nav-link" href="${path}/reg"><span class="glyphicon glyphicon-user">登录</span></a>
            </li>
        </c:if>
    </ul>
    </div>
</nav>