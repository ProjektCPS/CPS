package entities.customEntities;

import entities.KategorieEntity;

public class CategoryProducts {
    private KategorieEntity category;
    private boolean hasAppliedDiscount;

    public CategoryProducts(KategorieEntity category, boolean hasAppliedDiscount) {
        this.category = category;
        this.hasAppliedDiscount = hasAppliedDiscount;
    }

    public KategorieEntity getCategory() {
        return category;
    }

    public boolean isHasAppliedDiscount() {
        return hasAppliedDiscount;
    }
}
