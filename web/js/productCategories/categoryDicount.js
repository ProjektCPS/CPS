let categoryDiscount = function ($) {
    const MODAL_DISCOUNT_ID = "#category-discount-modal";
    const DISCOUNT_BUTTON_SELECTOR = ".category-discount-edit";
    const TABLE_ID = "#discounts-table";
    const APPLY_BUTTON_ID = "#apply-discount";

    const ENDPOINT_TYPES = [
        "discountsAsync",
        "categoryDisc"
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
            "info": false
        }
    );

    function createTable(response) {
        table.clear();

        let selectedDiscountType = $('.ui.dropdown').dropdown('get value')[0];

        let columnName = "Hodnota";
        switch (selectedDiscountType) {
            case constants.priceDiscount:
                columnName = "Hodnota zlavy";
                break;
            case constants.percentDiscount:
                columnName = "Počet percent";
                break;
            case constants.dateDiscount:
                columnName = "Množstvo";
                break;
            case constants.quantityDiscount:
                columnName = "Počet dní";
                break;
        }
        $(table.column(1).header()).text(columnName);

        response.forEach(item => {
            let discountValue = selectedDiscountType === constants.priceDiscount ? item.cenovaZlavaEntity.hodnotaZlavy :
                selectedDiscountType === constants.percentDiscount ? item.percentualnaZlavaEntity.percentZlavy :
                    selectedDiscountType === constants.dateDiscount ? item.kvantitovaZlavaEntity.mnozstvo :
                        selectedDiscountType === constants.quantityDiscount ? item.datumovaZlavaEntity.den : "-";
            let dateTo = item.dateTo === null || item.dateTo === undefined || item.dateTo === "" ? "" : item.dateTo;
            table.row.add([
                item.typZlavyEntity.nazovTypu,
                discountValue,
                item.dateFrom,
                dateTo
            ]).node().id = item.zlavaEntity.idZlavy;
            table.draw(false);
        });
    }

    const onEdit = function () {
        let productCategoryId = $(this).data(DATA_PRODUCT_CATEGORY);

        getDiscountsByType(constants.priceDiscount, (response) => {
            console.log(response);
            createTable(response);
            showModal()
        });
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

    function showModal() {
        $(MODAL_DISCOUNT_ID + ".ui.modal").modal('show');
    }

    function onHideModal() {
        // $(MODAL_DETAIL_ID + ' input[name=category-name]').val("");
        // $(MODAL_DETAIL_ID + ' input[name=' + MODAL_DETAIL_SELECTED_ITEM_NAME_ID + ']').val("");
    }

    function onApply() {
        let selectedRow = table.rows('.selected');
        let id = $(selectedRow).attr('id');
        console.log(id);
    }

    function onApproveModalDetail() {

    }

    function createDropdown() {
        let dropdownItems = [
            {
                name: "Cenová",
                value: constants.priceDiscount
            },
            {
                name: "Percentuálna",
                value: constants.quantityDiscount
            },
            {
                name: "Kvatitová",
                value: constants.percentDiscount
            }
        ];

        $('.ui.dropdown')
            .dropdown({
                values: dropdownItems,
                showOnFocus: false,
                selected: constants.priceDiscount
            });
        $('.ui.dropdown').dropdown("set selected", constants.priceDiscount)
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

    createDropdown();
};
