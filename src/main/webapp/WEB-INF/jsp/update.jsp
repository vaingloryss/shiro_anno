<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>修改成绩</title>
</head>
<body>
<h3>修改学生成绩</h3>
<form action="${pageContext.request.contextPath}/userController/updateScore" method="post">
    <table>
        <tr>
            <td>序号：</td>
            <td><input type="text" value="${scoreItem.id}" name="id" readonly></td>
        </tr>
        <tr>
            <td>成绩：</td>
            <td><input type="text" value="${scoreItem.score}" name="score" ></td>
        </tr>
        <tr>
            <td>等级：</td>
            <td><input type="text" value="${scoreItem.rank}" name="rank" ></td>
        </tr>
        <tr>
            <td>学号：</td>
            <td><input type="text" value="${scoreItem.userId}" name="userId" ></td>
        </tr>
        <tr>
            <td><input type="submit" value="提交" ></td>
            <td><input type="reset" value="重置" ></td>
        </tr>
    </table>
</form>
</body>
</html>
