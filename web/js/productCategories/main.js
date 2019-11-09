jQuery(document).ready(function ($) {

        // get categories for product menu
        $(document).on("click", "#categorie", function () {
            $.ajax({
                url: 'products',
                success: function (response) {
                    $("#categorie").find(".menu").empty();
                    if (response != null) {
                        $.each(response, function (index, item) {
                            var urlHref = "productCategories?categoryName=" + item;
                            $('<a class="inverted blue item">').text(item)
                                .prop("href", urlHref)
                                .appendTo($("#categorie").find(".menu"));
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
    }
);
