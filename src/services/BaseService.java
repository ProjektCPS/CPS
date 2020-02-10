package services;

import dataAccessObjects.AppliedDiscountTypes;
import dataAccessObjects.BusinessStates;
import dataAccessObjects.DiscountTypes;
import entities.*;
import entities.customEntities.CategoryProducts;
import entities.customEntities.Discount;
import entities.customEntities.Product;

import java.util.List;
import java.util.Map;

public interface BaseService {

    TypPredmetuEntity getMainCategory(int mainCategoryId);

    // Hlavne kategorie
    List<TypPredmetuEntity> getProductTypes();

    // Daj vsetky hlavne typy zlav
    List<TypZlavyEntity> getMainDiscountTypes();

    List<CategoryProducts> getProductCategories(String categoryName);

    KategorieEntity getProductCategory(int categoryId);

    Map<String, String> insertProductCategory(Map<String, String> data);

    Map<String, String> updateProductCategory(int id, Map<String, String> data);

    List<Product> getProducts(String categoryName);

    List<RegistrovanyUzivatelEntity> getExternalSystemAccounts();

    Object[] getExternalSystemAccount(int adminId, BusinessStates state);

    Map<String, String> insertMainCategory(Map<String, String> data);

    Map<String, String> insertMainDiscountType(Map<String, String> data);

    Map<String, String> updateMainCategory(String mainCategoryId, Map<String, String> data);

    Map<String, String> updateMainDiscountType(String mainCategoryId, Map<String, String> data);

    List<Discount> getDiscounts(DiscountTypes discountType);

    Map<String, String> insertDiscount(Map<String, String> data);

    Map<String,String> deleteMainDiscountType(int mainDiscountTypeId);

    TypZlavyEntity getMainDiscountType(int mainDiscountTypeId);

    Discount getDiscount(int discountId);

    Map<String, String> updateDiscount(int discountIdNumber, Map<String, String> data);

    List<Discount> getAppliedDiscounts(int id, AppliedDiscountTypes appliedDiscountType);

    List<String> getAppliedDiscountTypes(int id, AppliedDiscountTypes appliedDiscountType);

    Map<String, String> applyOnlyDiscounts(int id, List<Integer> discounts, AppliedDiscountTypes productCategory);

    boolean hasAppliedDiscount(int id, AppliedDiscountTypes appliedDiscountType);

    Map<String,String> insertProduct(Map<String,String> data);

    Map<String,String> updateProduct(int productIdNumber, Map<String,String> data);

    PredmetPredajaEntity getProductById(int productId);

    List<Product> getProductAllProduct();

    Map<String,String> deleteProduct(Integer id);
}
