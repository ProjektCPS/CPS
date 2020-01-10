const TABLE_ID = "#main-categories-table";
const MODAL_DETAIL_ID = "#main-category-modal";
const MODAL_DELETE_ID = "#delete-main-category-modal";
const EDIT_BUTTON_ID = "#edit-main-category";
const DELETE_BUTTON_ID = "#delete-main-category";
const NEW_BUTTON_ID = "#new-main-category";

const MODAL_DETAIL_SELECTED_ITEM_NAME_ID = "main-category-id";

const URL_PARAM = "mainCategoryId";

function isUpdate() {
    let id = getMainCategoryId();
    return id && !isNaN(parseInt(id))
}

jQuery(document).ready(function ($) {
    $('#profile').addClass('highlighted');

    $("table tr").click(function () {
        $(this).addClass('selected').siblings().removeClass('selected');
    });

    $(MODAL_DETAIL_ID).modal({
        onHidden: onHideTenantModal,
        onApprove: onApproveModalDetail
    });

    $(MODAL_DELETE_ID).modal({
        onApprove: onApproveModalDelete
    });

    $(NEW_BUTTON_ID).click(onNew);

    $(EDIT_BUTTON_ID).click(onEdit);

    $(DELETE_BUTTON_ID).click(onDelete);

});

onNew = function () {
    $(MODAL_DETAIL_ID + ".ui.modal")
        .modal('show');
};

onEdit = function () {
    let selectedRow = $(TABLE_ID + " tr.selected");

    let selectedRowId = selectedRow.data("id");
    if (selectedRowId !== undefined) {
        getMainCategory(selectedRowId);
    } else {
        alert(warningMessages.selectRow);
    }
};

onDelete = function () {
    let selectedRow = $(TABLE_ID + " tr.selected");

    let selectedRowId = selectedRow.data("id");
    if (selectedRowId !== undefined) {
        $(MODAL_DELETE_ID + ".ui.modal")
            .modal('show');
    } else {
        alert(warningMessages.selectRow);
    }
};

function getMainCategory(selectedRowId) {
    $.ajax({
        type: "GET",
        url: '../../account/productType?' + URL_PARAM + '=' + selectedRowId,
        beforeSend: function () {
            console.log("beforeSend");
        },
        success: function (response) {
            $(MODAL_DETAIL_ID + ' input[name=main-category-name]').val(response.nazov);
            $(MODAL_DETAIL_ID + ' input[name=' + MODAL_DETAIL_SELECTED_ITEM_NAME_ID + ']').val(response.idTypu);
            $(MODAL_DETAIL_ID)
                .modal('show');
        },
        error: function (err) {
            alert("Nepodarilo sa nacitat hlavnu kategoriu.")
        }
    })
}

function onHideTenantModal() {
    $(MODAL_DETAIL_ID + ' input[name=main-category-name]').val("");
    $(MODAL_DETAIL_ID + ' input[name=' + MODAL_DETAIL_SELECTED_ITEM_NAME_ID + ']').val("");
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
    return $(MODAL_DETAIL_ID + ' input[name=' + MODAL_DETAIL_SELECTED_ITEM_NAME_ID + ']').val();
}

function updateMainCategory(id) {
    let data = getData();
    console.log(data);
    $.ajax({
        type: "POST",
        url: '../../account/productType?' + URL_PARAM + '=' + id,
        data: data,
        beforeSend: function () {
        },
        success: function (response) {
            reloadPage();
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
            reloadPage()
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
        value: $(MODAL_DETAIL_ID + ' input[name=' + MODAL_DETAIL_SELECTED_ITEM_NAME_ID + ']').val()
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

