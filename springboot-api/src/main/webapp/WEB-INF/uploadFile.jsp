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
<br>
<div >
    <form method="POST" enctype="multipart/form-data" action="http://localhost:8081/upload/fileUpload">
        <table>
            <tr><td>上传文件222:</td><td><input type="file" name="fileName" /></td></tr>
            <tr><td></td><td><input type="submit" value="上传" /></td></tr>
        </table>

    </form>
</div>
<br><br>

<div style="margin-left: 5%">
    <h3 style="text-align: center">文件列表</h3>
    <table border="1">
        <thead>
        <tr style="background-color: beige">
            <td>文件名</td>
            <td>文件ID</td>
            <td>contentType</td>
            <td>文件大小</td>
            <td>上传时间</td>
            <td>md5</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <tr th:if="${files.size()} eq 0">
            <td colspan="3">没有文件信息！！</td>
        </tr>
        <tr th:each="http://localhost:8081/file/file : ${files}">
            <td><a th:href="'files/'+${file.id}" th:text="${file.name}" /></td>
            <td th:text="${file.id}" ></td>
            <td th:text="${file.contentType}" ></td>
            <td th:text="${file.size}" ></td>
            <td th:text="${file.uploadDate}" ></td>
            <td th:text="${file.md5}" ></td>
            <td><a target="_blank" th:href="@{/view(id=${file.id})}">预览</a>|<a th:href="@{/delete(id=${file.id})}">删除</a></td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>