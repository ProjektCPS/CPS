jQuery(document).ready(function ($) {

    // get categories for product menu
    $(document).on("click", "#products", function () {
        $.ajax({
            url: '../../account/products-type',
            success: function (response) {
                $("#products").find(".menu").empty();
                if (response != null) {
                    $.each(response, function (index, item) {
                        var urlHref = "../../account/productCategories?categoryName=" + item.nazov + "&categoryId=" + item.idTypu;
                        $('<a class="inverted blue item">').text(item.nazov)
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

    $('.ui.avatarImg').popup({
        position: 'left center'
        }
    );
});

class RowDataDiscount {
    constructor(id, name, value, dateFrom, dateTo, discountTypeFormatted) {
        this._id = id;
        this._name = name;
        this._value = value;
        this._dateFrom = dateFrom;
        this._dateTo = dateTo;
        this._discountTypeFormatted = discountTypeFormatted;
    }

    get id() {
        return this._id;
    }

    get name() {
        return this._name;
    }

    get value() {
        return this._value;
    }

    get dateFrom() {
        return this._dateFrom;
    }

    get dateTo() {
        return this._dateTo;
    }

    get discountTypeFormatted() {
        return this._discountTypeFormatted;
    }

    get discountType() {
        let name = constants.unknownDiscountName;
        switch (this._discountTypeFormatted) {
            case constants.priceDiscountName:
                name = constants.priceDiscount;
                break;
            case constants.percentDiscountName:
                name = constants.percentDiscount;
                break;
            case constants.dateDiscountName:
                name = constants.dateDiscount;
                break;
            case constants.quantityDiscountName:
                name = constants.quantityDiscount;
                break;
        }

        return name;
    }
}
