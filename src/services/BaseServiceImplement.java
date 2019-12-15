package services;

import dataAccessObjects.*;
import entities.*;

import java.util.List;

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
}
