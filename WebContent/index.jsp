<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%request.setCharacterEncoding("UTF-8"); %>
<head>
		<meta charset="UTF-8">
		<title>Mr.Daebak</title>
		<link rel="stylesheet" type="text/css" href="./layout/layout.css">
</head>
<body>
<div class="container">
	<h1 display="inline" id="title">Mr.Daebak Dinner Service</h1>
	<div class = "main-menu">
			
		<a class="menu" href = "/MrDaebak_2MM/Member/loginView.jsp"> 로그인 </a>
		<a class="menu" href = "/MrDaebak_2MM/Member/signUpView.jsp"> 회원가입</a>
		<a class="menu" href="/MrDaebak_2MM/Order/Order.jsp">비회원 주문</a>			
	</div>
</div>
</body>
