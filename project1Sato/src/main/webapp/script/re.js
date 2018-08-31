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
				sendAjaxGet("info?entity=request&get=for", populateReqManagedCard);
				sendAjaxGet("info?entity=request&get=all", populateAllCard);
			}
		}
	} else {
		window.location = "login";
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
				let image = row.insertCell(5);
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
				let modal = document.getElementById('myModal');
				let requestIMG = document.createElement("img");
				let modalImg = document.getElementById("img01");
				let captionText = document.getElementById("caption");
				requestIMG.src = "data:image/jpg;base64," + res[req].image;
				image.appendChild(requestIMG);
				image.onclick = function () {
					modal.style.display = "block";
					modalImg.src = requestIMG.src;
					captionText.innerHTML = res[req].description;
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
					//ROW CREATION
					let row = requestCard.insertRow();
					let eyedee = row.insertCell(0);
					let datey = row.insertCell(1);
					let disc = row.insertCell(2);
					let amt = row.insertCell(3);
					let image = row.insertCell(4);
					let empeyedee = row.insertCell(5);
					let apButt = row.insertCell(6);
					let dnButt = row.insertCell(7);
					//row id is the request id:
					row.setAttribute("id", res[req].requestID);

					//FILL STATIC PARTS
					eyedee.innerHTML = res[req].requestID;
					datey.innerHTML = "" + res[req].requestDate;
					disc.innerHTML = "" + res[req].description;
					amt.innerHTML = res[req].amount;
					empeyedee.innerHTML = "" + res[req].requester;

					//BUTTONS for approving and denying
					let approveButt = document.createElement("button");
					let denyButt = document.createElement("button");
					approveButt.onclick = function () { approve(res[req].requestID) };
					denyButt.onclick = function () { deny(res[req].requestID) };
					approveButt.classList.add("btn");
					approveButt.classList.add("btn-success");
					approveButt.innerHTML = "Approve";
					denyButt.classList.add("btn");
					denyButt.classList.add("btn-danger");
					denyButt.innerHTML = "Deny";
					apButt.appendChild(approveButt);
					dnButt.appendChild(denyButt);

					//IMAGES
					let modal = document.getElementById('myModal');
					let requestIMG = document.createElement("img");
					let modalImg = document.getElementById("img01");
					let captionText = document.getElementById("caption");
					requestIMG.src = "data:image/jpg;base64," + res[req].image;
					image.appendChild(requestIMG);
					image.onclick = function () {
						modal.style.display = "block";
						modalImg.src = requestIMG.src;
						captionText.innerHTML = res[req].description;
					}

				}
			}
		}
	}
};
function populateAllCard(xhr) {
	if (xhr.responseText) {
		var res = JSON.parse(xhr.responseText);
		var requestCard = document.getElementById("allRequestsCard");
		if (res) {
			for (let req = 0; req < res.length; req++) {
				let row = requestCard.insertRow();
				let eyedee = row.insertCell(0);
				let datey = row.insertCell(1);
				let disc = row.insertCell(2);
				let amt = row.insertCell(3);
				let reqBy = row.insertCell(4);
				eyedee.innerHTML = res[req].requestID;
				datey.innerHTML = "" + res[req].requestDate;
				disc.innerHTML = "" + res[req].description;
				amt.innerHTML = res[req].amount;
				reqBy = res[req].requester;
			}
		}
	}
};
function approve(requestID) {
	sendAjaxGet("ads?rid=" + requestID + "&status=1", removeCard);
}
function deny(requestID) {
	sendAjaxGet("ads?rid=" + requestID + "&status=-1", removeCard);
}
function removeCard(xhr) {
	var row = document.getElementById(xhr.responseText);
	row.parentNode.removeChild(row);
}


window.onload = function () {
	sendAjaxGet("userinfo", checkifManager);
	sendAjaxGet("info?entity=request&get=by", populateReqCard);

	let modal = document.getElementById('myModal');
	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];

	// When the user clicks on <span> (x), close the modal
	span.onclick = function () {
		modal.style.display = "none";
	}
};