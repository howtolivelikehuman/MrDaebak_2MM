<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<head>
		<meta charset="UTF-8">
		<title>Mr.Daebak</title>
		<link rel="stylesheet" type="text/css" href="/MrDaebak_2MM/layout/layout.css?ssss">
</head>
<body>
<jsp:include page = "/layout/header.jsp">
	<jsp:param name="title" value="나의 페이지"/>
</jsp:include>
<div class="container">
<c:set var = "dto" value = "${requestScope.member}" scope = "page"/>
<c:remove var ="dto" scope = "request"/>
	<c:if test = "${dto == null }">
		<script>
			alert("오류가 발생하였습니다");
			history.back();
		</script>
	</c:if>
	
	<form action = "Update.myprofile" method = "post">
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
				<td><input type = "text" name = "user_name" value = "${dto.name }" required></td>
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
		</table>
		<input type = "submit" value = "수정"> </form>
		
		
		<form action = "Delete.myprofile" method = "post">  
			<input type = "hidden" name = "user_no" value = "${dto.no}">
			<input type = "submit" value = "삭제"> 
		</form>
		
		<form action = "Check.myprofile" method = "post"> <input type = "submit" value = "확인"> </form>
	</div>
<jsp:include page = "/layout/footer.jsp"/>
</body>