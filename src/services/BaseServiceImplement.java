package services;

import dataAccessObjects.BaseDao;
import dataAccessObjects.BaseDaoImplement;
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
    public List<String> getProductCategories(int id_admin) {
        return baseDao.getProductCategories(id_admin);
    }
}
