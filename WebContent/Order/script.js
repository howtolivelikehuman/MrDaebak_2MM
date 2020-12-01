var dinnerIdx=-1;
var styleIdx=-1;
var totalPrice=0;
var order={ "name":"",
         "mobile":"",
         "address":"",
         "totalPrice":0,
         "cardNum":"",
         "isVip":false,
         "info":"",
         "cart":[/* {
            "menu":"",
            "style":"",
            "orderedDetailList":[],
            "price":0
           } */]
      }
function resetVar(){
   dinnerIdx=-1;
   styleIdx=-1;
}
function click_dinner(data){
   dinnerIdx=data-1;
   var temp=document.getElementById('dinner-info');
   temp.innerText=menu.info[data-1];
   var tag="";
   var target=document.getElementById('style-check');

   for (var i=0;i<menu.availableStyle[dinnerIdx].length;i++){
      var tmp=menu.availableStyle[dinnerIdx][i];
      var j=Number(tmp);
      tag+="<input type='radio' name='style-radio' value="+j+" onclick=click_style(this.value)>"+style.name[tmp-1];
   }
   target.innerHTML=tag;
}

function click_style(data){
   styleIdx=data-1;
   var temp=document.getElementById('style-info');
   temp.innerText=style.info[data-1];
   for(var i=0;i<document.getElementsByClassName('num-box').length;i++){
      document.getElementsByClassName('num-box')[i].value=0;
   }
   for(var i=0;i<menu.menuDetailListNo[dinnerIdx].length;i++){
      document.getElementById(menu.menuDetailListNo[dinnerIdx][i]).value=menu.menuDetailListEa[dinnerIdx][i];
   }
}
function push_cart(){
   var temp={"menu":"",
            "style":"",
            "orderedDetailList":[],
            "price":0};
   temp.menu=menu.name[dinnerIdx];
   temp.style=style.name[styleIdx];
   var temp2=document.getElementsByClassName('detail-name');
   var temp3=document.getElementsByClassName('num-box');
   for(var i=0;i<temp2.length;i++){
      var eachDetail={};
      eachDetail['name']=temp2[i].textContent;
      eachDetail['ea']=Number(temp3[i].value);
      if(eachDetail['ea']==0&&-1===menu.menuDetailListNo[dinnerIdx].findIndex((e) => e === temp3[i].getAttribute('id'))){
         continue;
      }
      temp.orderedDetailList.push(eachDetail);
      temp.price+=(detailPrice[eachDetail['name']])*(eachDetail.ea);
   }
   temp.price+=Number(style.price[styleIdx]);
   if(member.isVip){
      temp.price=temp.price*0.9;
   }
   totalPrice+=Number(temp.price);
   order.cart.push(temp);
}
function check_info(){
      var name=document.getElementById("name").value;
      var mobile=document.getElementById("mobile").value;
      var address=document.getElementById("address").value;
      if(name==''){
         alert('주문자 이름을 입력해주세요');
         return false;
      }
      if(mobile==''){
         alert('전화번호를 입력해주세요');
         return false;
      }
      if(address==''){
         alert('배달주소를 입력해주세요');
         return false;
      }
      return true;
}
function click_cart(){
   if(styleIdx==-1||dinnerIdx==-1){
      alert("디너와 스타일을 선택해주세요.");
      return;
   }
   push_cart();
   var radio1=document.getElementsByName('dinner-radio');
   for(var i=0;i<radio1.length;i++){
      if(radio1[i].checked==true){
         radio1[i].checked=false;
      }
   }
   var target=document.getElementById('style-check');
   var target2=document.getElementById('style-info');
   target.innerHTML="";
   target2.innerText="원하는 스타일을 선택하세요.";
   for(var i=0;i<document.getElementsByClassName('num-box').length;i++){
      document.getElementsByClassName('num-box')[i].value=0;
   }
   post_cart();
   resetVar();
}
function click_cart_delete(){
   if(order.cart.length==0){
      return;
   }else{
      var temp=order.cart.pop();
      if(order.cart.length==0){
         document.getElementById('final-box').style.display='none';
      }
      totalPrice-=Number(temp.price);
   }
   post_cart();
}
function click_order(){
   if(order.cart.length==0&&(styleIdx==-1||dinnerIdx==-1)){
      alert("디너와 스타일을 선택해주세요.");
      return;
   }else if(order.cart.length>0&&(styleIdx==-1||dinnerIdx==-1)){
      document.getElementById('final-box').style.display='inline';
      document.getElementById('cart-num').innerHTML=order.cart.length;
      resetVar();
      return;
   }
   push_cart();
   document.getElementById('final-box').style.display='inline';
   post_cart();
   resetVar();
}
function post_cart(){
   var str="";
   for(var i=0;i<order.cart.length;i++){
      str+="<p id='each-cart'>"+"디너: "+order.cart[i].menu+ "<br> 스타일: "+order.cart[i].style+"<br> 메뉴: ";
      var tmp=order.cart[i].orderedDetailList;
      for(var j=0;j<tmp.length;j++){
         str+='('+tmp[j].name+" "+tmp[j].ea+')';
      }
      str+="</p>"
   }
   document.getElementById('cart-num').innerHTML=order.cart.length;
   document.getElementById('cart-info').innerHTML=str;
   document.getElementById('total-price').innerHTML=totalPrice;
}
function do_order(){
   if(!check_info()){
      return;
   }
   order.name=document.getElementById('name').value;
   order.mobile=document.getElementById('mobile').value;
   order.address=document.getElementById('address').value;
   var temp="";
   for(var i=0;i<document.getElementsByName('card-num').length;i++){
      temp+=document.getElementsByName('card-num')[i].value;
   }
   order.totalPrice=totalPrice;
   order.cardNum=temp;
   order.info=document.getElementById('extra-info').value;
   order.isVip=member.isVip;

   do_post();
}
function do_post(){
   var form = document.createElement("form");
    form.setAttribute("charset", "UTF-8");
     form.setAttribute("method", "Post");  //Post 방식
     form.setAttribute("action", "DoOrder.order"); //요청 보낼 주소 채워넣으면 댐

   var hiddenField = document.createElement("input");
    hiddenField.setAttribute("type", "hidden");
    hiddenField.setAttribute("name", "order"); //stockArray 의 value에 스트링 형식으로 저장
   hiddenField.setAttribute("value", JSON.stringify(order));
   console.log(hiddenField.getAttribute("value"));
   
   form.appendChild(hiddenField);
   document.body.appendChild(form);
   form.submit();
}