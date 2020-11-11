<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <%request.setCharacterEncoding("UTF-8"); %>
<jsp:include page = "/layout/header.jsp">
	<jsp:param name="title" value="메인페이지"/>
</jsp:include>

<H2>Welcome!</H2>
<h3>나의 홈페이지!</h3>
<h5>이곳은 메인페이지입니다.</h5>

<jsp:include page = "/layout/footer.jsp"/>
