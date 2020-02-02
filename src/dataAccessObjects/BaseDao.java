package dataAccessObjects;

import entities.*;
import entities.customEntities.CategoryProducts;
import entities.customEntities.Discount;
import entities.customEntities.Product;

import java.util.List;
import java.util.Map;

public interface BaseDao {

    TypPredmetuEntity getMainCategory(int mainCategoryId);

    List<TypPredmetuEntity> getProductsTypes();

    List<CategoryProducts> getProductCategories(String categoryName);

    KategorieEntity getProductCategory(int categoryId);

    Map<String, String> insertProductCategory(Map<String, String> data);

    Map<String, String> updateProductCategory(int id, Map<String, String> data);

    List<Product> getProducts(String categoryName);

    List<RegistrovanyUzivatelEntity> getExternalSystemAccounts();

    Object[] getExternalSystemAccount(int adminId, BusinessStates state);

    Map<String, String> insertMainCategory(Map<String, String> data);

    Map<String, String> updateMainCategory(String mainCategoryId, Map<String, String> data);

    List<TypZlavyEntity> getMainDiscountTypes();

    List<Discount> getDiscounts(DiscountTypes discountType);

    Map<String,String> insertMainDiscountType(Map<String,String> data);

    Map<String,String> updateMainDiscountType(String mainCategoryId, Map<String,String> data);

    Map<String,String> deleteMainDiscountType(int mainDiscountTypeId);

    TypZlavyEntity getMainDiscountType(int mainDiscountTypeId);

    Map<String, String> insertDiscount(Map<String, String> data);

    Discount getDiscount(int discountId);

    Map<String, String> updateDiscount(int discountIdNumber, Map<String, String> data);

    List<Discount> getAppliedDiscounts(int id, AppliedDiscountTypes appliedDiscountType);

    List<String> getAppliedDiscountTypes(int id, AppliedDiscountTypes appliedDiscountType);

    Map<String, String> applyOnlyDiscounts(int id, List<Integer> discounts, AppliedDiscountTypes productCategory);

    boolean hasAppliedDiscount(int id, AppliedDiscountTypes appliedDiscountType);
}
