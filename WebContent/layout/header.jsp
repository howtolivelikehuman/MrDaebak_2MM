<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>KKK'S : ${param.title != null ? param.title : "나의 페이지!" }</title>
		<link rel="stylesheet" type="text/css" href="/myhome/layout/layout.css">
	</head>
	<body>
		<div align = "center">
			<div class = "header" align = "center">
			<c:choose>
				<c:when test = "${sessionScope.currentNickname == null}" >
					<a href = "/MrDaebak_2MM/Member/loginView.jsp"> LOGIN </a>
					<a  href = "/MrDaebak_2MM/Member/signUpView.jsp"> JOIN  </a>
				</c:when>
				<c:otherwise>
					${sessionScope.Name}님
					<a href = "/myhome/logout/logoutLogic.jsp">LOGOUT</a> 
					<a href = "/myhome/mypage/mypageView.jsp">MY PAGE</a>
				</c:otherwise>
			</c:choose>
			<a href = "/myhome/board/BoardList.do?page=1">BOARD</a>
			</div>
			<div class="main" align="center">
			

