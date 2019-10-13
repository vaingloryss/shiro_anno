<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>成绩单</title>
</head>
<body>
<h3>学生成绩单</h3>
<%--<shiro:authenticated>
	&lt;%&ndash;获取用户名&ndash;%&gt;
	<label>
		欢迎您，
		<shiro:principal/>
	</label>
</shiro:authenticated>--%>

<shiro:user>
    <%--获取用户名--%>
    <label>
        欢迎您，
        <shiro:principal/>
    </label>
</shiro:user>

<a href="${pageContext.request.contextPath}/userController/logout">退出</a>
<table border="1" style="">
	<tr>
		<td>序号</td>
		<td>成绩</td>
		<td>等级</td>
		<td>学号</td>
		<td>姓名</td>
		<td>操作</td>
	</tr>
	<c:forEach items="${scoreItemList}" var="scoreItem">
			<tr>
		<td>${scoreItem.id }</td>
		<td>${scoreItem.score }</td>
		<td>${scoreItem.rank }</td>
		<td>${scoreItem.userId }</td>
		<td>${scoreItem.user.username}</td>
		<td>
			<shiro:hasPermission name="scoreItem:update">
				<a href="${pageContext.request.contextPath}/userController/updateScore?scoreId=${scoreItem.id }">修改</a>&nbsp;
			</shiro:hasPermission>
			<shiro:hasPermission name="scoreItem:delete">
				<a href="${pageContext.request.contextPath}/userController/deleteScore?scoreId=${scoreItem.id }">删除</a>
			</shiro:hasPermission>
        </td>
	</tr>
	</c:forEach>
</table>
</body>
</html>