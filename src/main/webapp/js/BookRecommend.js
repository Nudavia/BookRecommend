window.onload = function() {

	index = 0;
	n = 8
	gArr = ["#g0", "#g1", "#g2", "#g3", "#g4", "#g5", "#g6", "#g7"];
	bArr = ["#b0", "#b1", "#b2", "#b3", "#b4", "#b5", "#b6", "#b7"];

	searchButton = document.getElementById("search");
	keywordInput = document.getElementById("keyword");
	leftButton = document.getElementsByClassName("leftButton")[0]
	rightButton = document.getElementsByClassName("rightButton")[0]
	bigImgs = document.getElementsByClassName("bigImg")
	buttons = document.getElementsByClassName("little")

	change(index);

	searchButton.onclick = function() {
		var keyword = keywordInput.value
		if (keyword.trim().length > 0) {
			window.alert(keyword);
		}
	}

	keywordInput.onkeydown = function(event) {
		if (event.keyCode === 13) {
			searchButton.click()
		}
	}

	leftButton.onclick = function changeLeft() {
		index--;
		change(index);
	}

	rightButton.onclick = function changeRight() {
		index++;
		change(index);
	}

	timer = null
	dbclick = true

	for (let i = 0; i < n; i++) {
		bigImgs[i].onclick = function () {
			dbclick = !dbclick
			timer = window.setTimeout(function () {
				index = i
				change(index)
				if (dbclick) {
					window.alert(bigImgs.src)
				}
				window.clearTimeout(timer)
				dbclick = true
			}, 200)
		}

		buttons[i].onclick = function () {
			index = i
			change(index)
		}
	}

	/*业务执行*/
	function change() {
		if (index === n) {
			index = 0;
		} else if (index === -1) {
			index = n - 1;
		}
		changeButton();
		changeImg();
	}


	/*切换按钮*/
	function changeButton() {
		$(".little").removeClass("blue");
		var bStyle = bArr[index];
		$(bStyle).addClass("blue");
	}

	/*切换图片*/
	function changeImg() {
		// 首先全置为hide
		$("li").removeClass().addClass("hidden");
		// 确定左中右
		var center = gArr[index];
		var right = gArr[(index + 1) % n];
		var right2 = gArr[(index + 2) % n];
		var left = gArr[(index + n - 1) % n];
		var left2 = gArr[(index + n - 2) % n];
		// 更改对应左中右
		$(center).removeClass().addClass("center");
		$(left).removeClass().addClass("left");
		$(right).removeClass().addClass("right");
		$(left2).removeClass().addClass("left2");
		$(right2).removeClass().addClass("right2");
	}
}
