let Discount = function ($) {
    const TABLE_ID = "#discounts-table";
    const MODAL_DETAIL_ID = "#discount-modal";
    const MODAL_DELETE_ID = "#delete-discount-modal";
    const EDIT_BUTTON_ID = "#edit-discount";
    const DELETE_BUTTON_ID = "#delete-discount";
    const NEW_BUTTON_ID = "#new-discount";

    const ENDPOINT_TYPES = [
        "discount",
        "discountTypes"
    ];

    const MODAL_DETAIL_SELECTED_ITEM_NAME_ID = "discount-id";

    const URL_PARAM = "discountId";

    const onNew = function () {

        getDiscountTypes((response) => {
            console.log(response);

            fillDropdownItems(response);

            $(MODAL_DETAIL_ID)
                .modal('show');
        });
    };

    function fillDropdownItems(items) {
        let dropdownItems = items.map((item) => {
            return {
                name: item.nazovTypu,
                value: item.idTypu
            }
        });

        console.log(dropdownItems);
        $('.ui.dropdown')
            .dropdown({
                values: dropdownItems,
                showOnFocus:false
            });
    }

    const onEdit = function () {
        let selectedRow = $(TABLE_ID + " tr.selected");

        let selectedRowId = selectedRow.data("id");
        if (selectedRowId !== undefined) {
            getMainCategory(selectedRowId);
        } else {
            alert(warningMessages.selectRow);
        }
    };

    const onDelete = function () {
        let selectedRow = $(TABLE_ID + " tr.selected");

        let selectedRowId = selectedRow.data("id");
        if (selectedRowId !== undefined) {
            $(MODAL_DELETE_ID + ".ui.modal")
                .modal('show');
        } else {
            alert(warningMessages.selectRow);
        }
    };

    const onHideTenantModal = function () {
        $(MODAL_DETAIL_ID + ' input[name=main-category-name]').val("");
        $(MODAL_DETAIL_ID + ' input[name=' + MODAL_DETAIL_SELECTED_ITEM_NAME_ID + ']').val("");
    };

    const onApproveModalDetail = function () {
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
    };

    function getDiscountTypes(callback) {
        $.ajax({
            type: "GET",
            url: '../../account/' + ENDPOINT_TYPES[1],
            beforeSend: function () {
                console.log("beforeSend");
            },
            success: function (response) {
                callback(response);
            },
            error: function (err) {
                alert("Nepodarilo sa nacitat hlavne typy zlav.")
            }
        })
    }

    const onApproveModalDelete = function () {
        alert("TODO: implementuj delete akciu");
        return false;
    };


    function isUpdate() {
        let id = getMainCategoryId();
        return id && !isNaN(parseInt(id))
    }

    function getMainCategory(selectedRowId) {
        $.ajax({
            type: "GET",
            url: '../../account/' + ENDPOINT_TYPE + '?' + URL_PARAM_DISCOUNT + '=' + selectedRowId,
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

    // listeners have to be on end of the file
    $(TABLE_ID + " tr").click(function () {
        $(this).addClass('selected').siblings().removeClass('selected');
    });

    $('#dateRangeStart').calendar({
        popupOptions: {
            observeChanges: false
        },
        type: 'datetime',
        endCalendar: $('#dateRangeStart')
    });

    $('#dateRangeEnd').calendar({
        popupOptions: {
            observeChanges: false
        },
        type: 'datetime',
        startCalendar: $('#dateRangeEnd')
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
};
