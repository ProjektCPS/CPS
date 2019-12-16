package dataAccessObjects;

import entities.RegistrovanyUzivatelEntity;
import entities.KategorieEntity;
import entities.PredmetPredajaEntity;
import entities.TypPredmetuEntity;

import java.util.List;

public interface BaseDao {

    List<String> getProductsType();

    List<TypPredmetuEntity> getProductsTypes();

    List<KategorieEntity> getProductCategories(String categoryName);

    List<PredmetPredajaEntity> getProduct(String categoryName);

    List<RegistrovanyUzivatelEntity> getExternalSystemAccounts();

    Object[] getExternalSystemAccount(int adminId, BusinessStates state);
}
