package services;

import dataAccessObjects.BaseDao;
import dataAccessObjects.BaseDaoImplement;
import entities.RegistrovanyUzivatelEntity;

public class BaseServiceImplement implements BaseService {
    private BaseDao loginDao = new BaseDaoImplement();

    @Override
    public boolean login(String username, String password) {
        return loginDao.login(username, password);
    }

    @Override
    public String registration(RegistrovanyUzivatelEntity user) {
        return loginDao.register(user);
    }
}
