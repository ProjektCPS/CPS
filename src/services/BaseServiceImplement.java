package services;

import dataAccessObjects.BaseDao;
import dataAccessObjects.BaseDaoImplement;
import entities.KategorieEntity;
import entities.UcetEntity;

import java.util.List;

public class BaseServiceImplement implements BaseService {
    private BaseDao baseDao = new BaseDaoImplement();

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
    public List<String> getProduct(int id_admin, String categoryName) {
        return baseDao.getProduct(id_admin, categoryName);
    }
}
