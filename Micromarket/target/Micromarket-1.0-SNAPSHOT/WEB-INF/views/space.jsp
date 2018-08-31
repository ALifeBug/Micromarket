<%--
  Created by IntelliJ IDEA.
  User: hqs
  Date: 18-7-18
  Time: 上午10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="header.jsp"%>
<title>${sessionScope.user.nickname}</title>
<link rel="stylesheet" href="${path}/css/space.css"/>
<script src="${path}/js/space.js"></script>
    <div class="userInfo">
        <div class="top">
            <span>${sessionScope.user.nickname}</span>
            <a href="${path}/user/logout" class="btn btn-default" role="button">注 销</a>
            <a href="${path}/user/editinfo" class="btn btn-default" role="button">修改个人信息</a>
        </div>
        <hr style="width: 1000px;margin: 0;border-color: #f7f7f7"/>
        <div class="bottom">
            <table  cellspacing="4" cellpadding="5" style="color: #f7f7f7">
                <tr>
                    <td align="right"><span class="glyphicon glyphicon-envelope">我的邮箱:&nbsp;</span></td>
                    <td>${sessionScope.user.email}</td>
                </tr>
                <tr>
                    <td align="right"><span class="glyphicon glyphicon-stats">年级学院:&nbsp;</span></td>
                    <td>${sessionScope.user.grade}&nbsp;${sessionScope.user.academy}</td>
                </tr>
                <tr>
                    <td align="right"><span class="glyphicon glyphicon-phone">手机号码:&nbsp;</span></td>
                    <td>${sessionScope.user.phone}</td>
                </tr>
                <tr>
                    <td align="right"><span class="glyphicon glyphicon-send">其他联系:&nbsp;</span></td>
                    <td>
                        <c:if test="${!empty sessionScope.user.contact}">
                            ${sessionScope.user.contact}
                        </c:if>
                        <c:if test="${empty sessionScope.user.contact}">
                            (无)
                        </c:if>
                    </td>
                </tr>
            </table>
        </div>
    </div>

    <div class="products">
        <ul class="tab-group"><li class="tab active"><a title="#sell">我的店铺</a></li>
            <li class="tab"><a title="#buy">我的订单</a></li>
            <li class="tab"><a title="#sold">我的出货</a></li>
            <li class="tab"><a title="#bought">我淘到的</a></li>
        </ul>
        <div class="tab-content">
            <div id="sell" >
                <c:if test="${!empty sell}">
                    <div class="scroll">
                        <table class="table table-hover" style="color: #f7f7f7;">
                            <thead>
                                <tr>
                                    <td>名称</td>
                                    <td>价格</td>
                                    <td>上架时间</td>
                                    <td><span class="glyphicon glyphicon-Eye-open">热度</span></td>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="product" items="${sell}">
                                <tr>
                                    <td><a href="${path}/detail?productNum=${product.number}">${product.name}</a></td>
                                    <td>￥${product.price}</td>
                                    <td><fmt:formatDate value="${product.upTime}"  pattern="yyyy-MM-dd HH:mm"/></td>
                                    <td>${product.browserCount}人感兴趣</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
                <c:if test="${empty sell}">
                    <div class="empty">您的店铺还是空空如也哦</div>
                </c:if>
                <a href="${path}/user/addProduct">
                    <button class="btn btn-primary addBtn">点击添加商品</button>
                </a>
            </div>
            <div id="buy">
                <c:if test="${!empty buy}">
                    <div class="scroll">
                        <table class="table table-hover" style="color: #f7f7f7;">
                            <thead>
                            <tr>
                                <td>商品</td>
                                <td>价格</td>
                                <td>卖家</td>
                                <td>电话</td>
                                <td>下单时间</td>
                                <td>订单状态</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="product" items="${buy}">
                                <tr style="line-height: 20px">
                                    <td><a href="${path}/detail?productNum=${product.number}">${product.name}</a></td>
                                    <td>￥${product.price}</td>
                                    <td><a  onclick="viewspace(${map[product.number].sellerphone})" href="#">${product.seller}</a></td>
                                    <td>${map[product.number].sellerphone}</td>
                                    <td><fmt:formatDate value="${map[product.number].time}"  pattern="yyyy-MM-dd HH:mm"/></td>
                                    <c:if test="${map[product.number].state eq 'waiting'}">
                                        <td style="color: #007bff">等待中</td>
                                    </c:if>
                                    <c:if test="${map[product.number].state eq 'missed'}">
                                        <td style="color: #5a6268">没抢到</td>
                                    </c:if>
                                    <c:if test="${map[product.number].state eq 'got'}">
                                        <td style="color:#28a745">抢到了</td>
                                    </c:if>
                                    <c:if test="${map[product.number].state eq 'waiting'}">
                                        <td><a id="${product.number}" style="color: #dc3545;" href="#" class="cancel">取消订单</a></td>
                                    </c:if>
                                    <c:if test="${(map[product.number].state eq 'missed') || (map[product.number].state eq 'got')}">
                                        <td><a id="${map[product.number].id}" style="color: #17a2b8;" href="javascript:void(0)" class="delete">删除订单</a></td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
                <c:if test="${empty buy}">
                    <div class="empty">您还没有任何订单哦</div>
                </c:if>
            </div>
            <div id="sold">
                <c:if test="${!empty sold}">
                    <div  class="scroll">
                        <table class="table table-hover" style="color: #f7f7f7;">
                            <thead>
                            <tr>
                                <td>名称</td>
                                <td>价格</td>
                                <td>买家</td>
                                <td>上架时间</td>
                                <td>成交时间</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="product" items="${sold}">
                                <tr>
                                    <td><a href="${path}/detail?productNum=${product.number}">${product.name}</a></td>
                                    <td>￥${product.price}</td>
                                    <td>${product.buyer}</td>
                                    <td><fmt:formatDate value="${product.upTime}"  pattern="yyyy-MM-dd HH:mm"/></td>
                                    <td><fmt:formatDate value="${product.dealTime}"  pattern="yyyy-MM-dd HH:mm"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
                <c:if test="${empty sold}">
                    <div class="empty">您还未卖出任何商品哦</div>
                </c:if>
            </div>
            <div id="bought">
                <c:if test="${!empty bought}">
                    <div class="scroll">
                        <table class="table table-hover" style="color: #f7f7f7;">
                            <thead>
                            <tr>
                                <td>名称</td>
                                <td>价格</td>
                                <td>卖家</td>
                                <td>上架时间</td>
                                <td>成交时间</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="product" items="${bought}">
                                <tr>
                                    <td><a href="${path}/detail?productNum=${product.number}">${product.name}</a></td>
                                    <td>￥${product.price}</td>
                                    <td>${product.seller}</td>
                                    <td><fmt:formatDate value="${product.upTime}"  pattern="yyyy-MM-dd HH:mm"/></td>
                                    <td><fmt:formatDate value="${product.dealTime}"  pattern="yyyy-MM-dd HH:mm"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
                <c:if test="${empty bought}">
                    <div class="empty">您还未买到任何商品哦</div>
                </c:if>
            </div>
        </div>
    </div>
<%@include file="footer.jsp"%>