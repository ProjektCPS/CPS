jQuery(document).ready(function ($) {

    // get categories for product menu
    $(document).on("click", "#products", function () {
        $.ajax({
            url: '../../account/products-type',
            success: function (response) {
                $("#products").find(".menu").empty();
                if(response != null){
                    $.each(response, function (index, item) {
                        var urlHref = "../../account/productCategories?categoryName="+ item;
                        $('<a class="inverted blue item">').text(item)
                            .prop("href", urlHref)
                            .appendTo($("#products").find(".menu"));
                    })
                }
            },
            error: function (err) {
                console.log("error");
                console.log(err);
            }
        });
    });


    // menu dropdown
    $('.ui.menu .ui.dropdown').dropdown({
        on: 'click'
    });

    $('.ui.menu .ui.dropdown').on('click', function () {
        $(this).siblings().removeClass('active');
        $(this).siblings().removeClass('highlighted');
    });

    $('.ui.menu a.item').on('click', function () {
        $(this).addClass('active').siblings().removeClass('active');
        $(this).addClass('highlighted').siblings().removeClass('highlighted');
    });

    $('.ok').on('click', function(e){
        alert($("#table tr.selected td:first").html());
    });

    $('.ui.selection.dropdown').dropdown();

    $('.ui.checkbox').checkbox();

    // scripts for cards
    $('.special.card .image').dimmer({
        on: 'hover'
    });

    $('.star.rating').rating();

    $('.card .dimmer').dimmer({
        on: 'hover'
    });
});
