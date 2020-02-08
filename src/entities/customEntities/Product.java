package entities.customEntities;

import entities.PredmetPredajaEntity;

import java.util.List;

public class Product {
    private PredmetPredajaEntity product;
    private boolean hasAppliedDiscount;
    private List<String> appliedDiscountTypes;
    private double afterDiscountsPrice;

    public Product(PredmetPredajaEntity product, boolean hasAppliedDiscount, List<String> appliedDiscountTypes, double afterDiscountsPrice) {
        this.product = product;
        this.hasAppliedDiscount = hasAppliedDiscount;
        this.appliedDiscountTypes = appliedDiscountTypes;
        this.afterDiscountsPrice = afterDiscountsPrice;
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
}
