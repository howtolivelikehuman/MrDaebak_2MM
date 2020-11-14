<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%request.setCharacterEncoding("UTF-8"); %>

	
<div class="container">
	<jsp:include page = "/layout/header.jsp">
		<jsp:param name="title" value="회원 가입 !"/>
	</jsp:include>
	<script type = "text/javascript" src = "script.js"></script>
	
	<form name = "joinForm" method = "post" action = "signUpLogic.signup">
		<table border = "1" align = "center">
			<caption><h3>회원 가입</h3></caption>
			<tr>
				<th>
					ID
				</th>
				<td>
					<input type = "text" name ="user_id" placeholder="아이디를 입력하세요." required>
					<input type = "button" value = "중복확인" onclick = "checkId()">
				</td>
			</tr>
			<tr>
				<th rowspan = "2">
					Password
				</th>
				<td>
					<input type = "password" name = "user_password" placeholder="비밀번호를 입력하세요." required>
				</td>
			</tr>
			<tr>
				<td>
					<input type = "password" name = "user_repassword" placeholder="비밀번호를 다시 입력하세요." required>
				</td>
			</tr>
			
			
			<tr>
				<th>
					Name
				</th>
				<td>
					<input type = "text" name = "user_name" placeholder = "이름을 입력하세요." required>
				</td>
			</tr>
			<tr>
				<th>
					Mobile
				</th>
				<td>
					<input type = "text" name = "user_mobile" placeholder = "닉네임을 입력하세요." required>
				</td>
			</tr>
			<tr>
				<th>
					Address
				</th>
				<td>
					<input type = "text" name = "user_address" placeholder = "주소를 입력하세요." required>
				</td>
			</tr>
			<tr>
				<td colspan = "2" align = "center">
					<script type = "text/javascript" src ="script.js"></script>
					<input type = "button" value = "가입" onclick = "checkPassword()">
					<input type = "reset" value = "초기화">
				</td>
			</tr>
		</table>
	</form>
	<jsp:include page = "/layout/footer.jsp"/>
</div>


