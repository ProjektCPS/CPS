jQuery(document).ready(function ($) {
    init();

    //save
    $('#account-save').click(onAccountSave);
});

onAccountSave = function () {
    let validateResults = validateAll();

    if (validateResults.length > 0) {
        validateResults.forEach( function (item) {
           $(item.itemInput).popup({
               content: item.messages.toString().replace(",", "\n")
           });
            $(item.item).addClass("error")
        });

        alert(warningMessages.fillAllFieldsCorectly);
        return;
    }

    alert("saved")
};

function validateAll() {
    let data = $('.field');

    let result = [];
    data.toArray().forEach(function (item) {
        let itemInput = $(item).find("input");
        let itemValue = itemInput.val().trim();
        let messages = [];

        // remove error and popup before will start validation
        itemInput.popup('destroy');
        $(item).removeClass("error");

        switch (itemInput.attr("name")) {
            case "tenant-id":
                if (itemValue === "") {
                    messages.push("Vyplňte tenant-id / Užívateľa")
                }
                break;
            case "role":
                if (itemValue === "") {
                    messages.push("Vyplňte rolu")
                }
                break;
            case "first-name":
                if (itemValue === "") {
                    messages.push("Vyplňte krstné meno")
                }
                break;
            case "last-name":
                if (itemValue === "") {
                    messages.push("Vyplňte priezvisko")
                }
                break;
            case "RC":
                if (itemValue === "") {
                    messages.push("Vyplňte rodné číslo")
                }
                if(isNaN(itemValue)){
                    messages.push("Rodné číslo musí byť číslo")
                }
                if(itemValue.includes("\\")){
                    messages.push("Rodné číslo nesmie obsahovať lomítko (\\)")
                }
                break;
            case "email":
                if (itemValue === "") {
                    messages.push("Vyplňte email")
                }
                break;
            case "street":
                if (itemValue === "") {
                    messages.push("Vyplňte ulicu")
                }
                break;
            case "streetNumber":
                if (itemValue === "") {
                    messages.push("Vyplňte číslo domu")
                }
                break;
            case "city":
                if (itemValue === "") {
                    messages.push("Vyplňte mesto")
                }
                break;
            case "postcode":
                if (itemValue === "") {
                    messages.push("Vyplňte PSČ")
                }
                break;
            case "country":
                if (itemValue === "") {
                    messages.push("Vyplňte krajinu")
                }
                break;
            case "state":
                if (itemValue === "") {
                    messages.push("Vyplňte kraj")
                }
                break;
            case "region":
                if (itemValue === "") {
                    messages.push("Vyplňte okres")
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

function init() {
    $('#accounts').addClass('highlighted');
    setActiveState();
}

function setActiveState() {
    let checkboxActive = $('#checkbox-active');

    let method = checkboxActive.data('active') === 1 ? 'set checked' : 'set unchecked';

    checkboxActive.checkbox(method);
}
