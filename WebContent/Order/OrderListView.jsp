 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8"); %>
<head>
		<meta charset="UTF-8">
		<title>Mr.Daebak</title>
		<link rel="stylesheet" type="text/css" href="/MrDaebak_2MM/layout/layout.css?after1">
</head>
<jsp:include page="/layout/header.jsp">
	<jsp:param name = "title" value = "주문 목록" />
</jsp:include>

<script>
	var status=[];
	<c:forEach var = "dto" items = "${ requestScope.list }"  varStatus="status">
	</c:forEach>
</script>
<body>
<c:if test = "${requestScope.altmsg != null}">
		<script>
			alert( "${requestScope.altmsg}");
		</script>
</c:if>

	<div class="container">
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
				<th>${currentpage*10 +status.index - 9 }</th>
				
				<th width="20%" align = "left"><a href = "ReadOrder.orderlist?OrdNo=${dto.no}">${dto.name }</a></th>
				<th style="font-size:0.7rem;">${dto.orderTime }</th>
				<th>
					<c:if test="${dto.status==0}">
					준비중
					</c:if>
					
					<c:if test="${dto.status ==1}">
					준비완료
					</c:if>
					<c:if test="${dto.status ==2}">
					배달중
					</c:if>
					<c:if test="${dto.status ==3}">
					취소
					</c:if>
					<c:if test="${dto.status ==4}">
					배달완료
					</c:if>
				</th>
				<th style="font-size:0.8rem;">${dto.totalPrice }</th>
				<th style="font-size:0.8rem;">${dto.memberID }</th>
				
			</tr>	
			</c:forEach>
			<tr>
				<td colspan = 6 align = "center">
				<c:if test = "${currentpage > 3 }">
					<input type ="Button" value = "이전" onclick = "location.href = 'OrderList.orderlist?page=${currentpage-3 }'">
				</c:if>
				<c:if test = "${currentpage > 2 }">
					<input type ="Button" value = "${currentpage-2 }" onclick = "location.href = 'OrderList.orderlist?page=${currentpage-2 }'">	
				</c:if>
				<c:if test = "${currentpage > 1 }">
					<input type ="Button" value = "${currentpage-1 }" onclick = "location.href = 'OrderList.orderlist?page=${currentpage-1 }'">
				</c:if>
				${currentpage }
				<c:if test = "${currentpage < totalpage }">
					<input type ="Button" value = "${currentpage+1 }" onclick = "location.href = 'OrderList.orderlist?page=${currentpage+1 }'">
				</c:if>
				<c:if test = "${currentpage+1 < totalpage }">
					<input type ="Button" value = "${currentpage+2 }" onclick = "location.href = 'OrderList.orderlist?page=${currentpage+2 }'">
				</c:if>
				<c:if test = "${currentpage+2 < totalpage }">
					<input type ="Button" value = "다음" onclick = "location.href = 'OrderList.orderlist?page=${currentpage+3 }'">
				</c:if>
				</td>
			</tr>			
		</table>
	</c:otherwise>
</c:choose>
</div>
</body>
<jsp:include page="/layout/footer.jsp" />