<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%request.setCharacterEncoding("UTF-8"); %>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<head>
		<meta charset="UTF-8">
		<title>Mr.Daebak</title>
		<link rel="stylesheet" type="text/css" href="/MrDaebak_2MM/layout/layout.css?ssss">
		<script type = "text/javascript" src ="script.js"></script>
</head>

<jsp:include page = "/layout/header.jsp">
	<jsp:param name="title" value="회원 가입 결과!"/>
</jsp:include>

<div class="container">	
	<c:choose>
		<c:when test = "${param.result}">
			<h3>회원가입에 감사드립니다.</h3>
			<input type = "button" value = "로그인" onclick = "location.href='/MrDaebak_2MM/Member/loginView.jsp'">
		</c:when>
		<c:otherwise>
			<h3>회원가입에 실패했습니다.</h3>
			<input type = "button" value = "뒤로가기" onclick = "history.back()">
		</c:otherwise>
	</c:choose>
</div>

<jsp:include page = "/layout/footer.jsp"/>