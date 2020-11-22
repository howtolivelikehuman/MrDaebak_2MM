<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>MrDaebak_2MM : ${param.title != null ? param.title : "나의 페이지!" }</title>
		<link rel="stylesheet" type="text/css" href="./layout.css?ssss">
	</head>
	<body>
		<div class = "header">
		<span id="logo">Mr.Daebak</span>
			<c:choose>
				<c:when test = "${sessionScope.Name == null}" >
					<a class="top-menu" href = "/MrDaebak_2MM/index.jsp"> Main </a>
					<a class="top-menu" href = "/MrDaebak_2MM/Member/loginView.jsp"> LOGIN </a>
					<a class="top-menu" href = "/MrDaebak_2MM/Member/signUpView.jsp"> JOIN  </a>
				</c:when>
				<c:otherwise>
				<form class="top-menu" action = "Result.login" method = "post"> <input id="logout" type = "submit" value = "MAIN"> </form>
				<form class="top-menu" action = "Logic.logout" method = "post"> <input id="logout" type = "submit" value = "LOGOUT"> </form>
				</c:otherwise>
			</c:choose>
		</div>
		
	</body>
			

