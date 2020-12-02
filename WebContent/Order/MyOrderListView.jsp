 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8"); %>
<head>
		<meta charset="UTF-8">
		<title>내 주문 목록</title>
		<link rel="stylesheet" type="text/css" href="/MrDaebak_2MM/layout/layout.css?afte1r">
</head>
<jsp:include page="/layout/header.jsp"></jsp:include>


<body>

<c:if test = "${sessionScope.Type != 0}">
		<script>
			alert( "로그인된 고객만 이용하실 수 있습니다.");
			 window.location.replace("/MrDaebak_2MM/MainView.jsp");
		</script>
</c:if>
<c:if test = "${requestScope.altmsg != null}">
		<script>
			alert( "${requestScope.altmsg}");
		</script>
</c:if>



	<div class="container">
	<p>${sessionScope.Name} 님의 주문목록</p>
<c:choose>
	<c:when test = "${ requestScope.list == null}"> <%-- 주문이 한개라도 없을 경우 --%>
		<H2>마지막 페이지입니다.</H2>
	</c:when> 
	<c:otherwise> <%-- 주문이 한개라도 있을경우 --%>
		<table>
			<tr align = "center">
				<th>순번</th>
				<th>주문자</th>
				<th>주문시간</th>
				<th>상태</th>
				<th>가격</th>
				<th>주문ID</th>
			</tr>
			<c:forEach var = "dto" items = "${ requestScope.list }"  varStatus="status">
			<tr align = "center">
				<td>${currentpage*10 +status.index - 9 }</td>
				
				<td width="20%" align = "left"><a href = "ReadMyOrder.myorderlist?OrdNo=${dto.no}">${dto.name }</a></td>
				<td style="font-size:0.7rem;">${dto.orderTime }</td>
				<td><c:if test="${dto.status == 0}">준비중</c:if>
					<c:if test="${dto.status == 1}">준비완료</c:if>
					<c:if test="${dto.status == 2}">배달중</c:if>
					<c:if test="${dto.status == 3}">취소</c:if>
					<c:if test="${dto.status == 4}">배달완료</c:if></td>
				<td style="font-size:0.8rem;">${dto.totalPrice }</td>
				<td style="font-size:0.8rem;">${dto.memberID }</td>
				
			</tr>	
			</c:forEach>
			<tr>
				<td colspan = 6 align = "center">
				<c:if test = "${currentpage > 3 }">
					<input class="btn-page" type ="Button" value = "이전" onclick = "location.href = 'OrderList.orderlist?page=${currentpage-3 }'">
				</c:if>
				<c:if test = "${currentpage > 2 }">
					<input class="btn-page" type ="Button" value = "${currentpage-2 }" onclick = "location.href = 'MyOrderList.myorderlist?page=${currentpage-2 }'">	
				</c:if>
				<c:if test = "${currentpage > 1 }">
					<input class="btn-page" type ="Button" value = "${currentpage-1 }" onclick = "location.href = 'MyOrderList.myorderlist?page=${currentpage-1 }'">
				</c:if>
				${currentpage }
				<c:if test = "${currentpage < totalpage }">
					<input class="btn-page" type ="Button" value = "${currentpage+1 }" onclick = "location.href = 'MyOrderList.myorderlist?page=${currentpage+1 }'">
				</c:if>
				<c:if test = "${currentpage+1 < totalpage }">
					<input class="btn-page" type ="Button" value = "${currentpage+2 }" onclick = "location.href = 'MyOrderList.myorderlist?page=${currentpage+2 }'">
				</c:if>
				<c:if test = "${currentpage+2 < totalpage }">
					<input class="btn-page" type ="Button" value = "다음" onclick = "location.href = 'MyOrderList.myorderlist?page=${currentpage+3 }'">
				</c:if>
				</td>
			</tr>			
		</table>
	</c:otherwise>
</c:choose>
</div>
</body>
<jsp:include page="/layout/footer.jsp" />