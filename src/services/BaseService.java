package services;

import dataAccessObjects.AppliedDiscountTypes;
import dataAccessObjects.BusinessStates;
import dataAccessObjects.DiscountTypes;
import entities.*;
import entities.customEntities.Discount;

import java.util.List;
import java.util.Map;

public interface BaseService {

    TypPredmetuEntity getMainCategory(int mainCategoryId);

    // Hlavne kategorie
    List<TypPredmetuEntity> getProductTypes();

    // Daj vsetky hlavne typy zlav
    List<TypZlavyEntity> getMainDiscountTypes();

    List<KategorieEntity> getProductCategories(String categoryName);

    KategorieEntity getProductCategory(int categoryId);

    Map<String, String> insertProductCategory(Map<String, String> data);

    Map<String, String> updateProductCategory(int id, Map<String, String> data);

    List<PredmetPredajaEntity> getProduct(String categoryName);

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
}
