$(function() {
    $( "#dialog" ).dialog({
        autoOpen: false,
        buttons: [{
            text: "Yes",
            click: function() {
                var message = $('#utext').val();
                var user = $('#uname').val();
                console.log(message);
                console.log(user);
                addItem(user, message);
                $(this).dialog("close");
                $('#utext').val("");
                $('#uname').val("");
            }
        }, {
            text: "Cancel",
            click: function() {
                $(this).dialog("close");
                $('#utext').val("");
                $('#uname').val("");
            }
        }]
    });
    $( "#opener" ).click(function() {
        $( "#dialog" ).dialog( "open" );
    });
});

function addItem(user, text) {

    var data = {};
    data["text"] = text;
    data["user"] = user;

    $.ajax({
        url: "/messages/",
        contentType: "application/json",
        type: "POST",
        data: JSON.stringify(data),
        success: function(response) {
            var newRow = "<tr id='" + response.id + "'>" +
                "<td>" + user + "</td>" +
                "<td>" + text + "</td>" +
                "<td><button class='edit'>Edit</button><button class='delete'>Delete</button></td>" +
                "</tr>";

            $(newRow).appendTo($("#content #messages"));
        },
        error: function() {
            alert("Error!");
        }
    });
}
