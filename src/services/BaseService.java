package services;

import entities.UcetEntity;

public interface BaseService {
    public UcetEntity login(String username, String password);

    public String registration(UcetEntity user);
}
