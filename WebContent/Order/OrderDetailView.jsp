<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<head>
		<meta charset="UTF-8">
		<title>Mr.Daebak</title>
		<link rel="stylesheet" type="text/css" href="/MrDaebak_2MM/layout/layout.css?ssss">
</head>
<script>
	function setStatus(value){
		document.getElementsByName('order_status')[0].value=document.getElementById(value).value;
	}
</script>
<body>
<jsp:include page = "/layout/header.jsp">
	<jsp:param name="title" value="주문정보 확인"/>
</jsp:include>
<div class="container">

<c:set var = "dto" value = "${requestScope.order}" scope = "page"/>
<c:remove var ="dto" scope = "request"/>
	<c:if test = "${dto == null }">
		<script>
			alert("오류가 발생하였습니다");
			history.back();
		</script>
	</c:if>
<div>
	<form action = "UpdateOrder.orderlist" method = "post">
		<input type = "hidden" name = "order_no" value = "${dto.no}">
		<table border = "0">
			<tr>
				<th>주문자</th>
				<td>${dto.name}</td>
			</tr>
			<tr>
				<th>전화번호 </th>
				<td>${dto.mobile}</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>${dto.address}</td>
			</tr>
			<tr>
				<th>카드번호</th>
				<td>${dto.cardNum}</td>
			</tr>
			<tr>
				<th>주문시각</th>
				<td>${dto.orderTime}</td>
			</tr>
			
			<tr>
				<th>주문ID</th>
				<td>${dto.memberID}</td>
			</tr>
			<tr>
				<th>기타 주문사항</th>
				<td>${dto.info}</td>
			</tr>
			<tr> <!-- 주문사항 0,1,2,3,4에 따라서 준비중, 준비완료, 배달중, 취소, 배달완료 -->
				<th>상태</th>
				<td> <input style="display:none;" class="input" name = "order_status" value = "${dto.status }" required>
				<select onchange="setStatus(this.value)" id="status">
					    <option id='0' value='0' <c:if test="${dto.status == 0}">selected</c:if>>준비중</option>
					    <option id='1' value='1' <c:if test="${dto.status == 1}">selected</c:if>>준비완료</option>
					    <option id='2' value='2' <c:if test="${dto.status == 2}">selected</c:if>>배달중</option>
					    <option id='3' value='3' <c:if test="${dto.status == 3}">selected</c:if>>취소</option>
					    <option id='4' value='4' <c:if test="${dto.status == 4}">selected</c:if>>배달완료</option>
					</select></td>
			</tr>
			<tr>
				<th>단골 할인</th>
				<td>${dto.isDiscounted}</td>
			</tr>
			<tr>
				<th>가격</th>
				<td>${dto.totalPrice}</td>
			</tr>
			
			
			<c:forEach var = "dto_list" items = "${ dto.cart }"  varStatus="status">
			
			<tr align = "center">
			<td colspan =2> </td>
			</tr>
			
			<tr align = "center">
				<th>메뉴</th>
				<td>${dto_list.menu}</td>
			</tr>
			<tr align = "center">
				<th>스타일</th>
				<td>${dto_list.style}</td>
			</tr>
			<tr align = "center">
				<th>세부 구성</th>
				<td>${dto_list.orderedDetailList}</td>
			</tr>
			<tr align = "center">
				<th>가격</th>
				<td>${dto_list.price}</td>
			</tr>
			</c:forEach>
			
		</table>
		<button class="btn" type = "submit" >수정</button>
		</form>
		
		<div class="id-form-con">
		<form action = "DeleteOrder.orderlist" style="margin-top:3px; margin-bottom:3px;"  method = "post">  
			<input type = "hidden" name = "order_no" value = "${dto.no}">
			<button class="btn" type = "submit" >삭제 </button> 
		</form>
		<form action = "Result.orderlist" method = "post"> <button class="btn" type = "submit" >확인</button> </form>
		</div>
		
	</div>
	</div>
<jsp:include page = "/layout/footer.jsp"/>
</body>