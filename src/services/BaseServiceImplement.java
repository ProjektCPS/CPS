package services;

import dataAccessObjects.*;
import entities.*;

import javax.json.JsonObject;
import java.util.List;
import java.util.Map;

public class BaseServiceImplement implements BaseService {
    private BaseDao baseDao = new BaseDaoImplement();
    private AdminDao adminDao = new AdminDaoImplement();

    @Override
    public UcetEntity login(String username, String password) {
        return baseDao.login(username, password);
    }

    @Override
    public String registration(UcetEntity user) {
        return baseDao.register(user);
    }

    @Override
    public List<String> getProductType(String id_tenant) {
        return baseDao.getProductsType(id_tenant);
    }

    @Override
    public List<KategorieEntity> getProductCategories(String categoryName) {
        return baseDao.getProductCategories(categoryName);
    }

    @Override
    public List<PredmetPredajaEntity> getProduct(String categoryName) {
        return baseDao.getProduct(categoryName);
    }

    @Override
    public List<RegistrovanyUzivatelEntity> getExternalSystemAccounts() {
        return baseDao.getExternalSystemAccounts();
    }

    @Override
    public Object[] getExternalSystemAccount(int adminId, BusinessStates state) {
        return baseDao.getExternalSystemAccount(adminId, state);
    }

    @Override
    public Map<String, String> insertAccount(Map<String, String> accountData) {
        return adminDao.insertOrUpdateAccount(-1, accountData);
    }

    @Override
    public Map<String, String> updateAccount(int adminId, Map<String, String> accountData) {
        return adminDao.insertOrUpdateAccount(adminId, accountData);
    }

    @Override
    public List<UcetEntity> geAdminAccounts() {
        return adminDao.getAccounts();
    }

    @Override
    public Object[] getAdminAccount(int adminId, BusinessStates state) {
        return adminDao.getAccount(adminId, state);
    }

    @Override
    public List<TenantEntity> getTenants() {
        return adminDao.getTenants();
    }
}
