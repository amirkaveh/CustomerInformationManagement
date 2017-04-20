/**
 * Created by dotinschool6 on 4/18/2017.
 */
function validateNationalID() {
    var nationalID = document.forms["naturalPersonForm"]["nationalID"].value;
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
        alert("NationalID is not valid. Check it again!");
        return false;
    }
    return true;
}