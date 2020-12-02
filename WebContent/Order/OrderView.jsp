<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8"); %>
<head>
      <meta charset="UTF-8">
      <title>주문하기</title>
      <link rel="stylesheet" type="text/css" href="/MrDaebak_2MM/layout/layout.css?z1">
      <script type ="text/javascript" src = "/MrDaebak_2MM/Order/script.js?z"></script>
</head>
<script>

if('$requestCope.member' != null){
	var member={"id":'${requestScope.member.id}',"name":'${requestScope.member.name}',
			"mobile":'${requestScope.member.mobile}',"address":'${requestScope.member.address}',"isVip":'${requestScope.member.vip}'};
}
else{
	var member = {"id":'',"name":'',"mobile":'',"address":'',"isVip":false}
}

var menu={"name":[],"info":[],"availableStyle":[],"available":[],"menuDetailListNo":[],"menuDetailListEa":[],"extraDetailListNo":[],"extraDetailListEa":[]};
var style={"name":[],"price":[],"info":[]};
var detailPrice={};
<c:forEach var = "detailMenu" items = "${ requestScope.menulist[0].menuDetailList }" varStatus="status">
   detailPrice['${detailMenu.name}']=${detailMenu.price};
</c:forEach>
<c:forEach var = "extraMenu" items = "${ requestScope.menulist[0].extraDetailList }" varStatus="status">
   detailPrice['${extraMenu.name}']=${extraMenu.price};
</c:forEach>
<c:forEach var = "menu" items = "${ requestScope.menulist }" varStatus="status">
   menu.name.push('${menu.name}');
   menu.info.push('${menu.info}');
   menu.available.push('${menu.available}');
   var temp=[];
   var tempEa=[];
   <c:forEach var = "item" items = "${ menu.menuDetailList }" varStatus="status">
      temp.push('${item.stockNo}');
      tempEa.push('${item.ea}');
   </c:forEach>
   menu.menuDetailListNo.push(temp);
   menu.menuDetailListEa.push(tempEa);
   var temp2=[];
   var tempEa2=[];
   <c:forEach var = "item" items = "${ menu.extraDetailList }" varStatus="status">
      temp2.push('${item.stockNo}');
      tempEa2.push('${item.ea}');
   </c:forEach>
   menu.extraDetailListNo.push(temp2);
   menu.extraDetailListEa.push(tempEa2);
   menu.availableStyle.push(${menu.availableStyle});
</c:forEach>
<c:forEach var = "style" items = "${ requestScope.stylelist }" varStatus="status">
   style.name.push('${style.name}');
   style.info.push('${style.info}');
   style.price.push('${style.price}');
</c:forEach>
</script>
<body>

<c:if test = "${requestScope.altmsg != null}">
      <script>
         alert( "${requestScope.altmsg}");
      </script>
</c:if>

<jsp:include page = "/layout/header.jsp"></jsp:include>

<div class="container">
   <div id="order">
   
      <div id="dinners">
         <div align="center" class="text">Dinner</div>
         <div class="sub-container">
            <p id="dinner-info">원하는 디너를 선택하세요.</p>
            <div>
               <c:forEach var = "menu" items = "${ requestScope.menulist }" varStatus="status">
                  <c:choose>
		          <c:when test = "${menu.available==0}">
		            <label class="box-radio-input"><input type="radio" name="dinner-radio" value="${menu.no}"  disabled onclick="click_dinner(this.value)"><span>${menu.name}</span></label>
		          	<!--<input type="radio" name="dinner-radio" value="${menu.no}" disabled onclick="click_dinner(this.value)"><del>${menu.name}</del>-->
		          </c:when>
		          <c:otherwise>
		            <label class="box-radio-input"><input type="radio" name="dinner-radio" value="${menu.no}"  onclick="click_dinner(this.value)"><span>${menu.name}</span></label>
		          </c:otherwise>
		     	  </c:choose>
               </c:forEach>   
            </div>
         </div>
      </div>
      
      <div id="styles">
         <div align="center" class="text">Style</div>
         <div class="sub-container">
            <p id="style-info">원하는 스타일을 선택하세요. </p>
            <div id="style-check">
            </div>
         </div>
      </div>
      
      <div id="details">
         <div align="center" class="text">Detail</div>
         
         <div id="detail-box">
            <c:forEach var = "detailMenu" items = "${ requestScope.menulist[0].menuDetailList }" varStatus="status">
               <div class="detail">
                  <div class="detail-name">${detailMenu.name}</div>
                  <input class="num-box" id='${detailMenu.stockNo}' type="number" value="0" min="0" max="${ detailMenu.stockAmount}"/>
               </div>
            </c:forEach>
            <c:forEach var = "extraMenu" items = "${ requestScope.menulist[0].extraDetailList }" varStatus="status">
               <div class="detail">
                  <div class="detail-name" >${extraMenu.name}</div>
                  <input class="num-box" id='${extraMenu.stockNo }' type="number" value="0" min="0" max="${ extraMenu.stockAmount}"/>
               </div>
            </c:forEach>
         </div>
      </div>
      
      <div id="buttons">
      <button class="btn-order" onclick="click_cart()">장바구니 담기</button>
      <button class="btn-order" onclick="click_cart_delete()">장바구니 빼기</button>
      <button class="btn-order" onclick="click_order()">선택 완료</button>
      </div>
      
      <div id="texts">
      <p class="text-info">장바구니:</p>
      <div style="overflow:auto;">
      <p class="text-info" id='cart-num'></p>
      </div>
      <c:choose>
          <c:when test = "${requestScope.member.vip==true}">
             <p class="text-info">(VIP -10%)가격:</p>
            <p class="text-info" id='total-price'></p>
            </c:when>
          <c:otherwise>
             <p class="text-info">(할인 미적용)가격:</p>
            <p class="text-info" id='total-price'></p>   
         </c:otherwise>
     </c:choose>
      </div>
      <div id="cart-info">
      </div>
      <div id="final-box">
         <div id="user-inputs">
         <c:choose>
          <c:when test = "${requestScope.member==null}">
            <p class="text-info">주문자 이름:</p>
            <input type = "text" class="input" id = "name">
            <p class="text-info">전화번호:</p>
            <input type = "text" class="input" id = "mobile">
            <p class="text-info">배달 주소:</p>
            <input type = "text" class="input" id = "address">
         </c:when>
          <c:otherwise>   
             <p class="text-info">주문자 이름:</p>
            <input type = "text" class="input" id = "name" value="${requestScope.member.name}">
            <p class="text-info">전화번호:</p>
            <input type = "text" class="input" id = "mobile" value="${requestScope.member.mobile}">
            <p class="text-info">배달 주소:</p>
            <input type = "text" class="input" id = "address" value="${requestScope.member.address}">
          </c:otherwise>
        </c:choose>
            <p class="text-info">카드 번호:</p>
            <div id=card-inputs>
               <input type = "text" class="input" name = "card-num" maxlength="4">
               <p>-</p>
               <input type = "text" class="input" name = "card-num" maxlength="4">
               <p>-</p>
               <input type = "text" class="input" name = "card-num" maxlength="4">
               <p>-</p>
               <input type = "text" class="input" name = "card-num" maxlength="4">
            </div>
            <p class="text-info">요청사항:</p>
            <input type = "text" class="input" id = "extra-info" placeholder="요청사항">
         </div>
            <button class="btn" id='order-btn' onclick="do_order()">주문 완료 </button>
         </div>
      </div>
</div>
<jsp:include page = "/layout/footer.jsp"/>      

</body>