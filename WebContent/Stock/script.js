function clickCheck(){
	const stockArray={"stock":[]};

	for (var i=0; i<document.getElementsByName("now-input").length;i=i+4){
		var name=document.getElementsByName("now-input")[i].value;
		var price=document.getElementsByName("now-input")[i+1].value;
		var amount=document.getElementsByName("now-input")[i+2].value;
		var nextSupplyDay=document.getElementsByName("now-input")[i+3].value;
		stockArray.stock.push({'key':i/4,'name':name,'price':Number(price),'amount':Number(amount),'nextSupplyDay':nextSupplyDay});
	}
	 var form = document.createElement("form");
	 form.setAttribute("charset", "UTF-8");
     form.setAttribute("method", "Post");  //Post 방식
     form.setAttribute("action", ""); //요청 보낼 주소 채워넣으면 댐

	var hiddenField = document.createElement("input");
    hiddenField.setAttribute("type", "hidden");
    hiddenField.setAttribute("name", "stockArray"); //stockArray 의 value에 스트링 형식으로 저장
	hiddenField.setAttribute("value", JSON.stringify(stockArray));
	form.appendChild(hiddenField);
	document.body.appendChild(form);
	form.submit();
}
function clickDelete(num){
	var form = document.createElement("form");
	form.setAttribute("charset", "UTF-8");
    form.setAttribute("method", "Post");  //Post 방식
    form.setAttribute("action", ""); //요청 보낼 주소 채워넣으면 댐

	var hiddenField = document.createElement("input");
    hiddenField.setAttribute("type", "hidden");
    hiddenField.setAttribute("name", "stockNo"); 
	hiddenField.setAttribute("value", num);
	form.appendChild(hiddenField);
	document.body.appendChild(form);
	form.submit();
}

function clickAdd(){
	var name=document.getElementsByName("new-input")[0].value;
	var price=document.getElementsByName("new-input")[1].value;
	var amount=document.getElementsByName("new-input")[2].value;
	var nextSupplyDay=document.getElementsByName("new-input")[3].value;
	const newStock={'name':name,'price':Number(price),'amount':Number(amount),'nextSupplyDay':nextSupplyDay};

	var form = document.createElement("form");
	form.setAttribute("charset", "UTF-8");
    form.setAttribute("method", "Post");  //Post 방식
    form.setAttribute("action", ""); //요청 보낼 주소 채워넣으면 댐

	var hiddenField = document.createElement("input");
    hiddenField.setAttribute("type", "hidden");
    hiddenField.setAttribute("name", "newStock"); 
	hiddenField.setAttribute("value", JSON.stringify(newStock));
	console.log(hiddenField.getAttribute("value"));
	form.appendChild(hiddenField);
	document.body.appendChild(form);
	form.submit();
}
