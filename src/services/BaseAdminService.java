package services;

import dataAccessObjects.BusinessStates;
import entities.TenantEntity;
import entities.UcetEntity;

import java.util.List;
import java.util.Map;

public interface BaseAdminService {
    UcetEntity login(String username, String password);

    String registration(UcetEntity user);

    List<TenantEntity> getTenants();

    Object[] getAdminAccount(int adminId, BusinessStates state);

    List<UcetEntity> geAdminAccounts();

    Map<String, String> insertAccount(Map<String, String> accountData);

    Map<String, String> updateAccount(int adminId, Map<String, String> accountData);

    TenantEntity getTenant(int parseInt);
}
