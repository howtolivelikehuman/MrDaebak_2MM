const dinnerInfo=['프렌치 디너는 커피 한잔, 와인 한잔, 샐러드, 고기가 제공됩니다.',
				  '잉글리시 디너는 에그 스크램블, 베이컨, 빵, 스테이크가 제공됩니다.',
				  '샴페인 축제 디너는 2인 기준식사이며, 샴페인 한병, 바게트빵 4개, 커피 한 포트가 제공됩니다.'];
const styleInfo=['발렌타인 스타일은 작은 하트 모양과 큐피드가 장식된 접시에 냅킨과 함께 제공됩니다.(+10000원)',
				  '심플 스타일은 상자 접시에 냅킨이 플라스틱 쟁반에 제공되며, 와인은 플라스틱 잔으로 제공됩니다.(무료)',
				  '그랜드 스타일은 도자기 접시와 컵, 흰색 면 냅킨이 나무 쟁반에 제공됩니다.(+10000원)',
				  '딜럭스 스타일은 은 쟁반에 꽃들이 있는 작은 꽃병, 도자기 접시와 린넨 냅킨이 함께 제공됩니다.(+20000원)'];
function click_dinner(data){
	document.getElementById('styles').style.display="flex";
	document.getElementsByClassName('valentine')[0].style.display="inline-block";
	document.getElementsByClassName('simple')[0].style.display="inline-block";
	document.getElementsByClassName('valentine')[1].style.display="inline-block";
	document.getElementsByClassName('simple')[1].style.display="inline-block";
	var temp=document.getElementById('dinner-info');
	switch(data){
		case 'french':
			temp.innerText=dinnerInfo[0];
			break;
		case 'english':
			temp.innerText=dinnerInfo[1];
			break;
		case 'champagne':
			temp.innerText=dinnerInfo[2];
			document.getElementsByClassName('valentine')[0].style.display="none";
	document.getElementsByClassName('simple')[0].style.display="none";
	document.getElementsByClassName('valentine')[1].style.display="none";
	document.getElementsByClassName('simple')[1].style.display="none";
			break;
	}
}

function click_style(data){
	document.getElementById('details').style.display="flex";
	var temp=document.getElementById('style-info');
	switch(data){
		case 'valentine':
			temp.innerText=styleInfo[0];
			break;
		case 'simple':
			temp.innerText=styleInfo[1];
			break;
		case 'grand':
			temp.innerText=styleInfo[2];
			break;
		case 'deluxe':
			temp.innerText=styleInfo[3];
			break;
	}
}