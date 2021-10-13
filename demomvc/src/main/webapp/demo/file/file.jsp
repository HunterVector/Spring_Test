<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.FileInfoDto"%>
<html>
<head>
    <meta charset="utf-8">
    <script language="javascript" src="../../test/jquery-1.7.1.min.js"></script>
    <script language="javascript">
        function deleteFile(fileId) {
            // $.post("/demomvc/demo/testDelete",{fileId:fileId},function (data, status) {
            //     if (data.respCode == "DEL-000") {
            //         alert(data.respMsg);
            //         refresh();
            //     }
            //     else {
            //         alert(data);
            //     }
            // })
            $.ajax({
                type: "POST",
                url: "/demomvc/demo/testDelete",
                data: {fileId:fileId},
                dataType: "json",
                success: function(data){
                    if (data.respCode == "DEL-000") {
                        alert(data.respMsg);
                        refresh();
                    }
                    else {
                        alert(data);
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(errorThrown);
                }
            });
        }
        function refresh() {
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
<h1>File Page</h1>
<table>
    <button id = "${fileDto.fileId}" onclick="refresh()">refresh</button>
    <% List list=(List) session.getAttribute("fileList");
       for(int i=0;i<list.size();i++) {
           FileInfoDto file=( FileInfoDto)list.get(i);
           pageContext.setAttribute("fileDto", file);
    %>
    <tr>
        <%--<td>--%>
            <%--<%=file.getFileId() %>--%>
        <%--</td>--%>
        <td>
            <%--<%=file.getFileName() %>--%>
            <%--<button type="button" onclick="downClick(${fileDto.fileId});"></button>--%>
            <a href="/demomvc/downloadAttachment?fileId=${fileDto.fileId}">${fileDto.fileName}</a>
            <button id="${fileDto.fileId}" onclick="deleteFile(${fileDto.fileId})">delete</button>
        </td>
    </tr>
    <% } %>
</table>

<h1>上传</h1>

  <form action="/demomvc/demo/upload" method="post" enctype="multipart/form-data">
      文件: <input type="file" name="testFile"> <br>
      <input type="submit" name="upload"> <br>
  </form>


</body>
</html>