<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<head>
		<meta charset="UTF-8">
		<title>Mr.Daebak</title>
		<link rel="stylesheet" type="text/css" href="../layout/layout.css?after">
</head>
<body>
<jsp:include page = "/layout/header.jsp">
			<jsp:param name="title" value="로그인"/>
		</jsp:include>
	<div class="container">
		<form class="form-login" action = "Logic.login" method = "post">
			<c:choose>
						<c:when test = "${cookie.rememberID.value == null }">
					<input type = "text" class="input" name = "user_id" placeholder="ID" required>
						</c:when>
					<c:otherwise>
						<input type = "text" class="input" name = "user_id" value =  ${cookie.rememberID.value } placeholder="ID" required>
					</c:otherwise>
				</c:choose>
					<input type = "password" class="input" name = "user_password" placeholder="PASSWORD" required>
					<button class="btn" type="submit">로그인</button>
		</form>
	</div>
	<jsp:include page = "/layout/footer.jsp"/>
</body>




<%-- <form action = "Logic.login" method = "post">
			<table border = "1">
			<caption><h3>로그인</h3></caption>
				<tr>
					
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
					
					<td><input type = "password" name = "user_password" placeholder="PASSWORD를 입력하세요." required></td>
					</tr>
				<tr>
					<td colspan ="2"  align = "center"><input type = "submit" value = "로그인">
				</tr>
		
			</table>
		</form> --%>