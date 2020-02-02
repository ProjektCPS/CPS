jQuery(document).ready(function ($) {
    new productDiscounts($);
    init();
});

const TABLE_ID = "#products-table";
function init() {
    $("#products").addClass('highlighted');

    $(TABLE_ID).on('click', 'tbody tr', function () {
        // let row = table.row($(this)).data();
        $(this).addClass('selected').siblings().removeClass('selected');
    });
}
