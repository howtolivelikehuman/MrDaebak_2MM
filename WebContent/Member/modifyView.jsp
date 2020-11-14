<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page = "/layout/header.jsp">
	<jsp:param name="title" value="나의 페이지"/>
</jsp:include>

<c:set var = "dto" value = "${requestScope.member}" scope = "page"/>
<c:remove var ="dto" scope = "request"/>


	<c:if test = "${dto == null }">
		<script>
			alert("오류가 발생하였습니다");
			history.back();
		</script>
	</c:if>
	
	<form action = "modifyLogic.modify" method ="post">
		<input type = "hidden" name = "user_no" value = "${dto.no}">
		<table border = "1">
			<tr>
				<th>ID</th>
				<td>${dto.id}</td>
			</tr>
			
			<tr>
				<th rowspan = "2">Password</th>
				<td> <input type = "password" name = "user_password" value = "${dto.pw }" required></td>
			</tr>
			
			<tr>
				<td> <input type = "password" name = "user_repassword" value = "${dto.pw }" required></td>
			</tr>
			
			<tr>
				<th>Name</th>
				<td><input type = "text" name = "user_nickname" value = "${dto.name }" required></td>
			</tr>
			
			<tr>
				<th>Mobile</th>
				<td><input type = "text" name = "user_mobile" value = "${dto.mobile }" required></td>
			</tr>
			<tr>
				<th>Address</th>
				<td><input type = "text" name = "user_address" value = "${dto.address }" required></td>
			</tr>
			
			<c:if test = "${dto.id != sessionScope.Id}"> 
				<tr>
					<th>이사람은 직원입니다.</th>
				</tr>	
			</c:if>
			
			<tr>
				<td colspan = "2" align = "center">
					<input type = "button" value = "확인" onclick = "location.href='/MrDaebak_2MM'">
					<input type = "submit" value = "수정">
				</td>
			</tr>		
		</table>
	</form>


<jsp:include page = "/layout/footer.jsp"/>