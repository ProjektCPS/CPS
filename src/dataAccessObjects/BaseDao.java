package dataAccessObjects;

import entities.UcetEntity;

public interface BaseDao {
    public boolean login(String username, String password);

    public String register(UcetEntity user);
}
