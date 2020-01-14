package dataAccessObjects;

import entities.*;
import entities.customEntities.Discount;

import java.util.List;
import java.util.Map;

public interface BaseDao {

    TypPredmetuEntity getMainCategory(int mainCategoryId);

    List<TypPredmetuEntity> getProductsTypes();

    List<KategorieEntity> getProductCategories(String categoryName);

    KategorieEntity getProductCategory(int categoryId);

    Map<String, String> insertProductCategory(Map<String, String> data);

    Map<String, String> updateProductCategory(int id, Map<String, String> data);

    List<PredmetPredajaEntity> getProduct(String categoryName);

    List<RegistrovanyUzivatelEntity> getExternalSystemAccounts();

    Object[] getExternalSystemAccount(int adminId, BusinessStates state);

    Map<String, String> insertMainCategory(Map<String, String> data);

    Map<String, String> updateMainCategory(String mainCategoryId, Map<String, String> data);

    List<TypZlavyEntity> getAllDiscountsTypes();

    List<Discount> getDiscounts(DiscountTypes discountType);

    Map<String,String> insertMainDiscountType(Map<String,String> data);

    Map<String,String> updateMainDiscountType(String mainCategoryId, Map<String,String> data);

    Map<String,String> deleteMainDiscountType(String mainDiscountTzpe);
}
