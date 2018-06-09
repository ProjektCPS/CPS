package services;

import entities.RegistrovanyUzivatelEntity;

public interface BaseService {
    public boolean login(String username, String password);

    public String registration(RegistrovanyUzivatelEntity user);
}
