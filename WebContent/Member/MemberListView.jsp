 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8"); %>
<head>
		<meta charset="UTF-8">
		<title>Mr.Daebak</title>
		<link rel="stylesheet" type="text/css" href="/MrDaebak_2MM/layout/layout.css?after">
</head>
<jsp:include page="/layout/header.jsp">
	<jsp:param name = "title" value = "회원 목록" />
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
		<H2>마지막 페이지입니다.</H2>
	</c:when> 
	<c:otherwise> <%-- 회원이 한명이라도 있을경우 --%>
		<table>
			<tr align = "center">
				<th>회원 번호</th>
				<th>회원 ID</th>
				<th>회원 명</th>
				<th>회원 분류</th>
			</tr>
			<c:forEach var = "dto" items = "${ requestScope.list }">
			<tr align = "center">
				<th>${dto.no }</th>
				<th width="47%" align = "left"><a href = "MemberRead.manageMem?MemNo=${dto.no}">${dto.id }</a></th>
				<th>${dto.name }</th>
				
			<c:choose>
				<c:when test = "${dto.type == 1}">
					<th>Employee</th>
				</c:when>
				<c:otherwise>
					<th>Customer</th>
				</c:otherwise>
			</c:choose>
			
			</tr>	
			</c:forEach>
			<tr>
				<td colspan = 5 align = "center">
				<c:if test = "${currentpage > 3 }">
					<input type ="Button" value = "이전" onclick = "location.href = 'MemberList.manageMem?page=${currentpage-3 }'">
				</c:if>
				<c:if test = "${currentpage > 2 }">
					<input type ="Button" value = "${currentpage-2 }" onclick = "location.href = 'MemberList.manageMem?page=${currentpage-2 }'">	
				</c:if>
				<c:if test = "${currentpage > 1 }">
					<input type ="Button" value = "${currentpage-1 }" onclick = "location.href = 'MemberList.manageMem?page=${currentpage-1 }'">
				</c:if>
				${currentpage }
				<c:if test = "${currentpage < totalpage }">
					<input type ="Button" value = "${currentpage+1 }" onclick = "location.href = 'MemberList.manageMem?page=${currentpage+1 }'">
				</c:if>
				<c:if test = "${currentpage+1 < totalpage }">
					<input type ="Button" value = "${currentpage+2 }" onclick = "location.href = 'MemberList.manageMem?page=${currentpage+2 }'">
				</c:if>
				<c:if test = "${currentpage+2 < totalpage }">
					<input type ="Button" value = "다음" onclick = "location.href = 'MemberList.manageMem?page=${currentpage+3 }'">
				</c:if>
				</td>
			</tr>			
		</table>
	</c:otherwise>
</c:choose>
</div>
</body>
<jsp:include page="/layout/footer.jsp" />