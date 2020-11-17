<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%request.setCharacterEncoding("UTF-8"); %>
<head>
		<meta charset="UTF-8">
		<title>환영합니다!</title>
		<link rel="stylesheet" type="text/css" href="/MrDaebak_2MM/layout/layout.css?after">
</head>
<body>

<jsp:include page = "/layout/header.jsp">
		<jsp:param name="title" value="로그인 결과!"/>
	</jsp:include>
<div class="container">
	<div class = "main-menu">
		<form class="menu" action = "MemberList.manageMem?page=1" method = "post">
			<button class="menu menu-submit" type="submit">회원 관리</button>
		</form>
		<form class="menu" action = "" method = "post">
			<button class="menu menu-submit" type="submit">재고 관리</button>
		</form>
		<form class="menu" action = "" method = "post">
			<button class="menu menu-submit" type="submit">주문목록 관리</button>
		</form>		
	</div>
</div>
<jsp:include page = "/layout/footer.jsp"/>
</body>
