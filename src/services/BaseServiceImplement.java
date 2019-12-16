package services;

import dataAccessObjects.*;
import entities.*;

import java.util.List;
import java.util.Map;

public class BaseServiceImplement implements BaseService {
    private BaseDao baseDao;

    public BaseServiceImplement() {
        this.baseDao = new BaseDaoImplement();
    }

    public BaseServiceImplement(int tenantId) {
        this.baseDao = new BaseDaoImplement(tenantId);
    }

    @Override
    public List<String> getProductType() {
        return baseDao.getProductsType();
    }

    @Override
    public List<TypPredmetuEntity> getProductTypes() {
        return baseDao.getProductsTypes();
    }

    @Override
    public TypPredmetuEntity getMainCategory(int mainCategoryId) {
        return baseDao.getMainCategory(mainCategoryId);
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
    public Map<String, String> insertMainCategory(Map<String, String> data) {
        return baseDao.insertMainCategory(data);
    }

    @Override
    public Map<String, String> updateMainCategory(String mainCategoryId, Map<String, String> data) {
        return baseDao.updateMainCategory(mainCategoryId, data);
    }
}
