jQuery(document).ready(function ($) {

    // get categories for product menu by id_admin
    $(document).on("click", "#products", function () {
        $.ajax({
            url: 'products-type',
            data: {
                id_admin: $('#id_admin').val()
            },
            success: function (response) {
                $("#products").find(".menu").empty();
                if(response != null){
                    $.each(response, function (index, item) {
                        var urlHref = "productCategories?categoryName="+ item + "&id_admin=" + $('#id_admin').val();
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

    $("table tr").click(function(){
        $(this).addClass('selected').siblings().removeClass('selected');
        var value=$(this).data("id");
        alert(value);
    });

    $('.ok').on('click', function(e){
        alert($("#table tr.selected td:first").html());
    });

    $('.ui.selection.dropdown').dropdown();

    $('.ui.checkbox').checkbox();
});