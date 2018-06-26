package dataAccessObjects;

import entities.KategorieEntity;
import entities.UcetEntity;

import java.util.List;

public interface BaseDao {
    public UcetEntity login(String username, String password);

    public String register(UcetEntity user);

    public List<String> getProductsType(int id_admin);

    public List<KategorieEntity> getProductCategories(int id_admin, String categoryName);

    public List<String> getProduct(int id_admin, String categoryName);
}
