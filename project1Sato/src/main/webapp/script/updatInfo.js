function sendAjaxGet(url, func) {
	var xhr = new XMLHttpRequest()
		|| new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function () {
		if (this.readyState == 4 && this.status == 200) {
			func(this);
		}
	};
	xhr.open("GET", url, true);
	xhr.send();
};
function checkifManager(xhr) {
	if (xhr.responseText) {
		var res = JSON.parse(JSON.parse(xhr.responseText));
		if (res) {
			//console.log("is manager? " + res.isManager);
			if (res.isManager) {
				//showAll manager elements
				var managerCards = document.getElementsByClassName("manager");
				for (let mc = 0; mc < managerCards.length; mc++) {
					managerCards[mc].style.display = "inline";
				}
			}
			//for everyone just fill the forms with the current info
            var firstNameInput = document.getElementById("firstName");
            var lastNameInput = document.getElementById("lastName");
            var emailInput = document.getElementById("email");
            firstNameInput.value = res.firstName;
            lastNameInput.value = res.lastName;
            emailInput.value = res.emailAdd;
		}
	} else {
		window.location = "login";
	}
};
window.onload = function () {
	sendAjaxGet("userinfo", checkifManager);
};