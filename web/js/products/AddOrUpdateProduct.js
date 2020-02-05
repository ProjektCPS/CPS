jQuery(document).ready(function ($) {

    //save
    $('#product-save').click(onAccountSave);

    //discard
    $('#discard').click(onDiscard);
});

function isUpdate() {
    let param = getParamFromUrl("productId");
    return param && !isNaN(parseInt(param))
}

function updateAccount(id) {
    let data = getData();
    console.log(data);
    $.ajax({
        type: "POST",
        url: '../../account/product?productId=' + id,
        data: data,
        beforeSend: function() {
            console.log("beforeSend");
        },
        success: function (response) {
            alert("updated");
            goBackAndReloadPage();
        },
        error: function (err) {
            console.log(err);
            let msg = err.responseText ? err.responseText : 'nespecifikovana chyba';

            alert("Nepodarilo sa editovat produkt. Nastala chyba status: " + err.status + "."
                + "\r\nChyba info: " + msg);
        }
    })
    ;
}

function insertAccount() {
    let data = getData();
    $.ajax({
        type: "POST",
        url: '../../account/product',
        data: data,
        beforeSend: function() {
            console.log("beforeSend");
        },
        success: function (response) {
            alert("inserted");
            goBackAndReloadPage();
        },
        error: function (err) {
            console.log(err);
            let msg = err.responseText ? err.responseText : 'nespecifikovana chyba';

            alert("Nepodarilo sa vytvorit produkt. Nastala chyba status: " + err.status + "."
                + "\r\nChyba info: " + msg);
        }
    })
    ;
}

onDiscard = function () {
    goBackAndReloadPage();
};

onAccountSave = function () {
    let validateResults = validateAll();

    if (validateResults.length > 0) {
        validateResults.forEach(function (item) {
            $(item.itemInput).popup({
                content: item.messages.toString().replace(",", "\n")
            });
            $(item.item).addClass("error")
        });

        alert(warningMessages.fillAllFieldsCorectly);
        return;
    }

    if (isUpdate()) {
        updateAccount(getParamFromUrl("productId"));
    } else {
        insertAccount();
    }
};

function getData() {
    let data = $('.field').toArray().map(item => {
        return {
            name: $(item).find("input").attr("name"),
            value: $(item).find("input").val().trim()
        }
    });

    data.push({
       name: "categoryId",
        value: getParamFromUrl("productCategoryId")
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
            case "name":
                if (itemValue === "") {
                    messages.push("Vyplňte nazov produktu")
                }
                break;
            case "unit":
                if (itemValue === "") {
                    messages.push("Vyplňte jednotku")
                }
                break;
            case "price":
                if (itemValue === "") {
                    messages.push("Vyplňte cenu")
                }
                if (isNaN(itemValue)) {
                    messages.push("Cena musí byť číslo")
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

