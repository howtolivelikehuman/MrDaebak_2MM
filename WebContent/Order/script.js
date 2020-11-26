var dinnerIdx=0;
var styleIdx=0;
var totalPrice=0;
var cartNum=0;
var order={ "name":"",
			"mobile":"",
			"address":"",
			"totalPrice":0,
			"cardNum":"",
			"isDiscounted":"",
			"deliverydateTime":"",
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

	for (var i in menu.availableStyle[data-1]){
		var j=Number(i)+Number(1);
		tag+="<input type='radio' name='style-radio' value="+j+" onclick=click_style(this.value)>"+style.name[i];
	} 
	target.innerHTML=tag;
}

function click_style(data){
	console.log(data);
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
		temp.orderedDetailList.push(eachDetail);
		temp.price+=(detailPrice[eachDetail['name']])*(eachDetail.ea);
	}
	temp.price+=Number(style.price[styleIdx]);
	totalPrice+=Number(temp.price);
	order.cart.push(temp);
	document.getElementById('total-price').innerHTML=totalPrice;
}
function click_cart(){
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
	document.getElementById('cart-num').innerHTML=order.cart.length;
	document.getElementById('total-price').innerHTML=totalPrice;
}
function click_cart_delete(){
	if(order.cart.length==0){
		return;
	}else{
		var temp=order.cart.pop();
		totalPrice-=Number(temp.price);
	}
	document.getElementById('total-price').innerHTML=totalPrice;
	document.getElementById('cart-num').innerHTML=order.cart.length;
}
function click_order(){
	push_cart();
	document.getElementById('final-box').style.display='inline';
}
function do_order(){
	order.name=document.getElementById('name').value;
	order.mobile=document.getElementById('mobile').value;
	order.address=document.getElementById('address').value;
	var temp="";
	for(var i=0;i<document.getElementsByName('card-num').length;i++){
		temp+=document.getElementsByName('card-num')[i].value;
	}
	order.cardNum=temp;
	order.info=document.getElementById('extra-info').value;
	order.deliverydateTime=document.getElementById('hope-time').value;
}