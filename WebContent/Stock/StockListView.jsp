 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8"); %>
<head>
		<meta charset="UTF-8">
		<title>재고</title>
		<link rel="stylesheet" type="text/css" href="/MrDaebak_2MM/layout/layout.css?after12">
		<script type ="text/javascript" src = "/MrDaebak_2MM/Stock/script.js?adssr"></script>
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
		<c:when test = "${ requestScope.list == null}"> 
			<H2>재고가 없습니다.</H2>
		</c:when> 
		<c:otherwise> 
			<table>
				<tr align = "center">
					<th>번호</th>
					<th>이름</th>
					<th>가격</th>
					<th>수량</th>
					<th>다음 입고일</th>
					<th colspan="2"></th>
				</tr>
				<tbody id = "table_body">
					<c:forEach var = "dto" items = "${ requestScope.list }" varStatus="status">
					<tr align = "center">
							<td>${status.index+1}<input type = "hidden" name = "now-input" value = "${dto.no}"></td>
							<td><input class="input" type = "text" name = "now-input" value = "${dto.name }" required></td>
							<td><input class="input" type = "text" name = "now-input" value = "${dto.price }" required></td>
							<td><input class="input" type = "text" name = "now-input" value = "${dto.amount }" required></td>
							<td><input class="input" type = "text" name = "now-input" value = "${dto.nextSupplyDate }" required></td>
							<td><button class="delete" onclick="clickDelete_row(this, ${dto.no})"><img src="/MrDaebak_2MM/layout/delete.png" width = "25px"></button></td>
					</tr>
					</c:forEach>
				</tbody>
				<tr><td colspan="5"><button class="btn" onclick="clickCheck()">저장하기</button></td>
				<td><button class="check" onclick="clickAdd_row()" ><img src="/MrDaebak_2MM/layout/check.png" height=25px></button></td>
				</tr>
			</table>
	
		</c:otherwise>
	</c:choose>
	
	
</div>
<jsp:include page = "/layout/footer.jsp"/>	
</body>
</html>