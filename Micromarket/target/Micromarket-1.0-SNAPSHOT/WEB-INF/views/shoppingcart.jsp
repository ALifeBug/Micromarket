<%--
  Created by IntelliJ IDEA.
  User: hqs
  Date: 18-7-23
  Time: 下午7:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="header.jsp"%>
<title>购物车</title>
<link rel="stylesheet" href="${path}/css/cart.css">
<h1>购物车</h1>
<hr style="border-color: #f7f7f7">
<c:if test="${empty sessionScope.shoppingcart.products}">
    <center><div class="glyphicon glyphicon-star-empty" style="font-size: 200px;"></div></center>
    <h4>您的购物车还是空的哦</h4>
</c:if>

<c:if test="${!empty sessionScope.shoppingcart.products}">
    <div class="cart">
        <c:forEach var="product" items="${sessionScope.shoppingcart.products}">
            <div class="show">
                <div class="viewproduct">
                    <c:if test="${empty product.picture[0]}">
                        <img src="${path}/img/default.png" class="img-fluid img-thumbnail">
                    </c:if>
                    <c:if test="${!empty product.picture[0]}">
                        <img src="${path}/getImage?imgName=${product.picture[0]}" class="img-fluid img-thumbnail">
                    </c:if>
                </div>
                <div class="price">${product.name}&nbsp;￥${product.price}</div>
                <a href="${path}/detail?productNum=${product.number}" class="view"><button class="btn btn-warning button">查看</button></a>
            </div>
        </c:forEach>
    </div>
</c:if>

<%@include file="footer.jsp"%>