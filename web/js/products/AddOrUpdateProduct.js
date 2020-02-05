jQuery(document).ready(function ($) {
    init();

    //save
    $('#product-save').click(onAccountSave);
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
        url: '../../account/products?productId=' + id,
        data: data,
        beforeSend: function() {
            console.log("beforeSend");
        },
        success: function (response) {
            alert("updated");
            window.location.href = "./accounts";
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
        url: '../../account/products',
        data: data,
        beforeSend: function() {
            console.log("beforeSend");
        },
        success: function (response) {
            alert("inserted");
            window.location.href = "./accounts";
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
            case "nazov":
                if (itemValue === "") {
                    messages.push("Vyplňte tenanta")
                }
                break;
            case "jednotka":
                if (itemValue === "") {
                    messages.push("Vyplňte krstné meno")
                }
                break;
            case "last-name":
                if (itemValue === "") {
                    messages.push("Vyplňte priezvisko")
                }
                break;
            case "cena":
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

