 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8"); %>
<head>
		<meta charset="UTF-8">
		<title>회원 목록</title>
		<link rel="stylesheet" type="text/css" href="/MrDaebak_2MM/layout/layout.css?122">
</head>
<jsp:include page="/layout/header.jsp"></jsp:include>


<body>
<c:if test = "${requestScope.altmsg != null}">
		<script>
			alert( "${requestScope.altmsg}");
		</script>
</c:if>

	<div class="container">
<c:choose>
	<c:when test = "${ requestScope.list == null}"> <%-- 회원이 한명이라도 없을 경우 --%>
		<H2>마지막 페이지입니다.</H2>
	</c:when> 
	<c:otherwise> <%-- 회원이 한명이라도 있을경우 --%>
		<table>
			<tr align = "center">
				<th>순번</th>
				<th>회원 ID</th>
				<th>회원 명</th>
				<th>회원 분류</th>
			</tr>
			<c:forEach var = "dto" items = "${ requestScope.list }"  varStatus="status">
			<tr align = "center">
				<td>${currentpage*10 +status.index - 9 }</td>
				<td width="47%"><a href = "MemberRead.manageMem?MemNo=${dto.no}">${dto.id }</a></td>
				<td>${dto.name }</td>
				
			<c:choose>
				<c:when test = "${dto.type == 1}">
					<td>Employee</td>
				</c:when>
				<c:otherwise>
					<td>Customer</td>
				</c:otherwise>
			</c:choose>
			
			</tr>	
			</c:forEach>
			<tr>
				<td colspan = 5 align = "center">
				<c:if test = "${currentpage > 3 }">
					<input class="btn-page" type ="Button" value = "이전" onclick = "location.href = 'MemberList.manageMem?page=${currentpage-3 }'">
				</c:if>
				<c:if test = "${currentpage > 2 }">
					<input class="btn-page" type ="Button" value = "${currentpage-2 }" onclick = "location.href = 'MemberList.manageMem?page=${currentpage-2 }'">	
				</c:if>
				<c:if test = "${currentpage > 1 }">
					<input class="btn-page" type ="Button" value = "${currentpage-1 }" onclick = "location.href = 'MemberList.manageMem?page=${currentpage-1 }'">
				</c:if>
				<input class="btn-page-selected" type ="Button" value = "${currentpage }" >
				<c:if test = "${currentpage < totalpage }">
					<input class="btn-page" type ="Button" value = "${currentpage+1 }" onclick = "location.href = 'MemberList.manageMem?page=${currentpage+1 }'">
				</c:if>
				<c:if test = "${currentpage+1 < totalpage }">
					<input class="btn-page" type ="Button" value = "${currentpage+2 }" onclick = "location.href = 'MemberList.manageMem?page=${currentpage+2 }'">
				</c:if>
				<c:if test = "${currentpage+2 < totalpage }">
					<input class="btn-page" type ="Button" value = "다음" onclick = "location.href = 'MemberList.manageMem?page=${currentpage+3 }'">
				</c:if>
				</td>
			</tr>			
		</table>
	</c:otherwise>
</c:choose>
</div>
</body>
<jsp:include page="/layout/footer.jsp" />