jQuery(document).ready(function ($) {
    new MainCategories($);
    new DiscountType($);
    init();
});

function init() {
    $('#profile').addClass('highlighted');
}
