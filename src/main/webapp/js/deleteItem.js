$(document).on("click", ".delete", function() {

    var tr = $(this).closest('tr');
    var messageId = $(this).closest('tr').attr('id');

    $.ajax({
        url: '/messages/' + messageId,
        type: 'DELETE',
        success: function(result) {
            tr.remove ();
        }
    });
});