<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>KKK'S : ${param.title != null ? param.title : "나의 페이지!" }</title>
		<link rel="stylesheet" type="text/css" href="./layout.css">
	</head>
	<body>
		<div class = "header" align = "center">
			<a class="top-menu" href = "/MrDaebak_2MM/index.jsp"> Main </a>
			<c:choose>
				<c:when test = "${sessionScope.currentNickname == null}" >
					<a class="top-menu" href = "/MrDaebak_2MM/Member/loginView.jsp"> LOGIN </a>
					<a class="top-menu" href = "/MrDaebak_2MM/Member/signUpView.jsp"> JOIN  </a>
				</c:when>
				<c:otherwise>
					${sessionScope.Name}님
					<a class="top-menu" href = "/myhome/logout/logoutLogic.jsp">LOGOUT</a> 
					<a class="top-menu" href = "/myhome/mypage/mypageView.jsp">MY PAGE</a>
				</c:otherwise>
			</c:choose>
		</div>
	</body>
			

