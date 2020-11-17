 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8"); %>
<jsp:include page="/layout/header.jsp">
	<jsp:param name = "title" value = "게시판" />
</jsp:include>



<c:choose>
	<c:when test = "${ requestScope.list == null}"> <%-- 게시글이 하나도 없을 경우 --%>
		<H2>게시글이 없습니다.</H2>
	</c:when> 
	<c:otherwise> <%-- 게시글이 한 개라도 있을 경우 --%>
		<caption><h2>게시판</h2></caption>
		<table border = "1" width = "100%">
			<tr align = "center">
				<th>회원 번호</th>
				<th>회원 ID</th>
				<th>회원 명</th>
				<th>회원 분류</th>
			</tr>
			<c:forEach var = "dto" items = "${ requestScope.list }">
			<tr align = "center">
				<th>${dto.no }</th>
				<th width="47%" align = "left"><a href = "MemberRead.managemem?brdNo=${dto.no}">${dto.id }</th>
				<th>${dto.name }</th>
			<c:choose>
				<c:when test = "${dto.type == 1} ">
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
					<input type ="Button" value = "이전" onclick = "location.href = 'MemberList.managemem?page=${currentpage-3 }'">
				</c:if>
				<c:if test = "${currentpage > 2 }">
					<input type ="Button" value = "${currentpage-2 }" onclick = "location.href = 'MemberList.managemem?page=${currentpage-2 }'">	
				</c:if>
				<c:if test = "${currentpage > 1 }">
					<input type ="Button" value = "${currentpage-1 }" onclick = "location.href = 'MemberList.managemem?page=${currentpage-1 }'">
				</c:if>
				${currentpage }
				<c:if test = "${currentpage < totalpage }">
					<input type ="Button" value = "${currentpage+1 }" onclick = "location.href = 'MemberList.managemem?page=${currentpage+1 }'">
				</c:if>
				<c:if test = "${currentpage+1 < totalpage }">
					<input type ="Button" value = "${currentpage+2 }" onclick = "location.href = 'MemberList.managemem?page=${currentpage+2 }'">
				</c:if>
				<c:if test = "${currentpage+2 < totalpage }">
					<input type ="Button" value = "다음" onclick = "location.href = 'MemberList.managemem?page=${currentpage+3 }'">
				</c:if>
				</td>
			</tr>			
		</table>
	</c:otherwise>
</c:choose>
<jsp:include page="/layout/footer.jsp" />