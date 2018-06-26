package services;

import entities.KategorieEntity;
import entities.UcetEntity;

import java.util.List;

public interface BaseService {
    public UcetEntity login(String username, String password);

    public String registration(UcetEntity user);

    public List<String> getProductType(int id_admin);

    public List<KategorieEntity> getProductCategories(int id_admin, String categoryName);

    public List<String> getProduct(int id_admin, String categoryName);
}
