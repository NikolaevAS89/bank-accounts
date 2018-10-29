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
            if (this.readyState == 4 && this.status == 200) {
                document.getElementById(elementId).innerHTML = this.responseText;
            }
        };
        xhttp.open("GET", path, true);
        xhttp.send();
    } catch (err) {
        alert(err.toString());
    }
}