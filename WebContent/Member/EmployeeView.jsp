<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%request.setCharacterEncoding("UTF-8"); %>
<head>
		<meta charset="UTF-8">
		<title>환영합니다!</title>
		<link rel="stylesheet" type="text/css" href="../layout/layout.css">
</head>
<body>
<div class="container">
<jsp:include page = "/layout/header.jsp">
		<jsp:param name="title" value="로그인 결과!"/>
	</jsp:include>
	<h1 id="title">환영합니다! </h1>
	<div class = "main-menu">
			
		<a class="menu" href = "/MrDaebak_2MM/Member/loginView.jsp"> 회원 관리</a>
		<a class="menu" href = "/MrDaebak_2MM/Member/signUpView.jsp"> 재고 관리</a>
		<a class="menu" href="/MrDaebak_2MM/Order/Order.jsp">주문목록 관리</a>			
	</div>
</div>
<jsp:include page = "/layout/footer.jsp"/>
</body>
