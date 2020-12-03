<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%request.setCharacterEncoding("UTF-8"); %>
  <%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content = "text/html" charset="UTF-8">
<title>아이디 중복 확인</title>
<link rel="stylesheet" type="text/css" href="/MrDaebak_2MM/layout/layout.css">
<script type ="text/javascript" src = "/MrDaebak_2MM/Member/script.js"></script>
</head>
<body>
<div class="mini-container">
	<form method = "post" action ="CheckIdLogic.signup">
	<c:choose>
		<c:when test = "${param.result}" >
			&quot;${param.id }&quot; 는(은) 사용중입니다! <br>
			ID <input type ="text" name = "id">
			<input type = "submit" class="btn-check" value = "중복확인">
		</c:when>
		<c:otherwise>
		 	&quot;${param.id }&quot;는 사용 가능한 아이디입니다. <br><br>
		 	<input type = "button" class="btn-check" value = "사용" onclick = "checkIdFormClose('${param.id}')">	 		
		</c:otherwise>
	</c:choose>
	</form>
</div>
</body>
</html>