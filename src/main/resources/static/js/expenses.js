// (function($) {
//     let request = $.ajax({'url': '/expenses.json'});
//     request.done(function (expenses) {
//         var html = '';
//         expenses.forEach(function(ad) {
//             html += '<div>';
//             html += '<h1>' + ad.title + '</h1>';
//             html += '<p>' + ad.description + '</p>';
//             html += '<p>Published by ' + ad.owner.username + '</p>';
//             html += '</div>';
//         });
//         $('#expenses').html(html);
//     });
// })(jQuery);

// $.post("/expenses.json", {
//     amount: "100",
//     date:  "2019-10-10",
//     description: "Ajax test",
//     isRegular: true,
//     type: 1
// }).done(function(data) {
//     // do something with the response
// });

// var token = $('#_csrf').attr('content');
// var header = $('#_csrf_header').attr('content');


var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
let data = {
    amount: "100",
    date:  "2019-10-10",
    description: "Ajax test",
    isRegular: true,
    type: 1
};
// let options = {
//     type: "POST",
//     contentType : 'application/json; charset=utf-8',
//     dataType : 'json',
//     url: "/expenses.json",
//     data: JSON.stringify(data)
// };
// $(document).ajaxSend(function(e, xhr) {
//     xhr.setRequestHeader(header, token);
// });

$.ajax({
    type: "POST",
    contentType : 'application/json; charset=utf-8',
    dataType : 'json',
    url: "/expenses.json",
    data: JSON.stringify(data),
    beforeSend: function(xhr) {
        xhr.setRequestHeader(header, token);
    },

    success: function(data, textStatus, jqXHR) {
        alert(data);
    },
    error: function(request, status, error) {
        alert(error);
    },
    always: function () {
        alert("this ran")
    }
});
