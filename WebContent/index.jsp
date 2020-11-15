<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%request.setCharacterEncoding("UTF-8"); %>
<head>
		<meta charset="UTF-8">
		<title>Mr.Daebak</title>
		<link rel="stylesheet" type="text/css" href="./layout/layout.css">
</head>
<body>
<jsp:include page = "/layout/header.jsp">
		<jsp:param name="title" value="로그인 결과!"/>
	</jsp:include>
<div class="container">
	<div class = "main-menu">	
		<p class="menu"><a href = "/MrDaebak_2MM/Member/loginView.jsp"> 로그인 </a></p>
		<p class="menu"><a href = "/MrDaebak_2MM/Member/signUpView.jsp"> 회원가입</a></p>
		<p class="menu"><a href="/MrDaebak_2MM/Order/Order.jsp">비회원 주문</a></p>
	</div>
</div>
<jsp:include page = "/layout/footer.jsp"/>
</body>