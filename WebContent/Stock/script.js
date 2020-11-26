function clickCheck(){
	const stockArray={"stock":[]};

	for (var i=0; i<document.getElementsByName("now-input").length; i=i+5){
		var no = document.getElementsByName("now-input")[i].value;
		var name=document.getElementsByName("now-input")[i+1].value;
		var price=document.getElementsByName("now-input")[i+2].value;
		var amount=document.getElementsByName("now-input")[i+3].value;
		var nextSupplyDay=document.getElementsByName("now-input")[i+4].value;
		if(name==''){
			document.getElementsByName("now-input")[i+1].focus();
			return;
		}else if(price==''){
			document.getElementsByName("now-input")[i+2].focus();
			return;
		}else if(amount==''){
			document.getElementsByName("now-input")[i+3].focus();
			return;
		}else if(nextSupplyDay==''){
			document.getElementsByName("now-input")[i+4].focus();
			return;
		}
		
		stockArray.stock.push({'no':no,'name':name,'price':Number(price),'amount':Number(amount),'nextSupplyDate':nextSupplyDay});
	}
	 var form = document.createElement("form");
	 form.setAttribute("charset", "UTF-8");
     form.setAttribute("method", "Post");  //Post 방식
     form.setAttribute("action", "StockEdit.stock"); //요청 보낼 주소 채워넣으면 댐

	var hiddenField = document.createElement("input");
    hiddenField.setAttribute("type", "hidden");
    hiddenField.setAttribute("name", "stockArray"); //stockArray 의 value에 스트링 형식으로 저장
	hiddenField.setAttribute("value", JSON.stringify(stockArray));
	console.log(hiddenField.getAttribute("value"));
	
	form.appendChild(hiddenField);
	
	const deletestockArray = {"deletestock" : []};
	
	for(var i=0; i<document.getElementsByName("deletestock").length; i++){
		var no = document.getElementsByName("deletestock")[i].value;
		console.log(no);
		deletestockArray.deletestock.push({'no':no})
	}
	
	var hiddenField2 = document.createElement("input");
    hiddenField2.setAttribute("type", "hidden");
    hiddenField2.setAttribute("name", "deletestockArray"); //stockArray 의 value에 스트링 형식으로 저장
	hiddenField2.setAttribute("value", JSON.stringify(deletestockArray));
	console.log(hiddenField2.getAttribute("value"));
	form.appendChild(hiddenField2);
	
	document.body.appendChild(form);
	form.submit();
}


function clickAdd_row(){
	var table_body = document.getElementById('table_body');
	var row = table_body.insertRow(table_body.rows.length);
	var cell = new Array();
	
	cell[0] = row.insertCell(0);
	var cell0 = "+<input type = 'hidden' name = 'now-input' value = '0'>";
	cell[0].innerHTML = cell0;
	
	for(var i=1; i<5; i++){
		cell[i] = row.insertCell(i);
		cell[i].innerHTML = "<input class='input' type = 'text' name = 'now-input' value = '' required>";
	}
	cell[5] = row.insertCell(5);
	cell[5].innerHTML = "<button class='delete' onclick='clickDelete_row(this, 0)'><img src='/MrDaebak_2MM/layout/delete.png' height=20px></button>"
}

function clickDelete_row(obj, num){
     var tr = obj.parentNode.parentNode;
     if(num != 0){
	 var hiddenField2 = document.createElement("input");
	     hiddenField2.setAttribute("type", "hidden");
	     hiddenField2.setAttribute("name", "deletestock");
	     hiddenField2.setAttribute("value", num);
	     console.log(hiddenField2.getAttribute("value"));
	     document.body.appendChild(hiddenField2);
     }
     tr.parentNode.removeChild(tr); 
}
