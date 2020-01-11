
jQuery(document).ready(function($){
    init()
});

function init() {
    $('#discounts').addClass('highlighted');

    const path = "../../account/discounts?type=";

    $("#priceDiscount").attr("href", path + constants.priceDiscount);
    $("#percentDiscount").attr("href", path + constants.percentDiscount);
    $("#quantityDiscount").attr("href", path + constants.quantityDiscount);
}
