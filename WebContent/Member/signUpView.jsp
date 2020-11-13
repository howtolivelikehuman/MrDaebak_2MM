<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page = "/layout/header.jsp">
	<jsp:param name="title" value="회원가입"/>
</jsp:include>


<form action = "Logic.SignUp" method = "post">
	<table border = "1">
	<caption><h3>회원가입</h3></caption>
		<tr>
			<th>이름<th>
			<input type="text" name = "user_name" placeholder="이름을 입력하세요." required>
		</tr>
		<tr>
			<th>ID</th>
		<c:choose>
				<c:when test = "${cookie.rememberID.value == null }">
			<td><input type = "text" name = "user_id" placeholder="ID를 입력하세요." required></td>
				</c:when>
			<c:otherwise>
				<td><input type = "text" name = "user_id" value =  ${cookie.rememberID.value }></td>
			</c:otherwise>
		</c:choose>
		</tr>
		<tr>
			<th>PASSWORD</th>
			<td><input type = "password" name = "user_password" placeholder="PASSWORD를 입력하세요." required></td>
		</tr>
		<tr>
			<th>전화번호<th>
			<input type="text" name = "user_mobile" placeholder="전화번호를 입력하세요." required>
		</tr>
		<tr>
			<th>주소<th>
			<input type="text" name = "user_address" placeholder="주소를 입력하세요." required>
		</tr>
		<tr>
			<td colspan ="2"  align = "center"><input type = "submit" value = "회원가입">
		</tr>

	</table>
</form>

<jsp:include page = "/layout/footer.jsp"/>