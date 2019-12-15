package dataAccessObjects;

import entities.RegistrovanyUzivatelEntity;
import entities.KategorieEntity;
import entities.PredmetPredajaEntity;

import java.util.List;

public interface BaseDao {

    public List<String> getProductsType();

    public List<KategorieEntity> getProductCategories(String categoryName);

    public List<PredmetPredajaEntity> getProduct(String categoryName);

    List<RegistrovanyUzivatelEntity> getExternalSystemAccounts();

    Object[] getExternalSystemAccount(int adminId, BusinessStates state);
}
