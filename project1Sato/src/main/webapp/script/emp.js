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
function populateEmployeeManagedTable(xhr) {
	if (xhr.responseText) {
		//console.log(xhr.responseText);
		var res = JSON.parse(xhr.responseText);
		var table = document.getElementById("myEmployees");
		if (res) {
			for (let emp = 0,row=1; emp < res.length; emp++) {
                let row = table.insertRow();
                let name = row.insertCell(0);
                let email = row.insertCell(1);
                name.innerHTML = res[emp].firstName + " " + res[emp].lastName;
                email.innerHTML = res[emp].emailAdd;
                row++;
			}
		}
	} else {
		window.location = "http://localhost:8084/project1Sato/dash";
	}
};
function populateEmployeeTable(xhr){
    if (xhr.responseText) {
		//console.log(xhr.responseText);
		var res = JSON.parse(xhr.responseText);
		var table = document.getElementById("allEmployees");
		if (res) {
			for (let emp = 0,row=1; emp < res.length; emp++) {
                let row = table.insertRow();
                let name = row.insertCell(0);
                let email = row.insertCell(1);
                let manager = row.insertCell(2);
                name.innerHTML = res[emp].firstName + " " + res[emp].lastName;
                email.innerHTML = res[emp].emailAdd;
                //console.log(res[emp].managerID);
                manager.innerHTML= res[emp].managerName;
                row++;
			}
		}
	} else {
		window.location = "http://localhost:8084/project1Sato/dash";
	}
}

window.onload = function () {
    sendAjaxGet("http://localhost:8084/project1Sato/info?entity=employee&get=managed", populateEmployeeManagedTable);
    sendAjaxGet("http://localhost:8084/project1Sato/info?entity=employee&get=all", populateEmployeeTable);
}