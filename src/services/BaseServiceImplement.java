package services;

import dataAccessObjects.*;
import entities.*;
import entities.customEntities.Discount;

import java.util.List;
import java.util.Map;

public class BaseServiceImplement implements BaseService {
    private BaseDao baseDao;

    public BaseServiceImplement() {
        this.baseDao = new BaseDaoImplement();
    }

    public BaseServiceImplement(int tenantId) {
        this.baseDao = new BaseDaoImplement(tenantId);
    }

    @Override
    public List<TypPredmetuEntity> getProductTypes() {
        return baseDao.getProductsTypes();
    }

    @Override
    public List<TypZlavyEntity> getMainDiscountTypes() {
        return baseDao.getMainDiscountTypes();
    }

    @Override
    public TypPredmetuEntity getMainCategory(int mainCategoryId) {
        return baseDao.getMainCategory(mainCategoryId);
    }

    @Override
    public List<KategorieEntity> getProductCategories(String categoryName) {
        return baseDao.getProductCategories(categoryName);
    }

    @Override
    public KategorieEntity getProductCategory(int categoryId) {
        return baseDao.getProductCategory(categoryId);
    }

    @Override
    public Map<String, String> insertProductCategory(Map<String, String> data) {
        return baseDao.insertProductCategory(data);
    }

    @Override
    public Map<String, String> updateProductCategory(int id, Map<String, String> data) {
        return baseDao.updateProductCategory(id, data);
    }

    @Override
    public List<PredmetPredajaEntity> getProduct(String categoryName) {
        return baseDao.getProduct(categoryName);
    }

    @Override
    public List<RegistrovanyUzivatelEntity> getExternalSystemAccounts() {
        return baseDao.getExternalSystemAccounts();
    }

    @Override
    public Object[] getExternalSystemAccount(int adminId, BusinessStates state) {
        return baseDao.getExternalSystemAccount(adminId, state);
    }

    @Override
    public Map<String, String> insertMainCategory(Map<String, String> data) {
        return baseDao.insertMainCategory(data);
    }

    @Override
    public Map<String, String> insertMainDiscountType(Map<String, String> data) {
        return baseDao.insertMainDiscountType(data);
    }

    @Override
    public Map<String, String> updateMainCategory(String mainCategoryId, Map<String, String> data) {
        return baseDao.updateMainCategory(mainCategoryId, data);
    }

    @Override
    public Map<String, String> updateMainDiscountType(String mainCategoryId, Map<String, String> data) {
        return baseDao.updateMainDiscountType(mainCategoryId, data);
    }

    @Override
    public List<Discount> getDiscounts(DiscountTypes discountType) {
        return baseDao.getDiscounts(discountType);
    }

    @Override
    public Map<String, String> insertDiscount(Map<String, String> data) {
        return baseDao.insertDiscount(data);
    }

    @Override
    public Map<String, String> deleteMainDiscountType(int mainDiscountTypeId) {
        return baseDao.deleteMainDiscountType(mainDiscountTypeId);
    }

    @Override
    public TypZlavyEntity getMainDiscountType(int mainDiscountTypeId) {
        return baseDao.getMainDiscountType(mainDiscountTypeId);
    }

    @Override
    public Discount getDiscount(int discountId) {
        return baseDao.getDiscount(discountId);
    }

    @Override
    public Map<String, String> updateDiscount(int discountIdNumber, Map<String, String> data) {
        return baseDao.updateDiscount(discountIdNumber, data);
    }

    @Override
    public List<Discount> getAppliedDiscounts(int id, AppliedDiscountTypes appliedDiscountType) {
        return baseDao.getAppliedDiscounts(id, appliedDiscountType);
    }
}
