window.onload = function() {

	searchButton = document.getElementById("search");
	keywordInput = document.getElementById("keyword");
	searchButton.onclick = function() {
		var keyword = keywordInput.value
		if (keyword.trim().length > 0) {
			window.alert(keywordInput.value);
		}
	}

	keywordInput.onkeydown = function(event) {
		if (event.keyCode === 13) {
			searchButton.onclick()
		}
	}
}
