package dataAccessObjects;

import entities.KategorieEntity;
import entities.PredmetPredajaEntity;
import entities.UcetEntity;

import java.util.List;

public interface BaseDao {
    public UcetEntity login(String username, String password);

    public String register(UcetEntity user);

    public List<String> getProductsType(String id_tenant);

    public List<KategorieEntity> getProductCategories(String categoryName);

    public List<PredmetPredajaEntity> getProduct(String categoryName);
}
