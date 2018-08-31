<%--
  Created by IntelliJ IDEA.
  User: hqs
  Date: 18-7-16
  Time: 下午8:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="header.jsp"%>
<title>用户登录/注册</title>
<script>
    var result = ${result};
    if(result=="failed")
        alert("注册失败!");
</script>
<script src="${path}/js/form.js" type="text/javascript"></script>
<link rel="stylesheet" href="${path}/css/form.css"/>
    <div class="form">
        <ul class="tab-group">
            <li class="tab active"><a title="#login">登 录</a></li>
            <li class="tab"><a title="#signup">注 册</a></li>
        </ul>
        <div class="tab-content">
            <div id="login">
                <form action="${path}/login" method="post" id="logform">

                    <div class="field-wrap">
                        <label>
                            <span class="glyphicon glyphicon-user">昵称</span><span class="req">*</span><span id="logname-error" class="error"></span>
                        </label>
                        <input type="text" name="nickname" id="nickname-log" required maxlength="15"/>
                    </div>

                    <div class="field-wrap">
                        <label>
                            <span class="glyphicon glyphicon-lock">密码</span><span class="req">*</span><span id="logpwd-error" class="error"></span>
                        </label>
                        <input type="password" id="password-log" name="password" required />
                    </div>

                    <button class="button button-block">登 录</button>

                </form>
            </div>
            <div id="signup">
                <form action="${path}/reg" method="post" id="regform">
                    <div class="field-wrap">
                       <label>
                           <span class="glyphicon glyphicon-user">昵称</span><span class="req">*</span>(不超过15个字符)&nbsp;&nbsp;<span id="name-error" class="error"></span>
                        </label>
                        <input type="text" name="nickname" id="nickname" required maxlength="15"/>
                    </div>

                    <div class="field-wrap">
                        <label>
                            <span class="glyphicon glyphicon-lock">密码</span><span class="req">*</span>(至少6个字符)<span id="password-error" class="error"></span>
                        </label>
                        <input type="password" name="password" id="password" required>
                    </div>

                    <div class="field-wrap">
                        <label>
                            <span class="glyphicon glyphicon-lock">重新输入密码</span><span class="req">*</span>&nbsp;&nbsp;<span id="repeat-error" class="error"></span>
                        </label>
                        <input type="password" id="password-repeat" required>
                    </div>

                    <div class="field-wrap">
                        <label>
                            <span class="glyphicon glyphicon-envelope">邮箱</span><span class="req">*</span>&nbsp;&nbsp;<span id="email-error" class="error"></span>
                        </label>
                        <input type="text" id="email" name="email" required />
                    </div>

                    <div class="top-row">
                        <div class="field-wrap">
                            <label>
                                <span class="glyphicon glyphicon-stats">年级</span><span class="req">*</span>
                            </label>
                            <input type="text" name="grade" required />
                        </div>

                        <div class="field-wrap">
                            <label>
                                <span class="glyphicon glyphicon-stats">学院</span><span class="req">*</span>
                            </label>
                            <input type="text" name="academy" required />
                        </div>
                    </div>

                    <div class="field-wrap">
                        <label>
                            <span class="glyphicon glyphicon-phone">电话</span><span class="req">*</span>&nbsp;&nbsp;<span id="phone-error" class="error"></span>
                        </label>
                        <input type="text" id="phone" name="phone" required />
                    </div>

                    <div class="field-wrap">
                        <label>
                            <span class="glyphicon glyphicon-send">其他联系方式(选填)</span>
                        </label>
                        <input type="text" name="contact" />
                    </div>
                    <button type="submit" class="button button-block"/>开始购物</button>
                </form>
            </div>
        </div>
    </div>
<%@include file="footer.jsp"%>