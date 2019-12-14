package dataAccessObjects;

import entities.TenantEntity;
import entities.UcetEntity;

import javax.json.JsonObject;
import java.util.List;
import java.util.Map;

public interface AdminDao {
    public List<UcetEntity> getAccounts();

    public Object[] getAccount(int adminId, BusinessStates state);

    Map<String, String> insertOrUpdateAccount(int adminId, Map<String, String> accountData);

    List<TenantEntity> getTenants();
}
