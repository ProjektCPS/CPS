jQuery(document).ready(function ($) {

    $(document).on("click", "#products", function () { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
        console.log($('#id_admin').val());
        var id_admin = $('#id_admin').val();
        $.ajax({
            url: 'products',
            data: {
                id_admin: $('#id_admin').val()
            },
            success: function (response) {
                console.log(response);
                $.each(response, function (index, item) {
                    console.log(item);
                    var $li = $("<li>").appendTo($('#nav-menu'));
                    $('<a>').text(item).appendTo($li);
                })
            },
            error: function (err) {
                console.log("error");
                console.log(err);
            }
        });
    });
});