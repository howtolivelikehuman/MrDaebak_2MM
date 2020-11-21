<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8"); %>
<head>
      <meta charset="UTF-8">
      <title>환영합니다!</title>
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

<c:choose>
   <c:when test = "${sessionScope.Type == 0}">
      <div class="container">
         <div class = "main-menu">
            <form class="menu" action = "Read.myprofile" method = "post">
               <button class="menu menu-submit" type="submit">내 정보 확인</button>
            </form>
            <!-- <form class="menu" action = "" method = "post">
               <button class="menu menu-submit" type="submit">주문하기</button>
            </form> --><!-- 나중에 이걸로 바꿔야함 아래버튼 -->
            <button class="menu" type="button" onclick="location.href='/MrDaebak_2MM/Order/OrderView.jsp'">주문하기</button>
            <button class="menu" type="button" onclick="location.href='/MrDaebak_2MM/Order/Order.jsp'">주문기록 확인</button>
         </div>
      </div>
   </c:when>
   <c:otherwise>
      <div class="container">
         <div class = "main-menu">
            <form class="menu" action = "MemberList.manageMem?page=1" method = "post">
               <button class="menu menu-submit" type="submit">회원 관리</button>
            </form>
            <form class="menu" action = "StockList.stock" method = "post">
               <button class="menu menu-submit" type="submit">재고 관리</button>
            </form>
            <form class="menu" action = "" method = "post">
               <button class="menu menu-submit" type="submit">주문목록 관리</button>
            </form>      
         </div>
      </div>
   </c:otherwise>
</c:choose>


<jsp:include page = "/layout/footer.jsp"/>
</body>