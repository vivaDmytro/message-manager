$(document).ready(function() {
    $.get("/messages/", function(data) {
        $.each(data, function( key, value ) {
            var newRow = "<tr id='" + value.id + "'>" +
                "<td>" + value.user + "</td>" +
                "<td>" + value.text + "</td>" +
                "<td><button class='edit'>Edit</button><button class='delete'>Delete</button></td>" +
                "</tr>";
            $(newRow).appendTo($("#content #messages"));
        });
    });
});