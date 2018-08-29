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

function populatePage(xhr) {
	if (xhr.responseText) {
		var res = JSON.parse(JSON.parse(xhr.responseText));
		if (res) {
			//Do this for every employee
			document.getElementById("profileId").innerText = "Employee ID: " + res.employeeID;
			document.getElementById("profileName").innerText = res.firstName + " " + res.lastName;
			document.getElementById("profileEmail").innerText = res.emailAdd;
			//console.log("is manager? " + res.isManager);
			if (res.isManager) {
				//change size of yourRequests card
				var yourRequestsCard = document.getElementById("yourRequests")
				yourRequestsCard.classList.remove("col-lg-9");
				yourRequestsCard.classList.add("col-lg-6");
				//show all manager cards
				var managerCards = document.getElementsByClassName("manager");
				for (let mc = 0; mc < managerCards.length; mc++) {
					managerCards[mc].style.display = "inline";
				}
				sendAjaxGet("http://localhost:8084/project1Sato/info?entity=employee&get=managed", populateManCard);
				sendAjaxGet("http://localhost:8084/project1Sato/info?entity=request&get=for", populateReqManagedCard);
			}
		}
	} else {
		window.location = "http://localhost:8084/project1Sato/login";
	}
};

function populateManCard(xhr) {
	//console.log("Running populate man card")
	if (xhr.responseText) {
		//console.log(xhr.responseText);
		var res = JSON.parse(xhr.responseText);
		var managedList = document.getElementById("employeesManaged");
		if (res) {
			for (let emp = 0; emp < res.length; emp++) {
				var name = res[emp].firstName + " " + res[emp].lastName;
				var entry = document.createElement('li');
				entry.classList.add("list-group-item");
				entry.appendChild(document.createTextNode(name));
				managedList.appendChild(entry);
			}
		}
	}
};
function populateReqCard(xhr) {
	if (xhr.responseText) {
		var res = JSON.parse(xhr.responseText);
		var requestCard = document.getElementById("requestsSubmittedCard");
		if (res) {
			for (let req = 0; req < res.length; req++) {
				let row = requestCard.insertRow();
				let eyedee = row.insertCell(0);
				let datey = row.insertCell(1);
				let disc = row.insertCell(2);
				let amt = row.insertCell(3);
				let appr = row.insertCell(4);
				eyedee.innerHTML = res[req].requestID;
				datey.innerHTML = "" + res[req].requestDate;
				disc.innerHTML = "" + res[req].description;
				amt.innerHTML = res[req].amount;
				if (res[req].approvalStatus == 0) {
					appr.innerHTML = "Pending";
				} else if (res[req].approvalStatus == 1) {
					appr.innerHTML = "Approved";
				} else if (res[req].approvalStatus == -1) {
					appr.innerHTML = "Denied";
				}
			}
		}
	}
};
function populateReqManagedCard(xhr) {
	if (xhr.responseText) {
		var res = JSON.parse(xhr.responseText);
		var requestCard = document.getElementById("requestsManagedCard");
		if (res) {
			for (let req = 0; req < res.length; req++) {
				if (res[req].approvalStatus == 0) {
						let row = requestCard.insertRow();
						let eyedee = row.insertCell(0);
						let datey = row.insertCell(1);
						let empeyedee = row.insertCell(2);
						eyedee.innerHTML = res[req].requestID;
						datey.innerHTML = "" + res[req].requestDate;
						empeyedee.innerHTML = "" + res[req].requester;
				}
			}
		}
	}
};
window.onload = function () {
	sendAjaxGet("http://localhost:8084/project1Sato/userinfo", populatePage);
	sendAjaxGet("http://localhost:8084/project1Sato/info?entity=request&get=by", populateReqCard);
};
