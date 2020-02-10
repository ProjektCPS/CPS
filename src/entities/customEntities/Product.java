package entities.customEntities;

import entities.PredmetPredajaEntity;

import java.util.List;

public class Product {
    private PredmetPredajaEntity product;
    private boolean hasAppliedDiscount;
    private List<String> appliedDiscountTypes;
    private double afterDiscountsPrice;
    private double afterDiscountsPriceNonNegative;
    private final double defaultResult = 0.01;

    public Product(PredmetPredajaEntity product, boolean hasAppliedDiscount, List<String> appliedDiscountTypes, double afterDiscountsPrice) {
        this.product = product;
        this.hasAppliedDiscount = hasAppliedDiscount;
        this.appliedDiscountTypes = appliedDiscountTypes;
        this.afterDiscountsPrice = afterDiscountsPrice;
        this.afterDiscountsPriceNonNegative = afterDiscountsPrice > 0 ? afterDiscountsPrice : defaultResult;
    }

    public PredmetPredajaEntity getProduct() {
        return product;
    }

    public boolean isHasAppliedDiscount() {
        return hasAppliedDiscount;
    }

    public List<String> getAppliedDiscountTypes() {
        return appliedDiscountTypes;
    }

    public double getAfterDiscountsPrice() {
        return afterDiscountsPrice;
    }

    public double getAfterDiscountsPriceNonNegative() {
        return afterDiscountsPriceNonNegative;
    }
}
