package dataAccessObjects;

public enum DiscountTypes {
    QUANTITY_DISCOUNT("quantity"),
    PERCENT_DISCOUNT("percent"),
    PRICE_DISCOUNT("price"),
    DATE_DISCOUNT("date");

    private String discountType;

    DiscountTypes(String discountType) {
        this.discountType = discountType;
    }

    @Override
    public String toString() {
        return this.discountType;
    }

    public static boolean contains(String discountTypes) {
        for (DiscountTypes type : DiscountTypes.values()) {
            if (type.toString().equals(discountTypes)) {
                return true;
            }
        }
        return false;
    }

    public static DiscountTypes getIfExists(String discountTypes) {
        for (DiscountTypes type : DiscountTypes.values()) {
            if (type.toString().equals(discountTypes)) {
                return type;
            }
        }
        return null;
    }
}
