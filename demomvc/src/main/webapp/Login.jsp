<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="utf-8">
    <title>网盘demo</title>
    <script language="javascript" src="test/jquery-1.7.1.min.js"></script>
    <script language="javascript">
        function login() {
            $.post("/demomvc/demo/login",{},function (data, status) {
                window.location.href = "/demomvc/demo/file/file.jsp";
            })
        }
    </script>
    <link rel="stylesheet" type="text/css" href="test/loginStyle.css">
</head>
<body>

<div class="dowebok">
    <div class="logo"></div>
    <div class="form-item">
        <input id="username" type="text" autocomplete="off" placeholder="用户名">
    </div>
    <div class="form-item">
        <input id="password" type="password" autocomplete="off" placeholder="登录密码">
    </div>
    <div class="form-item"><button id="submit" onclick="login()">登 录</button></div>
    <%--<div class="reg-bar">--%>
        <%--<a class="reg" href="javascript:">立即注册</a>--%>
        <%--<a class="forget" href="javascript:">忘记密码</a>--%>
    <%--</div>--%>
</div>


<%--<h1>登录页面</h1>--%>
<%--<p id="demo">欢迎登录</p>--%>
<%--<form action="/demomvc/demo/login" method="post">--%>
    <%--用户名: <input type="text" name="userCode"> <br>--%>
    <%--密码: <input type="password" name="userPass"> <br>--%>
    <%--<input type="submit" name="login"> <br>--%>
<%--</form>--%>

</body>
</html>