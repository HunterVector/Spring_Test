<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@page import="model.UserInfoDto"%>
<html>
<head>
    <meta charset="utf-8">
    <title>下载demo</title>
    <script language="javascript" src="test/jquery-1.7.1.min.js"></script>
    <script language="javascript">
        function query() {
            $.post("/demomvc/demo/testQuery",{},function (data, status) {
                window.location.href = "/demomvc/demo/file/file.jsp";
            })
        }
    </script>
</head>
<body>
<%--<% UserInfoDto user=(UserInfoDto) session.getAttribute("userInfo");--%>
   <%--pageContext.setAttribute("userDto", user);--%>
<%--%>--%>

<h1>查询</h1>
<p id="demo">查询文件列表</p>

<button type="button" onclick="query()">查询</button>

</body>
</html>