<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8"); %>
<head>
		<meta charset="UTF-8">
		<title>Mr.Daebak</title>
		<link rel="stylesheet" type="text/css" href="/MrDaebak_2MM/layout/layout.css?afterss">
		<script type ="text/javascript" src = "script.js"></script>
</head>
<body>

<jsp:include page = "/layout/header.jsp">
		<jsp:param name="title" value="주문하기"/>
	</jsp:include>
<div class="container">
	<div id="order">
		<div id="dinners">
			<h3>Dinner</h3>
			<input type="radio" name="dinner" value="HTML" onclick="click_dinner(this.value)">1
			<input type="radio" name="dinner" value="CSS" onclick="click_dinner(this.value)">2
			<input type="radio" name="dinner" value="웹디자인" onclick="click_dinner(this.value)">3
		</div>
		<div id="styles">
			<h3>Style</h3>
			<input type="radio" name="style" value="HTML" onclick="click_style(this.value)">4
			<input type="radio" name="style" value="CSS"  onclick="click_style(this.value)">5
			<input type="radio" name="style" value="웹디자인" onclick="click_style(this.value)">6
		</div>
		<div id="details">
			<h3>Detail</h3>
			<input type="radio" name="detail" value="HTML">7
			<input type="radio" name="detail" value="CSS">8
			<input type="radio" name="detail" value="웹디자인">9
		</div>
	</div>
</div>
<jsp:include page = "/layout/footer.jsp"/>
</body>