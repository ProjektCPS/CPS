const TABLE_ID_DISCOUNT = "#main-discountType-table";
const MODAL_DETAIL_ID_DISCOUNT = "#discounts-type-modal";
const MODAL_DELETE_ID_DISCOUNT = "#delete-discounts-type-modal";
const EDIT_BUTTON_ID_DISCOUNT = "#edit-main-discount-type";
const DELETE_BUTTON_ID_DISCOUNT = "#delete-main-discout-type";
const NEW_BUTTON_ID_DISCOUNT = "#new-main-discount-type";

const MODAL_DETAIL_SELECTED_ITEM_NAME_ID_DISCOUNT = "discounts-type-id";

const URL_PARAM_DISCOUNT = "discount-type-id";

function isUpdate() {
    let id = getMainCategoryId();
    return id && !isNaN(parseInt(id))
}

jQuery(document).ready(function ($) {
    $('#profile').addClass('highlighted');

    $("table tr").click(function () {
        $(this).addClass('selected').siblings().removeClass('selected');
    });

    $(MODAL_DETAIL_ID_DISCOUNT).modal({
        onHidden: onHideTenantModal,
        onApprove: onApproveModalDetail
    });

    $(MODAL_DELETE_ID_DISCOUNT).modal({
        onApprove: onApproveModalDelete
    });

    $(NEW_BUTTON_ID_DISCOUNT).click(onNew);

    $(EDIT_BUTTON_ID_DISCOUNT).click(onEdit);

    $(DELETE_BUTTON_ID_DISCOUNT).click(onDelete);

});

onNew = function () {
    $(MODAL_DETAIL_ID_DISCOUNT + ".ui.modal")
        .modal('show');
};

onEdit = function () {
    let selectedRow = $(TABLE_ID_DISCOUNT + " tr.selected");

    let selectedRowId = selectedRow.data("id");
    if (selectedRowId !== undefined) {
        getMainCategory(selectedRowId);
    } else {
        alert(warningMessages.selectRow);
    }
};

onDelete = function () {
    let selectedRow = $(TABLE_ID_DISCOUNT + " tr.selected");

    let selectedRowId = selectedRow.data("id");
    if (selectedRowId !== undefined) {
        $(MODAL_DELETE_ID_DISCOUNT + ".ui.modal")
            .modal('show');
    } else {
        alert(warningMessages.selectRow);
    }
};

function getMainCategory(selectedRowId) {
    $.ajax({
        type: "GET",
        url: '../../account/productType?' + URL_PARAM_DISCOUNT + '=' + selectedRowId,
        beforeSend: function () {
            console.log("beforeSend");
        },
        success: function (response) {
            $(MODAL_DETAIL_ID_DISCOUNT + ' input[name=main-category-name]').val(response.nazov);
            $(MODAL_DETAIL_ID_DISCOUNT + ' input[name=' + MODAL_DETAIL_SELECTED_ITEM_NAME_ID_DISCOUNT + ']').val(response.idTypu);
            $(MODAL_DETAIL_ID_DISCOUNT)
                .modal('show');
        },
        error: function (err) {
            alert("Nepodarilo sa nacitat tenanta.")
        }
    })
}

function onHideTenantModal() {
    $(MODAL_DETAIL_ID_DISCOUNT + ' input[name=main-category-name]').val("");
    $(MODAL_DETAIL_ID_DISCOUNT + ' input[name=' + MODAL_DETAIL_SELECTED_ITEM_NAME_ID_DISCOUNT + ']').val("");
}

function onApproveModalDetail() {
    let validateResults = validateAll();

    if (validateResults.length > 0) {
        validateResults.forEach(function (item) {
            $(item.itemInput).popup({
                content: item.messages.toString().replace(",", "\n")
            });
            $(item.item).addClass("error")
        });

        alert(warningMessages.fillAllFieldsCorectly);
        return false;
    }

    if (isUpdate()) {
        updateMainCategory(getMainCategoryId());
    } else {
        insertMainCategory();
    }
}

function getMainCategoryId() {
    return $(MODAL_DETAIL_ID_DISCOUNT + ' input[name=' + MODAL_DETAIL_SELECTED_ITEM_NAME_ID_DISCOUNT + ']').val();
}

function updateMainCategory(id) {
    let data = getData();
    console.log(data);
    $.ajax({
        type: "POST",
        url: '../../account/productType?' + URL_PARAM_DISCOUNT + '=' + id,
        data: data,
        beforeSend: function () {
        },
        success: function (response) {
            alert("updated");
            window.location.href = "../account/profile";
        },
        error: function (err) {
            console.log(err);
            let msg = err.responseText ? err.responseText : 'nespecifikovana chyba';

            alert("Nepodarilo sa ulozit hlavnu kategoriu. Nastala chyba status: " + err.status + "."
                + "\r\nChyba info: " + msg);
        }
    })
    ;
}

function insertMainCategory() {
    let data = getData();
    $.ajax({
        type: "POST",
        url: '../../account/productType',
        data: data,
        beforeSend: function () {
        },
        success: function (response) {
            alert("inserted");
            window.location.href = "../account/profile";
        },
        error: function (err) {
            console.log(err);
            let msg = err.responseText ? err.responseText : 'nespecifikovana chyba';

            alert("Nepodarilo sa vlozit hlavnu kategoriu. Nastala chyba status: " + err.status + "."
                + "\r\nChyba info: " + msg);
        }
    })
    ;
}

function getData() {
    let data = $('.field').toArray().map(item => {
        return {
            name: $(item).find("input").attr("name"),
            value: $(item).find("input").val().trim()
        }
    });

    data.push({
        name: "mainCategoryId",
        value: $(MODAL_DETAIL_ID_DISCOUNT + ' input[name=' + MODAL_DETAIL_SELECTED_ITEM_NAME_ID_DISCOUNT + ']').val()
    });

    return data;
}

function validateAll() {
    let data = $('.field.required');

    let result = [];
    data.toArray().forEach(function (item) {
        let itemInput = $(item).find("input");
        let itemValue = itemInput.val().trim();
        let messages = [];

        // remove error and popup before will start validation
        itemInput.popup('destroy');
        $(item).removeClass("error");

        switch (itemInput.attr("name")) {
            case "main-category-name":
                if (itemValue === "") {
                    messages.push("Vyplňte názov kategórie")
                }
                break;
            default:
                break
        }

        if (messages.length > 0) {
            result.push({
                item: item,
                itemInput: itemInput,
                messages: messages
            })
        }
    });
    return result;
}

function onApproveModalDelete() {
    alert("TODO: implementuj delete akciu");
    return false;
}

