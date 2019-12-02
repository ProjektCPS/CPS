package services;

import dataAccessObjects.BusinessStates;
import entities.RegistrovanyUzivatelEntity;
import entities.KategorieEntity;
import entities.PredmetPredajaEntity;
import entities.UcetEntity;

import javax.json.JsonObject;
import java.util.List;
import java.util.Map;

public interface BaseService {
    public UcetEntity login(String username, String password);

    public String registration(UcetEntity user);

    public List<String> getProductType(String id_tenant);

    public List<KategorieEntity> getProductCategories(String categoryName);

    public List<PredmetPredajaEntity> getProduct(String categoryName);

    public List<UcetEntity> geAdminAccounts();

    public Object[] getAdminAccount(int adminId, BusinessStates state);

    public List<RegistrovanyUzivatelEntity> getExternalSystemAccounts();

    public Object[] getExternalSystemAccount(int adminId, BusinessStates state);

    public void insertAccount(Map<String, String> accountData);

    public void updateAccount(int adminId, Map<String, String> accountData);
}
