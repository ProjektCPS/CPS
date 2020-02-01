let categoryDiscount = function ($) {
    const MODAL_DISCOUNT_ID = "#category-discount-modal";
    const DISCOUNT_BUTTON_SELECTOR = ".category-discount-edit";
    const DELETE_APPLIED_DISCOUNT_SELECTOR = "a.label i.delete";
    const APPLIED_DISCOUNTS_CONTAINER_ID = "#applied-discounts";
    const TABLE_ID = "#discounts-table";
    const APPLY_BUTTON_ID = "#apply-discount";

    const ENDPOINT_TYPES = [
        "discountsAsync",
        "appliedDiscountsCategory"
    ];

    const URL_PARAMS = [
        "productCategoryId",
        "type",
    ];

    let table = $(TABLE_ID).DataTable(
        {
            "select": true,
            "paging": false,
            "ordering": false,
            "info": false,
            "columnDefs": [
                {
                    "targets": [0],
                    "visible": false,
                    "searchable": false
                }
            ]
        }
    );

    function createTable(response) {
        table.clear();

        let selectedDiscountType = $('.ui.dropdown').dropdown('get value')[1];

        let columnName = "Hodnota";
        switch (selectedDiscountType) {
            case constants.priceDiscount:
                columnName = "Hodnota zlavy";
                break;
            case constants.percentDiscount:
                columnName = "Počet percent";
                break;
            case constants.dateDiscount:
                columnName = "Počet dní";
                break;
            case constants.quantityDiscount:
                columnName = "Množstvo";
                break;
        }
        // index 2 pretoze prvy stlpec existuje ibaze je schovany
        $(table.column(2).header()).text(columnName);

        response.forEach(item => {
            let discountValue = selectedDiscountType === constants.priceDiscount ? item.cenovaZlavaEntity.hodnotaZlavy :
                selectedDiscountType === constants.percentDiscount ? item.percentualnaZlavaEntity.percentZlavy :
                    selectedDiscountType === constants.quantityDiscount ? item.kvantitovaZlavaEntity.mnozstvo :
                        selectedDiscountType === constants.dateDiscount ? item.datumovaZlavaEntity.den : "-";
            let dateTo = isEmpty(item.dateTo) ? "" : item.dateTo;
            table.row.add([
                item.zlavaEntity.idZlavy,
                item.typZlavyEntity.nazovTypu,
                discountValue,
                item.dateFrom,
                dateTo,
                getDiscountTypeName(selectedDiscountType)
            ]);
        });
        table.draw();
    }

    function fillAppliedDiscounts(response) {
        let appliedDiscounts = $(APPLIED_DISCOUNTS_CONTAINER_ID);
        appliedDiscounts.empty();
        response.forEach(item => {
                let data = createDataFromResponse(item);
                appliedDiscounts.append(createDiscountTag(data));
            }
        )
    }

    function createDataFromResponse(item) {
        let discountValue = !isEmpty(item.cenovaZlavaEntity) ? item.cenovaZlavaEntity.hodnotaZlavy :
            !isEmpty(item.percentualnaZlavaEntity) ? item.percentualnaZlavaEntity.percentZlavy :
                !isEmpty(item.kvantitovaZlavaEntity) ? item.kvantitovaZlavaEntity.mnozstvo :
                    !isEmpty(item.datumovaZlavaEntity) ? item.datumovaZlavaEntity.den : "-";
        let discountType = !isEmpty(item.cenovaZlavaEntity) ? getDiscountTypeName(constants.priceDiscount) :
            !isEmpty(item.percentualnaZlavaEntity) ? getDiscountTypeName(constants.percentDiscount) :
                !isEmpty(item.kvantitovaZlavaEntity) ? getDiscountTypeName(constants.quantityDiscount) :
                    !isEmpty(item.datumovaZlavaEntity) ? getDiscountTypeName(constants.dateDiscount) : "-";
        let dateTo = isEmpty(item.dateTo) ? "" : item.dateTo;

        return new RowData(
            item.zlavaEntity.idZlavy,
            item.typZlavyEntity.nazovTypu,
            discountValue,
            item.dateFrom,
            dateTo,
            discountType
        );
    }

    function isEmpty(value) {
        return value === null || value === undefined || value === ""
    }

    const onEdit = function () {
        let productCategoryId = $(this).data(DATA_PRODUCT_CATEGORY);
        getAppliedDiscounts(productCategoryId, (response) => {
            fillAppliedDiscounts(response);
            showModal()
        })
    };


    function getDiscountsByType(type, callback) {
        $.ajax({
            type: "GET",
            url: '../../account/' + ENDPOINT_TYPES[0] + '?' + URL_PARAMS[1] + '=' + type,
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

    function getAppliedDiscounts(productCategoryId, callback) {
        $.ajax({
            type: "GET",
            url: '../../account/' + ENDPOINT_TYPES[1] + '?' + URL_PARAMS[0] + '=' + productCategoryId,
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

    function showModal() {
        $(MODAL_DISCOUNT_ID + ".ui.modal").modal('show');
    }

    function onHideModal() {
        $('.ui.dropdown').dropdown('clear');

        table.clear().draw();

        let appliedDiscounts = $(APPLIED_DISCOUNTS_CONTAINER_ID);
        appliedDiscounts.empty();
    }

    function onApply() {
        let selectedRow = table.rows('.selected').data()[0];
        let rowData = new RowData(
            selectedRow[0],
            selectedRow[1],
            selectedRow[2],
            selectedRow[3],
            selectedRow[4],
            selectedRow[5]
        );

        if (rowData.id !== undefined) {
            applyDiscount(rowData);
        } else {
            alert(warningMessages.selectRow);
        }
    }

    function odDeleteAppliedDiscount() {
        $(this).parent().remove();
    }

    function applyDiscount(rowData) {
        let appliedDiscounts = $(APPLIED_DISCOUNTS_CONTAINER_ID + " a");

        let exists = appliedDiscounts.toArray().filter(item => $(item).data('id') === rowData.id).length > 0;
        if (exists) {
            alert("Zľava je už applikovaná.")
        } else {
            let appliedDiscountsBox = $(APPLIED_DISCOUNTS_CONTAINER_ID);
            appliedDiscountsBox.append(createDiscountTag(rowData))
        }
    }

    function createDiscountTag(rowData) {
        let tag = $("<a></a>").text(rowData.name + " - " + rowData.value + " ( " + rowData.discountType + " )");
        tag.addClass("ui label customTag");
        tag.data('id', rowData.id);

        let icon = $("<i></i>");
        icon.addClass("delete icon");

        tag.append(icon);

        return tag;
    }

    function onApproveModalDetail() {

    }

    function getDiscountTypeName(discountType) {
        let name = "Neznáma";
        switch (discountType) {
            case constants.priceDiscount:
                name = "Cenová";
                break;
            case constants.percentDiscount:
                name = "Percentuálna";
                break;
            case constants.dateDiscount:
                name = "Dátumová";
                break;
            case constants.quantityDiscount:
                name = "Kvatitová";
                break;
        }

        return name;
    }

    function onDropdownChange(value, text, $choice) {
        if (value) {
            getDiscountsByType(value, (response) => {
                createTable(response);
            });
        }
    }

    function createDropdown() {
        let dropdownItems = [
            {
                name: "Cenová",
                value: constants.priceDiscount
            },
            {
                name: "Percentuálna",
                value: constants.percentDiscount
            },
            {
                name: "Kvatitová",
                value: constants.quantityDiscount
            }
        ];

        $('.ui.dropdown')
            .dropdown({
                values: dropdownItems,
                showOnFocus: false,
                selected: constants.priceDiscount,
                onChange: onDropdownChange
            });
    }

    // listeners have to be on end of the file

    $(DISCOUNT_BUTTON_SELECTOR).click(onEdit);

    $(MODAL_DISCOUNT_ID).modal({
        onHidden: onHideModal,
        onApprove: onApproveModalDetail
    });

    $(TABLE_ID).on('click', 'tbody tr', function () {
        // let row = table.row($(this)).data();
        $(this).addClass('selected').siblings().removeClass('selected');
    });

    $(APPLY_BUTTON_ID).click(onApply);
    $(APPLIED_DISCOUNTS_CONTAINER_ID).on("click", DELETE_APPLIED_DISCOUNT_SELECTOR, odDeleteAppliedDiscount);

    createDropdown();
};

class RowData {
    constructor(id, name, value, dateFrom, dateTo, discountType) {
        this._id = id;
        this._name = name;
        this._value = value;
        this._dateFrom = dateFrom;
        this._dateTo = dateTo;
        this._discountType = discountType;
    }


    get id() {
        return this._id;
    }

    get name() {
        return this._name;
    }

    get value() {
        return this._value;
    }

    get dateFrom() {
        return this._dateFrom;
    }

    get dateTo() {
        return this._dateTo;
    }

    get discountType() {
        return this._discountType;
    }
}
