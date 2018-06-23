package dataAccessObjects;

import entities.UcetEntity;

import java.util.List;

public interface BaseDao {
    public UcetEntity login(String username, String password);

    public String register(UcetEntity user);

    public List<String> getProductCategories(int id_admin);
}
