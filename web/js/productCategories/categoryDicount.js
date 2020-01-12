let categoryDicount = function ($) {
    const MODAL_DISCOUNT_ID = "#category-discount-modal";
    const DISCOUNT_BUTTON_SELECTOR = ".category-discount-edit";

    const onEdit = function () {
        let productCategoryId = $(this).data(DATA_PRODUCT_CATEGORY);
        showModal()
        //getCategoryDicount(productCategoryId);
    };

    function showModal() {
        $(MODAL_DISCOUNT_ID + ".ui.modal").modal('show');
    }

    function onHideModal() {
        // $(MODAL_DETAIL_ID + ' input[name=category-name]').val("");
        // $(MODAL_DETAIL_ID + ' input[name=' + MODAL_DETAIL_SELECTED_ITEM_NAME_ID + ']').val("");
    }

    function onApproveModalDetail() {

    }

    $(DISCOUNT_BUTTON_SELECTOR).click(onEdit);

    $(MODAL_DISCOUNT_ID).modal({
        onHidden: onHideModal,
        onApprove: onApproveModalDetail
    });
};
