$(document).on("click", ".edit", function() {

    var rowId = $(this).closest('tr').attr("id");

    var tds = $(this).closest('tr').find('td').filter(function() {
        return $(this).find('.edit').length === 0;
    });

    if ($(this).html() === 'Edit') {
        $(this).html('Save');
        tds.prop('contenteditable', true);
    } else {
        $(this).html('Edit');
        tds.prop('contenteditable', false);

        var data = {};
        data["id"] = parseInt(rowId);
        data["text"] = tds[0].textContent;
        data["user"] = tds[1].textContent;

        $.ajax({
            type: "PUT",
            url: "/message",
            contentType: "application/json",
            data: JSON.stringify(data)
        });
    }
});