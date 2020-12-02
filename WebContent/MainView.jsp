<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8"); %>
<head>
	  <link rel="icon" type="image/png" href="http://example.com/myicon.png">
      <meta charset="UTF-8">
      <title>미스터 대박 디너 서비스!</title>
      <link rel="stylesheet" type="text/css" href="/MrDaebak_2MM/layout/layout.css?after">
</head>
<body>


<jsp:include page = "/layout/header.jsp">
      <jsp:param name="title" value="로그인 결과!"/>
</jsp:include>
   
<c:if test = "${requestScope.altmsg != null}">
   <script>
      alert( "${requestScope.altmsg}");
   </script>
</c:if>

<div class="container">
<c:choose>
   <c:when test = "${sessionScope.Type == 0}">
         <div class = "main-menu">
            <form class="menu" action = "Read.myprofile" method = "post">
               <button class="menu menu-submit" type="submit">내 정보 확인</button>
            </form>
           <form class="menu" action = "GetOrderForm.order" method = "post">
               <button class="menu menu-submit" type="submit">주문하기</button>
            </form>
                        <form class="menu" action = "MyOrderList.myorderlist?page=1" method = "post">
               <button class="menu menu-submit" type="submit">주문기록 확인</button>
            </form>  
         </div>
   </c:when>
   <c:when test = "${sessionScope.Type == 1}">
         <div class = "main-menu">
            <form class="menu" action = "MemberList.manageMem?page=1" method = "post">
               <button class="menu menu-submit" type="submit">회원 관리</button>
            </form>
            <form class="menu" action = "StockList.stock" method = "post">
               <button class="menu menu-submit" type="submit">재고 관리</button>
            </form>
            <form class="menu" action = "OrderList.orderlist?page=1" method = "post">
               <button class="menu menu-submit" type="submit">주문목록 관리</button>
            </form>      
         </div>
   </c:when>
   <c:otherwise>
   
	<div class = "main-menu">
		<button class="menu" type="button" onclick="location.href='LoginView.login'">로그인</button>
		<button class="menu" type="button" onclick="location.href='signUpView.signup'">회원가입</button>
		<button class="menu" type="button" onclick="location.href='GetOrderForm.order'">비회원 주문</button>
	</div>
   </c:otherwise>
</c:choose>
      </div>

<jsp:include page = "/layout/footer.jsp"/>
</body>