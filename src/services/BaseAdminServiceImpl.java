package services;

import dataAccessObjects.AdminDao;
import dataAccessObjects.AdminDaoImplement;
import dataAccessObjects.BusinessStates;
import entities.TenantEntity;
import entities.UcetEntity;

import java.util.List;
import java.util.Map;

public class BaseAdminServiceImpl implements BaseAdminService{

    private AdminDao adminDao = new AdminDaoImplement(); // nepotrebuje tenatId pretoze tato servica je pre spolocnu databazu vsetkych UCTOV

    @Override
    public UcetEntity login(String username, String password) {
        return adminDao.login(username, password);
    }

    @Override
    public String registration(UcetEntity user) {
        return adminDao.register(user);
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
