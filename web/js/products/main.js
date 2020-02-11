jQuery(document).ready(function ($) {
    new productDiscounts($);
    init();
});

const SEARCHER_SELECTOR = ".searcher";
const TABLE_ID = "#products-table";
const EDIT_PRODUCT_BUTTON_ID = "#edit-product";
const PARAMS_URL = [
    "productCategoryId",
    "productId"
];

function init() {

    let table = $(TABLE_ID).DataTable(
        {
            "select": true,
            "paging": false,
            "ordering": true,
            "info": false
        }
    );

    $("#products").addClass('highlighted');

    $(EDIT_PRODUCT_BUTTON_ID).click(onEdit);

    $(TABLE_ID).on('click', 'tbody tr', function () {
        // let row = table.row($(this)).data();
        $(this).addClass('selected').siblings().removeClass('selected');
    });

    $(SEARCHER_SELECTOR).on("click", 'button', onSearch);
    $(SEARCHER_SELECTOR).on("keypress", 'input', function (e) {
        if (e.which === 13) {
            onSearch();
        }
    });
}

function onSearch() {
    let searchTerm = $(SEARCHER_SELECTOR + ' input').val();

    let tableFilter = $(TABLE_ID + "_filter input");
    tableFilter.val(searchTerm);
    tableFilter.trigger('search');
}

const onEdit = function () {
    let productId = getSelectedProductRowId();

    if (productId !== undefined) {
        window.location.href = "./product?" + PARAMS_URL[0] + "=" + getParamFromUrl(PARAMS_URL[0]) + "&" + PARAMS_URL[1] + "=" + productId;
    } else {
        alert(warningMessages.selectRow);
    }
};

function getSelectedProductRowId() {
    let selectedRow = $(TABLE_ID + " tr.selected");

    return selectedRow.data("id");
}

