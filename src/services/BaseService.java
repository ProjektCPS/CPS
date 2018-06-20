package services;

import entities.UcetEntity;

public interface BaseService {
    public boolean login(String username, String password);

    public String registration(UcetEntity user);
}
