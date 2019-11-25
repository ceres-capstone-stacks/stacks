//create function that takes in a value and passes value to the date constructor

//

function convertDate(somevalue) {
    new Date(somevalue);
    return date.toString();
}

$(document).ready(function () {
    let dateText = $(".date-span").text();
    console.log("test");
    alert(dateText);
    dateText = dateText.substr(6, 9) + "-" + dateText.substr(3, 5) + "-" + dateText.substr(0, 2);
    $(".date-span").text(dateText);
})
