<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<head>
		<meta charset="UTF-8">
		<title>개인정보 수정</title>
		<link rel="stylesheet" type="text/css" href="/MrDaebak_2MM/layout/layout.css?ssss">
</head>
<body>
<jsp:include page = "/layout/header.jsp"></jsp:include>
<div class="container">
<c:set var = "dto" value = "${requestScope.member}" scope = "page"/>
<c:remove var ="dto" scope = "request"/>


<c:if test = "${sessionScope.Type != 0 }">
		<script>
			alert( "로그인 된 고객만 이용하실 수 있습니다.");
			 window.location.replace("/MrDaebak_2MM/MainView.jsp");
		</script>
</c:if>
	
<c:if test = "${requestScope.altmsg != null}">
	<script>
		alert( "${requestScope.altmsg}");
	</script>
</c:if>

<div>
	<form action = "Update.myprofile" method = "post">
		<input type = "hidden" name = "user_no" value = "${dto.no}">

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
					<td><input class="input" type = "text" name = "user_position" value = "${dto.position }" required></td>
				</c:when>
				<c:otherwise>
					<th>단골고객</th>
					<td>
						<c:if test="${dto.vip == true}">O</c:if>
						<c:if test="${dto.vip == false}">X</c:if>
					</td>
				</c:otherwise>
			</c:choose>
			
				
		</table>
		<button class="btn" type = "submit" >수정</button>
		</form>
		
		
		<form action = "Delete.myprofile" style="margin-top:3px; margin-bottom:3px;"  method = "post">  
			<input type = "hidden" name = "user_no" value = "${dto.no}">
			<button class="btn" type = "submit" >삭제</button> 
		</form>
		
		<form action = "Check.myprofile" method = "post"> <button class="btn" type = "submit" >확인</button> </form>
	</div>
	</div>
<jsp:include page = "/layout/footer.jsp"/>
</body>