<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%request.setCharacterEncoding("UTF-8"); %>  
  <%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

	<jsp:include page = "/layout/header.jsp">
		<jsp:param name="title" value="로그인 결과!"/>
	</jsp:include>
	
	<c:choose>
		<c:when test = "${requestScope.state == success}">
			<h3>환영합니다 ! ${requestScope.state} <br> ${sessionScope.Name}님!</h3><br>
			 <br> 회원 타입 : ${sessionScope.type} <br>
		</c:when>
		<c:otherwise>
			<h3> ${requestScope.state}, 아이디 혹은 비밀번호를 확인해주세요.</h3>
		</c:otherwise>
	</c:choose>
	
		<input type ="button" value = "메인으로" onclick = "location.href='/MrDaebak_2MM/index.jsp'">
	
	<jsp:include page = "/layout/footer.jsp"/>
