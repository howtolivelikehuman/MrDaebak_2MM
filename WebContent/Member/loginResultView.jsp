<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%request.setCharacterEncoding("UTF-8"); %>  
  <%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page = "/layout/header.jsp">
	<jsp:param name="title" value="로그인 결과!"/>
</jsp:include>

<c:choose>
	<c:when test = "${sessionScope.Name != null}">
		<h3>환영합니다 ! <br> ${sessionScope.Name}님!</h3><br>
		<input type ="button" value = "회원탈퇴" onclick = "location.href='/myhome/signout/signoutView.jsp'">
	</c:when>
	<c:otherwise>
		<h3> 아이디 혹은 비밀번호를 확인해주세요.</h3>
	</c:otherwise>
</c:choose>

	<input type ="button" value = ex"메인으로" onclick = "location.href='/MrDaebak_2MM/index.jsp'">

<jsp:include page = "/layout/footer.jsp"/>