<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<h3>用户登录</h3>
<form action="${pageContext.request.contextPath}/userController/login" method="post">
    <table>
        <tr>
            <td>用户名</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>密码</td>
            <td>
                <input type="password" name="password">
            </td>
        </tr>
            <td ></td>
            <td><input type="checkbox" name="rememberMe">remember me</td>
        <tr>
            <td>
                <input type="submit" value="提交">
            </td>
            <td>
                <input type="reset" value="重置">&nbsp;&nbsp;&nbsp;
                <label><a href="${pageContext.request.contextPath}/userController/register">立刻注册</a></label>
            </td>
        </tr>
    </table>
    <span style="color: red">${loginMSG}</span>
</form>
</body>
</html>
