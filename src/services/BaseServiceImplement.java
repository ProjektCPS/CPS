package services;

import dataAccessObjects.BaseDao;
import dataAccessObjects.BaseDaoImplement;
import entities.UcetEntity;

public class BaseServiceImplement implements BaseService {
    private BaseDao loginDao = new BaseDaoImplement();

    @Override
    public UcetEntity login(String username, String password) {
        return loginDao.login(username, password);
    }

    @Override
    public String registration(UcetEntity user) {
        return loginDao.register(user);
    }
}
