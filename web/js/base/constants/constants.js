class errorMessages {}

class warningMessages {}

class infoMessages {}

class constants {}

//errors

//warning
warningMessages.selectRow = "Vyberte riadok prosím";
warningMessages.fillAllRequiredFields = "Vyplne všetky povinné údaje označené hviezdčkou (*)";
warningMessages.fillAllFieldsCorectly = "Výplňte správne všetky údaje prosím";

//info

//constants
constants.quantityDiscount = "quantity";
constants.percentDiscount = "percent";
constants.priceDiscount = "price";
constants.dateDiscount = "date";
constants.quantityDiscountName = "Kvatitová";
constants.percentDiscountName = "Percentuálna";
constants.priceDiscountName = "Cenová";
constants.dateDiscountName = "Dátumová";
constants.unknownDiscountName = "Neznáma";

Object.freeze(errorMessages);
Object.freeze(warningMessages);
Object.freeze(infoMessages);
Object.freeze(constants);






