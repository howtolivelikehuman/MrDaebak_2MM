<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<head>
		<meta charset="UTF-8">
		<title>로그인</title>
		<link rel="stylesheet" type="text/css" href="/MrDaebak_2MM/layout/layout.css">
</head>
<body>


	<jsp:include page = "/layout/header.jsp"></jsp:include>
	
	<c:if test = "${requestScope.altmsg != null}">
		<script>
			alert( "${requestScope.altmsg}");
		</script>
	</c:if>	
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

