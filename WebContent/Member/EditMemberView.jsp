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
	<jsp:param name="title" value="회원정보 확인"/>
</jsp:include>
<div class="container">
<c:set var = "dto" value = "${requestScope.member}" scope = "page"/>
<c:remove var ="dto" scope = "request"/>
<div>
	<form action = "MemberEdit.manageMem" method = "post">
		<input type = "hidden" name = "user_no" value = "${dto.no}">
		<input type = "hidden" name = "user_type" value = "${dto.type}">
		<table border = "0">
			<tr>
				<th>ID</th>
				<td>${dto.id}</td>
			</tr>
			
			<tr>
				<th rowspan = "2">Password</th>
				<td> <input class="input" type = "password" name = "user_password" value = "${dto.pw }" required></td>
			</tr>
			
			<tr>
				<td> <input class="input" type = "password" name = "user_repassword" value = "${dto.pw }" required></td>
			</tr>
			
			<tr>
				<th>Name</th>
				<td><input class="input" type = "text" name = "user_name" value = "${dto.name }" required></td>
			</tr>
			
			<tr>
				<th>Mobile</th>
				<td><input class="input" type = "text" name = "user_mobile" value = "${dto.mobile }" required></td>
			</tr>
			<tr>
				<th>Address</th>
				<td><input class="input" type = "text" name = "user_address" value = "${dto.address }" required></td>
			</tr>
			
			<c:choose>
				<c:when test = "${dto.type == 1}">
					<th>직책</th>
					<td><input class="input" type = "text" name = "user_position" value = "${dto.position}" required></td>
				</c:when>
				<c:otherwise>
					<th>단골고객</th>
					<td><input class="input" type = "text" name = "user_isVip" value = "${dto.vip}" required></td>
				</c:otherwise>
			</c:choose>
		</table>
		<button class="btn" type = "submit" >수정</button>
		</form>
		
		
		<form action = "MemberDelete.manageMem" style="margin-top:3px; margin-bottom:3px;"  method = "post">  
			<input type = "hidden" name = "user_no" value = "${dto.no}">
			<button class="btn" type = "submit" >삭제</button> 
		</form>
		
		<form action = "Check.manageMem" method = "post"> <button class="btn" type = "submit" >확인</button> </form>
	</div>
	</div>
<jsp:include page = "/layout/footer.jsp"/>
</body>