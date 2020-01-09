package services;

import dataAccessObjects.BusinessStates;
import entities.*;

import java.util.List;
import java.util.Map;

public interface BaseService {
    List<String> getProductType();

    TypPredmetuEntity getMainCategory(int mainCategoryId);

    // Hlavne kategorie
    List<TypPredmetuEntity> getProductTypes();

    // Daj vsetky zlavy
    List<TypZlavyEntity> getAllDiscount();

    List<KategorieEntity> getProductCategories(String categoryName);

    List<PredmetPredajaEntity> getProduct(String categoryName);

    List<RegistrovanyUzivatelEntity> getExternalSystemAccounts();

    Object[] getExternalSystemAccount(int adminId, BusinessStates state);

    Map<String, String> insertMainCategory(Map<String, String> data);

    Map<String, String> updateMainCategory(String mainCategoryId, Map<String, String> data);
}
