var dinnerIdx=0;
var styleIdx=0;
var totalPrice=0;
var cartNum=0;
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

function click_cart(){
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
	cartNum+=1;
	document.getElementById('cart-num').innerHTML=cartNum;
}

function click_order(){
	document.getElementById('final-box').style.display='inline';
}