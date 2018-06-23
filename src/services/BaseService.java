package services;

import entities.UcetEntity;

import java.util.List;

public interface BaseService {
    public UcetEntity login(String username, String password);

    public String registration(UcetEntity user);

    public List<String> getProductCategories(int id_admin);
}
