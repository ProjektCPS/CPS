package dataAccessObjects;

import entities.TenantEntity;
import entities.UcetEntity;

import java.util.List;
import java.util.Map;

public interface AdminDao {

    UcetEntity login(String username, String password);

    String register(UcetEntity user);

    List<UcetEntity> getAccounts();

    Object[] getAccount(int adminId, BusinessStates state);

    Map<String, String> insertOrUpdateAccount(int adminId, Map<String, String> accountData);

    List<TenantEntity> getTenants();
}
