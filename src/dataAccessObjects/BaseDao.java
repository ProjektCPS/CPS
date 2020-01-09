package dataAccessObjects;

import entities.*;

import java.util.List;
import java.util.Map;

public interface BaseDao {

    List<String> getProductsType();

    TypPredmetuEntity getMainCategory(int mainCategoryId);

    List<TypPredmetuEntity> getProductsTypes();

    List<KategorieEntity> getProductCategories(String categoryName);

    List<PredmetPredajaEntity> getProduct(String categoryName);

    List<RegistrovanyUzivatelEntity> getExternalSystemAccounts();

    Object[] getExternalSystemAccount(int adminId, BusinessStates state);

    Map<String, String> insertMainCategory(Map<String, String> data);

    Map<String, String> updateMainCategory(String mainCategoryId, Map<String, String> data);

    List<TypZlavyEntity> getAllDiscountsTypes();
}
