jQuery(document).ready(function ($) {

    $(document).on("click", "#products", function () {
        $.ajax({
            url: 'products',
            data: {
                id_admin: $('#id_admin').val()
            },
            success: function (response) {
                $("#products").find(".menu").empty();
                if(response != null){
                    $.each(response, function (index, item) {
                        $('<a class="inverted blue item">').text(item).appendTo($("#products").find(".menu"));
                    })
                }
            },
            error: function (err) {
                console.log("error");
                console.log(err);
            }
        });
    });

    $('.ui.menu .ui.dropdown').dropdown({
        on: 'click'
    });

    $('.ui.menu .ui.dropdown').on('click', function () {
        $(this).siblings().removeClass('active');
    });

    $('.ui.menu a.item').on('click', function () {
        $(this).addClass('active').siblings().removeClass('active');
    });
});