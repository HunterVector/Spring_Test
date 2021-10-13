<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="utf-8">
    <script language="javascript" src="../test/jquery-1.7.1.min.js"></script>
    <script language="javascript">
        function query() {
            $.post("/demomvc/demo/testQuery",{},function (data, status) {
                window.location.href = "/demomvc/demo/file/file.jsp";
            })
        }
    </script>
    
    <style>
        table td{
            vertical-align:top;
            border:solid 1px #888;
            padding:10px;
        }
    </style>
</head>
<body>
<h1>Up Success Page</h1>
<table>
    <tr>
        <td><button type="button" onclick="query()">查询</button></td>
    </tr>
</table>
</body>
</html>