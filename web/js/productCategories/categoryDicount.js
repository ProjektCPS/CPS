const DISCOUNT_BUTTON_SELECTOR = ".category-discount-edit";

const MODAL_DISCOUNT_ID = "#category-discount-modal";

jQuery(document).ready(function ($) {

    $(DISCOUNT_BUTTON_SELECTOR).click(onEditDiscount);

    $(MODAL_DISCOUNT_ID).modal({
        onHidden: onHideTenantModal,
        onApprove: onApproveModalDetail
    });
});

const onEditDiscount = function () {
    let productCategoryId = $(this).data(DATA_PRODUCT_CATEGORY);
    getCategoryDicount(productCategoryId);
};

function showModal() {
    $(MODAL_DISCOUNT_ID + ".ui.modal").modal('show');
}
