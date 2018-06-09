package dataAccessObjects;

import entities.RegistrovanyUzivatelEntity;

public interface BaseDao {
    public boolean login(String username, String password);

    public String register(RegistrovanyUzivatelEntity user);
}
