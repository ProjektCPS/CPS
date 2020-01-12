const DELETE_BUTTON_SELECTOR = ".delete-category";
const EDIT_BUTTON_SELECTOR = ".edit-category";
const NEW_BUTTON_ID = "#new-category";
const ALL_CATEGORIES_ID = "#allCategories";

const MODAL_DELETE_ID = "#delete-category-modal";
const MODAL_DETAIL_ID = "#category-modal";

const MODAL_DETAIL_SELECTED_ITEM_NAME_ID = "category-id";
const DATA_PRODUCT_CATEGORY = 'product-category-id';

const URL_PARAMS = [
    "productCategoryId"
];

function isUpdate() {
    let id = getCategoryId();
    return id && !isNaN(parseInt(id))
}

jQuery(document).ready(function ($) {
    init($);

    $(NEW_BUTTON_ID).click(onNew);

    $(EDIT_BUTTON_SELECTOR).click(onEdit);

    $(DELETE_BUTTON_SELECTOR).click(onDelete);

    $(MODAL_DETAIL_ID).modal({
        onHidden: onHideModal,
        onApprove: onApproveModalDetail
    });

    $(MODAL_DELETE_ID).modal({
        onApprove: onApproveModalDelete
    });
});

function init($) {
    new categoryDicount($);
    $('#products').addClass('highlighted');
}

const onNew = function () {
    $(MODAL_DETAIL_ID + ".ui.modal").modal('show');
};

const onEdit = function () {
    let productCategoryId = $(this).data(DATA_PRODUCT_CATEGORY);
    getCategory(productCategoryId);
};


const onDelete = function () {
    $(MODAL_DELETE_ID + ".ui.modal").modal('show');
};

function onApproveModalDelete() {
    alert("TODO: implementuj delete akciu");
    return false;
}

function onHideModal() {
    alert("janko");
    $(MODAL_DETAIL_ID + ' input[name=category-name]').val("");
    $(MODAL_DETAIL_ID + ' input[name=' + MODAL_DETAIL_SELECTED_ITEM_NAME_ID + ']').val("");
}

function getCategory(id) {
    $.ajax({
        type: "GET",
        url: '../../account/productCategory?' + URL_PARAMS[0] + '=' + id,
        beforeSend: function () {
            console.log("beforeSend");
        },
        success: function (response) {
            $(MODAL_DETAIL_ID + ' input[name=category-name]').val(response.nazov);
            $(MODAL_DETAIL_ID + ' input[name=' + MODAL_DETAIL_SELECTED_ITEM_NAME_ID + ']').val(response.idKategorie);
            $(MODAL_DETAIL_ID)
                .modal('show');
        },
        error: function (err) {
            alert("Nepodarilo sa nacitat categoriu.")
        }
    })
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
        updateCategory(getCategoryId());
    } else {
        insertCategory();
    }
}

function updateCategory(id) {
    let data = getData();
    $.ajax({
        type: "POST",
        url: '../../account/productCategory?' + URL_PARAMS[0] + '=' + id,
        data: data,
        beforeSend: function () {
        },
        success: function (response) {
            reloadPage();
        },
        error: function (err) {
            console.log(err);
            let msg = err.responseText ? err.responseText : 'nespecifikovana chyba';

            alert("Nepodarilo sa ulozit kategoriu. Nastala chyba status: " + err.status + "."
                + "\r\nChyba info: " + msg);
        }
    });
}

function insertCategory() {
    let data = getData();
    $.ajax({
        type: "POST",
        url: '../../account/productCategory',
        data: data,
        beforeSend: function () {
        },
        success: function (response) {
            reloadPage()
        },
        error: function (err) {
            console.log(err);
            let msg = err.responseText ? err.responseText : 'nespecifikovana chyba';

            alert("Nepodarilo sa vlozit kategoriu. Nastala chyba status: " + err.status + "."
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
        value: $(ALL_CATEGORIES_ID).data("main-category-id")
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
            case "category-name":
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

function getCategoryId() {
    return $(MODAL_DETAIL_ID + ' input[name=' + MODAL_DETAIL_SELECTED_ITEM_NAME_ID + ']').val();
}
