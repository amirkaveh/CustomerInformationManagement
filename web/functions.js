/**
 * Created by dotinschool6 on 4/18/2017.
 */

function validateNationalID() {
    var nationalID = document.forms["naturalPersonForm"]["nationalID"].value;
    if (nationalID.length != 10) {
        alert("National ID must be 10 digits.");
        return false;
    }
    nationalID = Number(nationalID);
    var rightDigit = nationalID % 10;
    nationalID = Math.floor(nationalID / 10);
    var position = 2;
    var sum = 0;
    while (nationalID > 0) {
        var temp = nationalID % 10;
        nationalID = Math.floor(nationalID / 10);
        temp = temp * position;
        position++;
        sum += temp;
    }
    sum = sum % 11;
    if (sum >= 2) {
        sum = 11 - sum;
    }
    if (rightDigit != sum) {
        alert("National ID is not valid. Check it again!");
        return false;
    }
    return true;
}

function checkGrantRequiredFields() {
    if (document.getElementById('name').value == "") {
        alert("Name field can't be empty!")
        return false;
    }
    if (document.getElementById('minContractDuration').value == "") {
        alert("Minimum Duration field can't be empty!")
        return false;
    }
    if (document.getElementById('maxContractDuration').value == "") {
        alert("Maximum Duration field can't be empty!")
        return false;
    }
    if (document.getElementById('minContractAmount').value == "") {
        alert("Minimum Amount field can't be empty!")
        return false;
    }
    if (document.getElementById('maxContractAmount').value == "") {
        alert("Maximum Amount field can't be empty!")
        return false;
    }
    return true;
}

function addRow() {
    if (!checkGrantRequiredFields())
        return;
    var nameDiv = document.createElement('td');
    nameDiv.innerHTML = document.getElementById('name').value + '<input type="hidden" name="grantConditionNames[]" value="' + document.getElementById('name').value + '"/>';
    document.getElementById('name').value = "";
    var minDurationDiv = document.createElement('td');
    minDurationDiv.innerHTML = document.getElementById('minContractDuration').value + '<input type="hidden" name="minContractDurations[]" value="' + document.getElementById('minContractDuration').value + '"/>';
    document.getElementById('minContractDuration').value = "";
    var maxDurationDiv = document.createElement('td');
    maxDurationDiv.innerHTML = document.getElementById('maxContractDuration').value + '<input type="hidden" name="maxContractDurations[]" value="' + document.getElementById('maxContractDuration').value + '"/>';
    document.getElementById('maxContractDuration').value = "";
    var minAmountDiv = document.createElement('td');
    minAmountDiv.innerHTML = document.getElementById('minContractAmount').value + '<input type="hidden" name="minContractAmounts[]" value="' + document.getElementById('minContractAmount').value + '"/>';
    document.getElementById('minContractAmount').value = "";
    var maxAmountDiv = document.createElement('td');
    maxAmountDiv.innerHTML = document.getElementById('maxContractAmount').value + '<input type="hidden" name="maxContractAmounts[]" value="' + document.getElementById('maxContractAmount').value + '"/>';
    document.getElementById('maxContractAmount').value = "";
    var deleteDiv = document.createElement('div');
    deleteDiv.innerHTML = '<button type="submit" class="btn btn-danger" onclick="removeRow(this)">\
        <i class="glyphicon glyphicon-remove"></i>\
        </button>';

    var trDiv = document.createElement('tr');
    trDiv.appendChild(nameDiv);
    trDiv.appendChild(minDurationDiv);
    trDiv.appendChild(maxDurationDiv);
    trDiv.appendChild(minAmountDiv);
    trDiv.appendChild(maxAmountDiv);
    trDiv.appendChild(deleteDiv);
    document.getElementById('tableBody').appendChild(trDiv);
}

function removeRow(input) {
    document.getElementById('tableBody').removeChild(input.parentNode.parentNode);
}

function grantTableNotEmpty() {
    var table = document.getElementById('tableBody');
    if (table.childElementCount == 0) {
        alert("You must add one grant condition at least.");
        return false;
    }
    return true;
}