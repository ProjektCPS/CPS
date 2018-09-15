package services;

import dataAccessObjects.*;
import entities.KategorieEntity;
import entities.PredmetPredajaEntity;
import entities.UcetEntity;

import java.util.List;

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
    public List<String> getProductType(int id_admin) {
        return baseDao.getProductsType(id_admin);
    }

    @Override
    public List<KategorieEntity> getProductCategories(int id_admin, String categoryName) {
        return baseDao.getProductCategories(id_admin, categoryName);
    }

    @Override
    public List<PredmetPredajaEntity> getProduct(int id_admin, String categoryName) {
        return baseDao.getProduct(id_admin, categoryName);
    }

    @Override
    public List<UcetEntity> getAccounts() {
        return adminDao.getAccounts();
    }

    @Override
    public Object[] getAccount(int adminId, BusinessStates state) {
        return adminDao.getAccount(adminId, state);
    }
}