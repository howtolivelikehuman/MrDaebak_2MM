<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page = "/layout/header.jsp">
	<jsp:param name="title" value="로그인"/>
</jsp:include>


<form action = "Logic.login" method = "post">
	<table border = "1">
	<caption><h3>로그인</h3></caption>
		<tr>
			<th>ID</th>
		<c:choose>
				<c:when test = "${cookie.rememberID.value == null }">
			<td><input type = "text" name = "user_id" placeholder="ID를 입력하세요." required></td>
				</c:when>
			<c:otherwise>
				<td><input type = "text" name = "user_id" value =  ${cookie.rememberID.value }></td>
			</c:otherwise>
		</c:choose>
		</tr>
		<tr>
			<th>PASSWORD</th>
			<td><input type = "password" name = "user_password" placeholder="PASSWORD를 입력하세요." required></td>
			</tr>
		<tr>
			<td colspan ="2"  align = "center"><input type = "submit" value = "로그인">
		</tr>

	</table>
</form>

<jsp:include page = "/layout/footer.jsp"/>