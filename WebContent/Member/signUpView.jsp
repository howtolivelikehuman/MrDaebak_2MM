<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%request.setCharacterEncoding("UTF-8"); %>
<head>
		<meta charset="UTF-8">
		<title>회원 가입</title>
		<link rel="stylesheet" type="text/css" href="/MrDaebak_2MM/layout/layout.css">
		<script type = "text/javascript" src ="/MrDaebak_2MM/Member/script.js"></script>
</head>
<body>
	<jsp:include page = "/layout/header.jsp"></jsp:include>

<div class="container">	
	<form class="form-signUp" name = "joinForm" method = "post" action = "signUpLogic.signup">
		<div class="id-form-con">
			<input class="input" type = "text" name ="user_id" placeholder="아이디를 입력하세요." required>
			<input class="btn" type = "button" value = "중복확인" onclick = "checkId()">
		</div>
		<input class="input" type = "password" name = "user_password" placeholder="비밀번호를 입력하세요." required>
		<input class="input" type = "password" name = "user_repassword" placeholder="비밀번호를 다시 입력하세요." required>
		<input class="input" type = "text" name = "user_name" placeholder = "이름을 입력하세요." required>
		<input class="input" type = "text" name = "user_mobile" placeholder = "전화번호를 입력하세요." required>
		<input class="input" type = "text" name = "user_address" placeholder = "주소를 입력하세요." required>
		<input class = "input" type = "password" name = "user_code" placeholder = "관리자라면 관리자 코드를 입력하세요.">
		<input class="btn" type = "button" name="sbButton" value = "가입" onclick = "checkPassword()" disabled>

	</form>
</div>
<jsp:include page = "/layout/footer.jsp"/>
</body>
