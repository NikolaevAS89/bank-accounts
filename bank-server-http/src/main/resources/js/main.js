document.addEventListener('DOMContentLoaded', load_page(), false);

function load_page() {
    load("top", "/public/v1/bank/account");
    load("north", "/public/v1/bank/cashbox");
    load("center", "/public/v1/bank/payments");
    load("south", "/public/v1/bank/accounts");
}

function load(elementId, path) {
    try {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4) {
                if (this.status == 200) {
                    document.getElementById(elementId).innerHTML = this.responseText;
                } else if (this.status >= 400) {
                    showError(this.responseText);
                }
            }
        };
        xhttp.open("GET", path, true);
        xhttp.send();
    } catch (err) {
        alert(err.toString());
    }
}

function create_new(description, path) {
    try {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4) {
                if (this.status == 200) {
                    showInfo(this.responseText);
                    load("south", "/public/v1/bank/accounts");
                } else if (this.status >= 400) {
                    showError(this.responseText);
                }
            }
        };
        xhttp.open("POST", path, true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send("description=" + description);
    } catch (err) {
        showError(err.toString());
    }
}

function showError(errMsg) {
    bttnClose = document.createElement("a");
    bttnClose.setAttribute("class", "close");
    bttnClose.setAttribute("href", "#");
    bttnClose.setAttribute("data-dismiss", "alert");
    bttnClose.setAttribute("aria-label", "close");
    bttnClose.innerHTML = '&#215;';
    msgbox = document.createElement("div");
    msgbox.className = "alert alert-danger alert-dismissible fade in";
    msgbox.innerText = errMsg;
    msgbox.appendChild(bttnClose);
    document.body.insertBefore(msgbox, document.body.firstChild);
}

function showInfo(infoMsg) {
    bttnClose = document.createElement("a");
    bttnClose.setAttribute("class", "close");
    bttnClose.setAttribute("href", "#");
    bttnClose.setAttribute("data-dismiss", "alert");
    bttnClose.setAttribute("aria-label", "close");
    bttnClose.innerHTML = "&#215;";
    msgbox = document.createElement("div");
    msgbox.className = "alert alert-success alert-dismissible fade in";
    msgbox.innerText = infoMsg;
    msgbox.appendChild(bttnClose);
    document.body.insertBefore(msgbox, document.body.firstChild);
}