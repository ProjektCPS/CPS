package dataAccessObjects;

import entities.UcetEntity;

import java.util.List;

public interface AdminDao {
    public List<UcetEntity> getAccounts();
}
