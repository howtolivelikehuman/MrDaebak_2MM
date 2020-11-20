function clickCheck(){
	const stockArray={"stock":[]};

	for (var i=0; i<document.getElementsByName("now-input").length; i=i+5){
		var no = document.getElementsByName("now-input")[i].value;
		var name=document.getElementsByName("now-input")[i+1].value;
		var price=document.getElementsByName("now-input")[i+2].value;
		var amount=document.getElementsByName("now-input")[i+3].value;
		var nextSupplyDay=document.getElementsByName("now-input")[i+34].value;
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

function clickAdd_row(){
	var table_body = document.getElementById('table_body');
	var row = table_body.insertRow(table_body.rows.length);
	var cell = new Array();
	
	cell[0] = row.insertCell(0);
	var cell0 = "<p id = 'index'>"+(table_body.rows.length)+"</p>"+"<input type = 'hidden' name = 'now-input' value = '${-1}'>";
	cell[0].innerHTML = cell0;
	
	for(var i=1; i<5; i++){
		cell[i] = row.insertCell(i);
		cell[i].innerHTML = "<input class='input' type = 'text' name = 'now-input' value = '' required>";
	}
	cell[5] = row.insertCell(5);
	cell[5].innerHTML = "<button class='delete' onclick='clickDelete_row(this)'><img src='/MrDaebak_2MM/layout/delete.png' height=20px></button>"
}

function clickDelete_row(obj){
     var tr = obj.parentNode.parentNode;
     tr.parentNode.removeChild(tr); 
}
