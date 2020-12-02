
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

		<header class = "header">
			<span id="logo">Mr.Daebak</span>
			<c:choose>
				<c:when test = "${sessionScope.Name == null}" >
					<a class="top-menu" href = "/MrDaebak_2MM/MainView.jsp"> MAIN </a>
					<a class="top-menu" href = "LoginView.login"> LOGIN </a>
					<a class="top-menu" href = "signUpView.signup"> JOIN  </a>
				</c:when>
				
				<c:otherwise>
				<a class="top-menu" href = "/MrDaebak_2MM/MainView.jsp">  MAIN </a>
				<a class="top-menu" href = "Logic.logout">LOGOUT</a>
				</c:otherwise>
				
			</c:choose>
		</header>
