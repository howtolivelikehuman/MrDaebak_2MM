 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8"); %>
<head>
		<meta charset="UTF-8">
		<title>재고</title>
		<link rel="stylesheet" type="text/css" href="/MrDaebak_2MM/layout/layout.css?after">
</head>
<jsp:include page="/layout/header.jsp">
	<jsp:param name = "title" value = "재고" />
</jsp:include>


<body>
<c:if test = "${requestScope.altmsg != null}">
		<script>
			alert( "${requestScope.altmsg}");
		</script>
</c:if>
<div class="container">

	<c:choose>
		<c:when test = "${ requestScope.list == null}"> <%-- 회원이 한명이라도 없을 경우 --%>
			<H2>재고가 없습니다.</H2>
		</c:when> 
		<c:otherwise> <%-- 회원이 한명이라도 있을경우 --%>
		
		<form action = "StockEdit.stock" method = "post">
			<table>
				<tr align = "center">
					<th>번호</th>
					<th>이름</th>
					<th>가격</th>
					<th>수량</th>
					<th>다음 입고일</th>
				</tr>

				<c:forEach var = "dto" items = "${ requestScope.list }">
				<tr align = "center">
					<td>${dto.no }</td>
					<td><input class="input" type = "text" name = "stock_name" value = "${dto.name }" required></td>
					<td><input class="input" type = "text" name = "stock_price" value = "${dto.price }" required></td>
					<td><input class="input" type = "text" name = "stock_amount" value = "${dto.amount }" required></td>
					<td><input class="input" type = "text" name = "stock_nextSupplyDate" value = "${dto.nextSupplyDate }" required></td>
				</tr>
				</c:forEach>
			</table>
				<button class="btn" type = "submit">수정</button>
		</form>
		</c:otherwise>
	</c:choose>

	
	<!--추가-->
	<form action = "StockAdd.stock" method = "post">
		<table>
			<tr align = "center">
				<td><input class="input" type = "text" name = "stock_name" value = "" required></td>
				<td><input class="input" type = "text" name = "stock_price" value = "" required></td>
				<td><input class="input" type = "text" name = "stock_amount" value = "" required></td>
				<td><input class="input" type = "text" name = "stock_nextSupplyDate" value = "" required></td>
			</tr>
		</table>
		<button class="btn" type = "submit">추가</button>
	</form>
	
</div>	
</body>
</html>