package entities.customEntities;

import entities.PredmetPredajaEntity;

import java.util.List;

public class Product {
    private PredmetPredajaEntity product;
    private boolean hasAppliedDiscount;
    private List<String> appliedDiscountTypes;

    public Product(PredmetPredajaEntity product, boolean hasAppliedDiscount, List<String> appliedDiscountTypes) {
        this.product = product;
        this.hasAppliedDiscount = hasAppliedDiscount;
        this.appliedDiscountTypes = appliedDiscountTypes;
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
}
