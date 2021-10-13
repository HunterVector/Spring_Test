<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <%--<meta charset="utf-8">--%>
    <%--<title>文件系统demo</title>--%>
    <%--<script language="javascript" src="test/jquery-1.7.1.min.js"></script>--%>
    <%--<script language="javascript">--%>
        <%--function query() {--%>
            <%--$.post("/demomvc/demo/testQuery",{},function (data, status) {--%>
                <%--window.location.href = "/demomvc/demo/file/file.jsp";--%>
            <%--})--%>
        <%--}--%>
    <%--</script>--%>
</head>
<body>

<h1>登录页面</h1>
<p id="demo">欢迎登录</p>
<form action="/demomvc/demo/login" method="post">
    用户名: <input type="text" name="userCode"> <br>
    密码: <input type="password" name="userPass"> <br>
    <input type="submit" name="login"> <br>
</form>

</body>
</html>