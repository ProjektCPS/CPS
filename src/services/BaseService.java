package services;

import dataAccessObjects.BusinessStates;
import entities.*;

import java.util.List;
import java.util.Map;

public interface BaseService {

    TypPredmetuEntity getMainCategory(int mainCategoryId);

    // Hlavne kategorie
    List<TypPredmetuEntity> getProductTypes();

    // Daj vsetky zlavy
    List<TypZlavyEntity> getAllDiscount();

    List<KategorieEntity> getProductCategories(String categoryName);

    KategorieEntity getProductCategory(int categoryId);

    Map<String, String> insertProductCategory(Map<String, String> data);

    Map<String, String> updateProductCategory(int id, Map<String, String> data);

    List<PredmetPredajaEntity> getProduct(String categoryName);

    List<RegistrovanyUzivatelEntity> getExternalSystemAccounts();

    Object[] getExternalSystemAccount(int adminId, BusinessStates state);

    Map<String, String> insertMainCategory(Map<String, String> data);

    Map<String, String> updateMainCategory(String mainCategoryId, Map<String, String> data);
}
