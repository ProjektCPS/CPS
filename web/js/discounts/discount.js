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

    const URL_PARAMS = [
        "discountId",
        "type",
    ];

    const onNew = function () {

        getDiscountTypes((response) => {
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

        $('.ui.dropdown')
            .dropdown({
                values: dropdownItems,
                showOnFocus: false
            });
    }

    const onEdit = function () {
        let selectedRow = $(TABLE_ID + " tr.selected");

        let selectedRowId = selectedRow.data("id");
        if (selectedRowId !== undefined) {
            getDiscount(selectedRowId);
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

    const onHideModal = function () {
        // clear all
        let inputs = $(MODAL_DETAIL_ID + " input");
        inputs.toArray().forEach(function (item) {
            $(item).val("");
        });

        //clear calendars
        $('#dateRangeStart').calendar("clear");
        $('#dateRangeEnd').calendar("clear");

        removeInputErrors(MODAL_DETAIL_ID);
    };

    function removeInputErrors(blockSelector) {
        // remove errors and popup
        let data = $(blockSelector + ' .field.required');
        data.toArray().forEach(function (item) {
            let itemInput = $(item).find("input");
            itemInput.popup('destroy');
            $(item).removeClass("error");
        });
    }

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
            update(getId());
        } else {
            insert();
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
        let id = getId();
        return id && !isNaN(parseInt(id))
    }

    function getDiscount(selectedRowId) {
        $.ajax({
            type: "GET",
            url: '../../account/' + ENDPOINT_TYPES[0] + '?' + URL_PARAMS[0] + '=' + selectedRowId,
            beforeSend: function () {
                console.log("beforeSend");
            },
            success: function (getDiscountResponse) {
                getDiscountTypes((response) => {
                    fillDropdownItems(response);

                    let discountVal;
                    switch (getParamFromUrl(URL_PARAMS[1])) {
                        case constants.priceDiscount:
                            discountVal = getDiscountResponse.cenovaZlavaEntity.hodnotaZlavy;
                            break;
                        case constants.percentDiscount:
                            discountVal = getDiscountResponse.percentualnaZlavaEntity.percentZlavy;
                            break;
                        case constants.quantityDiscount:
                            discountVal = getDiscountResponse.kvantitovaZlavaEntity.mnozstvo;
                            break;
                        case constants.dateDiscount:
                            discountVal = getDiscountResponse.datumovaZlavaEntity.den;
                            break;
                    }

                    $(MODAL_DETAIL_ID + ' input[name=discount-value]').val(discountVal);
                    $(MODAL_DETAIL_ID + ' input[name=' + MODAL_DETAIL_SELECTED_ITEM_NAME_ID + ']').val(getDiscountResponse.zlavaEntity.idZlavy);
                    $('.ui.dropdown')
                        .dropdown("set selected", getDiscountResponse.zlavaEntity.idTypu);

                    $(MODAL_DETAIL_ID)
                        .modal('show');
                    // set dates
                    $('#dateRangeStart').calendar("set date", new Date(getDiscountResponse.dateFrom));
                    $('#dateRangeEnd').calendar("set date", new Date(getDiscountResponse.dateTo));

                });
            },
            error: function (err) {
                alert("Nepodarilo sa nacitat hlavnu kategoriu.")
            }
        })
    }

    function getId() {
        return $(MODAL_DETAIL_ID + ' input[name=' + MODAL_DETAIL_SELECTED_ITEM_NAME_ID + ']').val();
    }

    function update(id) {
        let data = getData();
        $.ajax({
            type: "POST",
            url: '../../account/' + ENDPOINT_TYPES[0] + '?' + URL_PARAMS[0] + '=' + id,
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

    function insert() {
        let data = getData();
        $.ajax({
            type: "POST",
            url: '../../account/' + ENDPOINT_TYPES[0],
            data: data,
            beforeSend: function () {
            },
            success: function (response) {
                reloadPage()
            },
            error: function (err) {
                console.log(err);
                let msg = err.responseText ? err.responseText : 'nespecifikovana chyba';

                alert("Nepodarilo sa vlozit zľavu. Nastala chyba status: " + err.status + "."
                    + "\r\nChyba info: " + msg);
            }
        });
    }

    function getData() {
        let data = $('.field').toArray().map(item => {
            return {
                name: $(item).find("input").attr("name"),
                value: $(item).find("input").val().trim()
            }
        });

        data.push({
            name: "discountId",
            value: $(MODAL_DETAIL_ID + ' input[name=' + MODAL_DETAIL_SELECTED_ITEM_NAME_ID + ']').val()
        });

        data.push({
            name: "discountType",
            value: getParamFromUrl(URL_PARAMS[1])
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
                case "mainDiscountType":
                    if (itemValue === "") {
                        messages.push("Vyplňte typ zľavy")
                    }
                    break;
                case "discount-value":
                    if (itemValue === "") {
                        let message = "";
                        switch (getParamFromUrl(URL_PARAMS[1])) {
                            case constants.priceDiscount:
                                message = "Vyplňte hodnotu zľavy";
                                break;
                            case constants.percentDiscount:
                                message = "Vyplňte počet percent zľavy";
                                break;
                            case constants.quantityDiscount:
                                message = "Vyplňte množstvo zľavy";
                                break;
                            case constants.dateDiscount:
                                message = "Vyplňte počet dní zľavy";
                                break;
                            default:
                                message = "Vyplňte hodnotu zľavy";
                                break;
                        }
                        messages.push(message);
                    }
                    if (isNaN(itemValue)) {
                        messages.push("Zadajte číslo");
                    }
                    break;
                case "discount-from":
                    if (itemValue === "") {
                        messages.push("Vyplňte dátum od")
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

    const onShowModalDetail = () => {
        $('#dateRangeStart').calendar({
            popupOptions: {
                observeChanges: false
            },
            type: 'datetime',
            endCalendar: $('#dateRangeEnd')
        });

        $('#dateRangeEnd').calendar({
            popupOptions: {
                observeChanges: false
            },
            type: 'datetime',
            startCalendar: $('#dateRangeStart')
        });
    };

    // listeners have to be on end of the file
    $(TABLE_ID + " tr").click(function () {
        $(this).addClass('selected').siblings().removeClass('selected');
    });

    $(MODAL_DETAIL_ID).modal({
        onHidden: onHideModal,
        onShow: onShowModalDetail,
        onApprove: onApproveModalDetail
    });

    $(MODAL_DELETE_ID).modal({
        onApprove: onApproveModalDelete
    });

    $(NEW_BUTTON_ID).click(onNew);

    $(EDIT_BUTTON_ID).click(onEdit);

    $(DELETE_BUTTON_ID).click(onDelete);
};
