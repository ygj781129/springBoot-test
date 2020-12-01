<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/25
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
</head>
<body>
<h1 th:inlines="text">文件上传</h1>
<form action="fileUpload" method="post" enctype="multipart/form-data">
    <p>选择文件: <input type="file" name="fileName"/></p>
    <p><input type="submit" value="提交"/></p>
</form>


<form action="download?id=页面_1.html" method="post" enctype="multipart/form-data">
    <p><input type="submit" value="下载"/></p>
</form>

<form action="http://localhost:8081/springboottest/excel/exportExcel" method="get" enctype="multipart/form-data">
    <p><input type="submit" value="导出"/></p>
</form>
<form action="http://localhost:8080/operationChannel/export" method="post" enctype="multipart/form-data">
    <p><input type="submit" value="导出6666"/></p>
</form>

<form action="http://localhost:8081/excel/readExcel" enctype="multipart/form-data" method="post">
    <br/>
    <br/>
    <button type="submit" class="btn btn-primary">导入</button>
    <input class="form-input" type="file" name="file"/>
</form>



</body>
</html>