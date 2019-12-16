jQuery(document).ready(function ($) {
    $('#profile').addClass('highlighted');

    $("table tr").click(function () {
        $(this).addClass('selected').siblings().removeClass('selected');
    });

    $("#main-category-modal.ui.modal").modal({
        onHidden: onHideTenantModal
    });

    $("#new-main-category").click(function () {
        let selectedRow = $("#main-categories-table tr.selected");

        let selectedRowId = selectedRow.data("id");
        if (selectedRowId !== undefined) {
            $("#main-category-modal.ui.modal")
                .modal('show');
        } else {
            alert(warningMessages.selectRow);
        }

    });

    $("#edit-main-category").click(function () {
        let selectedRow = $("#main-categories-table tr.selected");

        let selectedRowId = selectedRow.data("id");
        if (selectedRowId !== undefined) {
            getMainCategory(selectedRowId);
        } else {
            alert(warningMessages.selectRow);
        }

    });

    $("#delete-main-category").click(function () {
        let selectedRow = $("#main-categories-table tr.selected");

        let selectedRowId = selectedRow.data("id");
        if (selectedRowId !== undefined) {
            $("#delete-main-category-modal.ui.modal")
                .modal('show');
        } else {
            alert(warningMessages.selectRow);
        }
    });

});

function getMainCategory(selectedRowId) {


}

function onHideTenantModal() {
    $('#main-category-modal.ui.modal input[name=main-category-name]').val("");
}

