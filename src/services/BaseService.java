package services;

import dataAccessObjects.BusinessStates;
import entities.*;

import java.util.List;

public interface BaseService {
    List<String> getProductType();

    List<KategorieEntity> getProductCategories(String categoryName);

    List<PredmetPredajaEntity> getProduct(String categoryName);

    List<RegistrovanyUzivatelEntity> getExternalSystemAccounts();

    Object[] getExternalSystemAccount(int adminId, BusinessStates state);
}
