var choosedElement = null;
var choosedElementColor = null;

function swapAccount() {
    resetAction();
    debetField = document.getElementById("debetId");
    creditField = document.getElementById("creditId");
    var debetId = debetField.innerHTML;
    var creditId = creditField.innerHTML;
    debetField.innerHTML = creditId;
    creditField.innerHTML = debetId;
}

function chooseAccount(id) {
    if (choosedElement != null) {
        choosedElement.innerHTML = id;
    }
    resetAction();
}

function changeAccount(id) {
    resetAction();
    choosedElement = document.getElementById(id);
    choosedElementColor = choosedElement.style.backgroundColor;
    choosedElement.style.backgroundColor = "#F22";
}

function create_new(description, path) {
    try {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                showError(this.responseText);
            }
        };
        xhttp.open("POST", path, true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send("description=" + description);
        load("south", "/public/v1/bank/accounts");
    } catch (err) {
        showError(err.toString());
    }
}

function createNewAccount() {
    resetAction();
    elDesc = document.getElementById("newAccountDescription");
    describe = elDesc.value;
    if (describe == null || describe.length <= 0) {
        showError("Описание счета не может быть пустым");
    } else if (describe.length > 255) {
        showError("Описание счета не может быть длинее 255 символов");
    } else {
        create_new(describe, "/public/v1/bank/account");
    }
}

function addNewCashbox() {
    resetAction();
    elDesc = document.getElementById("newCashboxDescription");
    describe = elDesc.value;
    if (describe == null || describe.length <= 0) {
        showError("Описание кассы не может быть пустым");
    } else if (describe.length > 255) {
        showError("Описание кассы не может быть длинее 255 символов");
    } else {
        create_new(describe, "/public/v1/bank/cashbox");
    }
}

function newDocument() {
    resetAction();
    debetId = parseInt(document.getElementById("debetId").innerHTML);
    creditId = parseInt(document.getElementById("creditId").innerHTML);
    if (isNaN(debetId) || isNaN(creditId)) {
        showError("Для создания проводки тредуется указать оба счета");
        return;
    }
    if (debetId == creditId) {
        showError("Для создания проводки тредуется указать разные счета");
        return;
    }
    strSumma = document.getElementById("summa").value;
    summa = parseFloat(strSumma);
    if (isNaN(summa)) {
        showError("Для создания проводки требуется указать сумму в виде числа");
        return;
    }
    if (strSumma.search("^[0-9]{1,6}(\.[0-9]{1,2})?$") <= -1) {
        showError("Для создания проводки требуется указать валидную сумму");
        return;
    }

    try {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                showError(this.responseText);
            }
        };
        xhttp.open("POST", "/public/v1/bank/payments", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send("debetId=" + debetId+"&creditId="+creditId+"&summa="+summa);
        load("south", "/public/v1/bank/accounts");
    } catch (err) {
        showError(err.toString());
    }
}

function showError(errMsg) {
    elError = document.getElementById("errorbox");
    elError.innerHTML = errMsg;
    elError.hidden = false;
}

function resetAction() {
    elError = document.getElementById("errorbox");
    elError.hidden = true;
    if (choosedElement != null) {
        choosedElement.style.backgroundColor = choosedElementColor;
        choosedElement = null;
    }
}