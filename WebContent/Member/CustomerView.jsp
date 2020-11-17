<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8"); %>
<head>
		<meta charset="UTF-8">
		<title>Mr.Daebak</title>
		<link rel="stylesheet" type="text/css" href="/MrDaebak_2MM/layout/layout.css?after">
</head>
<body>
<jsp:include page = "/layout/header.jsp">
		<jsp:param name="title" value="로그인 결과!"/>
</jsp:include>


<c:if test = "${requestScope.altmsg != null}">
		<script>
			alert( "${requestScope.altmsg}");
		</script>
</c:if>
	
<div class="container">
	<div class = "main-menu">
		<form class="menu" action = "Read.myprofile" method = "post">
			<button class="menu menu-submit" type="submit">내 정보 확인</button>
		</form>
		<button class="menu" type="button" onclick="location.href='/MrDaebak_2MM/Member/signUpView.jsp'">주문하기</button>
		<button class="menu" type="button" onclick="location.href='/MrDaebak_2MM/Order/Order.jsp'">주문기록 확인</button>
	</div>
	
		
</div>

<jsp:include page = "/layout/footer.jsp"/>
</body>
