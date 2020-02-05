package dataAccessObjects;

import entities.*;
import entities.customEntities.CategoryProducts;
import entities.customEntities.Discount;
import entities.customEntities.Product;
import org.hibernate.Session;

import utilities.DateUtil;
import utilities.HibernateUtil;

import javax.persistence.NoResultException;
import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BaseDaoImplement implements BaseDao {
    private int tenantId;

    public BaseDaoImplement() {
        this.tenantId = -1;
    }

    public BaseDaoImplement(int tenantId) {
        this.tenantId = tenantId;
    }

    private String getStringId() {
        return String.valueOf(tenantId);
    }

    @Override
    public TypPredmetuEntity getMainCategory(int mainCategoryId) {
        Session session = HibernateUtil.getSessionByTenant(getStringId());

        TypPredmetuEntity mainCategory = null;
        if (session != null) {
            try {
                CriteriaBuilder builder = session.getCriteriaBuilder();

                CriteriaQuery<TypPredmetuEntity> criteriaQuery = builder.createQuery(TypPredmetuEntity.class);
                Root<TypPredmetuEntity> root = criteriaQuery.from(TypPredmetuEntity.class);
                criteriaQuery.select(root).where(builder.equal(root.get("idTypu"), mainCategoryId));

                mainCategory = session.createQuery(criteriaQuery).getSingleResult();
            } catch (NoResultException exception) {
                System.out.println("Object not found"
                        + exception.getMessage());
                return null;
            } catch (Exception exception) {
                System.out.println("Exception occred while reading user data: "
                        + exception.getMessage());
                return null;
            } finally {
                session.close();
            }

        } else {
            System.out.println("DB server down.....");
        }
        return mainCategory;
    }

    @Override
    public List<TypPredmetuEntity> getProductsTypes() {
        Session session = HibernateUtil.getSessionByTenant(getStringId());

        List<TypPredmetuEntity> list = null;
        if (session != null) {
            try {
                CriteriaBuilder builder = session.getCriteriaBuilder();

                // Using FROM and JOIN
                CriteriaQuery<TypPredmetuEntity> criteriaQuery = builder.createQuery(TypPredmetuEntity.class);
                Root<TypPredmetuEntity> typeRoot = criteriaQuery.from(TypPredmetuEntity.class);
                criteriaQuery.select(typeRoot);

                list = session.createQuery(criteriaQuery).getResultList();
            } catch (NoResultException exception) {
                System.out.println("Object not found"
                        + exception.getMessage());
                return null;
            } catch (Exception exception) {
                System.out.println("Exception occred while reading user data: "
                        + exception.getMessage());
                return null;
            } finally {
                session.close();
            }
        } else {
            System.out.println("DB server down.....");
        }
        return list;
    }

    @Override
    public List<CategoryProducts> getProductCategories(String categoryName) {
        Session session = HibernateUtil.getSessionByTenant(getStringId());
        List<CategoryProducts> list = new ArrayList<>();
        if (session != null) {
            try {
                CriteriaBuilder builder = session.getCriteriaBuilder();

                // Using FROM and JOIN
                CriteriaQuery<KategorieEntity> criteriaQuery = builder.createQuery(KategorieEntity.class);
                Root<TypPredmetuEntity> typeRoot = criteriaQuery.from(TypPredmetuEntity.class);
                Root<KategorieEntity> catRoot = criteriaQuery.from(KategorieEntity.class);
                criteriaQuery.select(catRoot);
                criteriaQuery.where(
                        builder.equal(typeRoot.get("idTypu"), catRoot.get("idTypu")),
                        builder.equal(typeRoot.get("nazov"), categoryName)
                )
                        .distinct(true);

                List<KategorieEntity> response = session.createQuery(criteriaQuery).getResultList();
                response.forEach(category -> {
                    boolean hasAppliedDiscount = hasAppliedDiscount(category.getIdKategorie(), AppliedDiscountTypes.productCategory);
                    list.add(new CategoryProducts(category, hasAppliedDiscount));
                });

            } catch (NoResultException exception) {
                System.out.println("Object not found"
                        + exception.getMessage());
                return null;
            } catch (Exception exception) {
                System.out.println("Exception occred while reading user data: "
                        + exception.getMessage());
                return null;
            } finally {
                session.close();
            }

        } else {
            System.out.println("DB server down.....");
        }
        return list;
    }

    @Override
    public KategorieEntity getProductCategory(int categoryId) {
        Session session = HibernateUtil.getSessionByTenant(getStringId());

        KategorieEntity productCategory = null;
        if (session != null) {
            try {
                CriteriaBuilder builder = session.getCriteriaBuilder();

                CriteriaQuery<KategorieEntity> criteriaQuery = builder.createQuery(KategorieEntity.class);
                Root<KategorieEntity> root = criteriaQuery.from(KategorieEntity.class);
                criteriaQuery.select(root).where(builder.equal(root.get("idKategorie"), categoryId));

                productCategory = session.createQuery(criteriaQuery).getSingleResult();
            } catch (NoResultException exception) {
                System.out.println("Object not found"
                        + exception.getMessage());
                return null;
            } catch (Exception exception) {
                System.out.println("Exception occred while reading user data: "
                        + exception.getMessage());
                return null;
            } finally {
                session.close();
            }

        } else {
            System.out.println("DB server down.....");
        }
        return productCategory;
    }

    @Override
    public Map<String, String> insertProductCategory(Map<String, String> data) {
        Map<String, String> response = new HashMap<>();

        try (Session session = HibernateUtil.getSessionByTenant(getStringId())) {
            KategorieEntity newKategorieEntity = new KategorieEntity();
            newKategorieEntity.setNazov(data.get("category-name"));
            newKategorieEntity.setIdTypu(Integer.parseInt(data.get("mainCategoryId")));

            System.out.println("Inserting: " + newKategorieEntity.getNazov());
            session.beginTransaction();
            session.save(newKategorieEntity);
            session.getTransaction().commit();
        } catch (Exception exception) {
            System.out.println("Exception occurred while reading user data: "
                    + exception.getMessage());
            response.put("err", "Nepodarilo sa vlozit kategoriu.");
            return response;
        }

        return response;
    }

    @Override
    public Map<String, String> updateProductCategory(int id, Map<String, String> data) {
        Map<String, String> response = new HashMap<>();

        try (Session session = HibernateUtil.getSessionByTenant(getStringId())) {
            KategorieEntity newKategorieEntity = new KategorieEntity();
            newKategorieEntity.setNazov(data.get("category-name"));
            newKategorieEntity.setIdKategorie(id);
            newKategorieEntity.setIdTypu(Integer.parseInt(data.get("mainCategoryId")));

            System.out.println("Updating: " + newKategorieEntity.getNazov());
            session.beginTransaction();
            session.update(newKategorieEntity);
            session.getTransaction().commit();
        } catch (Exception exception) {
            System.out.println("Exception occurred while reading user data: "
                    + exception.getMessage());
            response.put("err", "Nepodarilo sa editovat kategoriu.");
            return response;
        }

        return response;
    }

    @Override
    public List<Product> getProducts(String categoryName) {
        Session session = HibernateUtil.getSessionByTenant(getStringId());
        List<Product> list = new ArrayList<>();
        if (session != null) {
            try {
                CriteriaBuilder builder = session.getCriteriaBuilder();

                // Using FROM and JOIN
                CriteriaQuery<PredmetPredajaEntity> criteriaQuery = builder.createQuery(PredmetPredajaEntity.class);
                Root<PredmetPredajaEntity> predmetRoot = criteriaQuery.from(PredmetPredajaEntity.class);
                Root<KategorieEntity> catRoot = criteriaQuery.from(KategorieEntity.class);

                criteriaQuery.select(predmetRoot);
                criteriaQuery.where(
                        builder.equal(predmetRoot.get("idKategorie"), catRoot.get("idKategorie")),
                        builder.equal(catRoot.get("nazov"), categoryName)
                )
                        .distinct(true);

                List<PredmetPredajaEntity> response = session.createQuery(criteriaQuery).getResultList();
                response.forEach(product -> {
                    List<String> appliedDiscountTypes = getAppliedDiscountTypes(product.getIdPredmetu(), AppliedDiscountTypes.product);
                    list.add(new Product(product, !appliedDiscountTypes.isEmpty(), appliedDiscountTypes));
                });
            } catch (NoResultException exception) {
                System.out.println("Object not found"
                        + exception.getMessage());
                return null;
            } catch (Exception exception) {
                System.out.println("Exception occred while reading user data: "
                        + exception.getMessage());
                return null;
            } finally {
                session.close();
            }

        } else {
            System.out.println("DB server down.....");
        }
        return list;
    }

    @Override
    public List<String> getAppliedDiscountTypes(int id, AppliedDiscountTypes appliedDiscountType) {
       List<Discount> appliedDiscounts = getAppliedDiscounts(id, appliedDiscountType);
      return appliedDiscounts
                .stream()
                .map(
                        item -> {
                            if (item.getCenovaZlavaEntity() != null) {
                                return DiscountTypes.PRICE_DISCOUNT.toString();
                            } else if (item.getPercentualnaZlavaEntity() != null) {
                                return DiscountTypes.PERCENT_DISCOUNT.toString();
                            } else if (item.getKvantitovaZlavaEntity() != null) {
                                return DiscountTypes.QUANTITY_DISCOUNT.toString();
                            } else if (item.getDatumovaZlavaEntity() != null) {
                                //TODO:
                            }
                            return null;
                        })
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<RegistrovanyUzivatelEntity> getExternalSystemAccounts() {
        Session session = HibernateUtil.getSessionByTenant(getStringId());
        List<RegistrovanyUzivatelEntity> list = new ArrayList<>();
        if (session != null) {
            try {
                CriteriaBuilder builder = session.getCriteriaBuilder();

                // Using FROM
                CriteriaQuery<RegistrovanyUzivatelEntity> criteriaQuery = builder.createQuery(RegistrovanyUzivatelEntity.class);
                Root<RegistrovanyUzivatelEntity> root = criteriaQuery.from(RegistrovanyUzivatelEntity.class);
                criteriaQuery.select(root).orderBy((builder.asc(root.get("email"))));

                list = session.createQuery(criteriaQuery).getResultList();
            } catch (NoResultException exception) {
                System.out.println("Object not found"
                        + exception.getMessage());
                return null;
            } catch (Exception exception) {
                System.out.println("Exception occred while reading user data: "
                        + exception.getMessage());
                return null;
            } finally {
                if (session != null) {
                    session.close();
                }
            }

        } else {
            System.out.println("DB server down.....");
        }
        return list;
    }

    @Override
    public Object[] getExternalSystemAccount(int adminId, BusinessStates state) {
        Session session = HibernateUtil.getSessionByTenant(getStringId());
        Object[] userAllInformation = null;
        if (session != null) {
            try {
                CriteriaBuilder builder = session.getCriteriaBuilder();

                // Using FROM
                CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
                Root<RegistrovanyUzivatelEntity> rootAccount = criteriaQuery.from(RegistrovanyUzivatelEntity.class);
                Root<OsobaEntity> rootPerson = criteriaQuery.from(OsobaEntity.class);
                Root<MestoEntity> rootCity = criteriaQuery.from(MestoEntity.class);
                Root<OkresEntity> rootRegion = criteriaQuery.from(OkresEntity.class);
                Root<KrajEntity> rootState = criteriaQuery.from(KrajEntity.class);
                Root<KrajinaEntity> rootCountry = criteriaQuery.from(KrajinaEntity.class);
                switch (state) {
                    case PERSON:
                        criteriaQuery.multiselect(rootAccount, rootPerson, rootCity, rootRegion, rootState, rootCountry);
                        criteriaQuery.where(
                                builder.equal(rootAccount.get("rodCislo"), rootPerson.get("rodCislo")),
                                builder.equal(rootPerson.get("psc"), rootCity.get("psc")),
                                builder.equal(rootCity.get("idOkresu"), rootRegion.get("idOkresu")),
                                builder.equal(rootRegion.get("idKraja"), rootState.get("idKraja")),
                                builder.equal(rootState.get("idKrajiny"), rootCountry.get("idKrajiny")),
                                builder.equal(rootAccount.get("idUzivatela"), adminId)
                        );
                        break;
                    case COMPANY:
                        Root<FirmaEntity> rootCompany = criteriaQuery.from(FirmaEntity.class);
                        criteriaQuery.multiselect(rootAccount, rootCompany, rootCity, rootRegion, rootState, rootCountry);
                        criteriaQuery.where(
                                builder.equal(rootAccount.get("ico"), rootCompany.get("ico")),
                                builder.equal(rootCompany.get("psc"), rootCity.get("psc")),
                                builder.equal(rootCity.get("idOkresu"), rootRegion.get("idOkresu")),
                                builder.equal(rootRegion.get("idKraja"), rootState.get("idKraja")),
                                builder.equal(rootState.get("idKrajiny"), rootCountry.get("idKrajiny")),
                                builder.equal(rootAccount.get("idUzivatela"), adminId)
                        );
                        break;
                    case SELF_EMPLOYED:
                        rootCompany = criteriaQuery.from(FirmaEntity.class);
                        criteriaQuery.multiselect(rootAccount, rootPerson, rootCompany, rootCity, rootRegion, rootState, rootCountry);
                        criteriaQuery.where(
                                builder.equal(rootAccount.get("rodCislo"), rootPerson.get("rodCislo")),
                                builder.equal(rootAccount.get("ico"), rootCompany.get("ico")),
                                builder.equal(rootCompany.get("ico"), rootPerson.get("ico")),
                                builder.equal(rootPerson.get("psc"), rootCity.get("psc")),
                                builder.equal(rootCity.get("idOkresu"), rootRegion.get("idOkresu")),
                                builder.equal(rootRegion.get("idKraja"), rootState.get("idKraja")),
                                builder.equal(rootState.get("idKrajiny"), rootCountry.get("idKrajiny")),
                                builder.equal(rootAccount.get("idUzivatela"), adminId)
                        );
                        break;
                }

                userAllInformation = session.createQuery(criteriaQuery).getSingleResult();
            } catch (NoResultException exception) {
                System.out.println("Object not found"
                        + exception.getMessage());
                return null;
            } catch (Exception exception) {
                System.out.println("Exception occred while reading user data: "
                        + exception.getMessage());
                return null;
            } finally {
                session.close();
            }

        } else {
            System.out.println("DB server down.....");
        }
        return userAllInformation;
    }

    @Override
    public Map<String, String> insertMainCategory(Map<String, String> data) {
        Map<String, String> response = new HashMap<>();

        try (Session session = HibernateUtil.getSessionByTenant(getStringId())) {
            TypPredmetuEntity newMainCategory = new TypPredmetuEntity();
            newMainCategory.setNazov(data.get("main-category-name"));
            System.out.println("Inserting: " + newMainCategory.getNazov());
            session.beginTransaction();
            session.save(newMainCategory);
            session.getTransaction().commit();
        } catch (Exception exception) {
            System.out.println("Exception occurred while reading user data: "
                    + exception.getMessage());
            response.put("err", "Nepodarilo sa vlozit hlavnu kategoriu.");
            return response;
        }

        return response;
    }

    @Override
    public Map<String, String> updateMainCategory(String productCategoryIdNumber, Map<String, String> data) {
        Map<String, String> response = new HashMap<>();

        try (Session session = HibernateUtil.getSessionByTenant(getStringId())) {
            TypPredmetuEntity newMainCategory = new TypPredmetuEntity();
            newMainCategory.setNazov(data.get("main-category-name"));
            newMainCategory.setIdTypu(Integer.parseInt(data.get("mainCategoryId")));

            System.out.println("Updating: " + newMainCategory.getNazov());
            session.beginTransaction();
            session.update(newMainCategory);
            session.getTransaction().commit();
        } catch (Exception exception) {
            System.out.println("Exception occurred while reading user data: "
                    + exception.getMessage());
            response.put("err", "Nepodarilo sa editovat hlavnu kategoriu.");
            return response;
        }

        return response;
    }

    @Override
    public List<TypZlavyEntity> getMainDiscountTypes() {
        Session session = HibernateUtil.getSessionByTenant(getStringId());

        List<TypZlavyEntity> list = null;
        if (session != null) {
            try {
                CriteriaBuilder builder = session.getCriteriaBuilder();

                // Using FROM and JOIN
                CriteriaQuery<TypZlavyEntity> criteriaQuery = builder.createQuery(TypZlavyEntity.class);
                Root<TypZlavyEntity> typeRoot = criteriaQuery.from(TypZlavyEntity.class);
                criteriaQuery.select(typeRoot);

                list = session.createQuery(criteriaQuery).getResultList();
            } catch (NoResultException exception) {
                System.out.println("Object not found"
                        + exception.getMessage());
                return null;
            } catch (Exception exception) {
                System.out.println("Exception occred while reading user data: "
                        + exception.getMessage());
                return null;
            } finally {
                session.close();
            }
        } else {
            System.out.println("DB server down.....");
        }
        return list;
    }

    @Override
    public List<Discount> getDiscounts(DiscountTypes discountType) {
        Session session = HibernateUtil.getSessionByTenant(getStringId());

        List<Discount> list = new ArrayList<>();
        if (session != null && discountType != null) {
            try {
                CriteriaBuilder builder = session.getCriteriaBuilder();

                // Using FROM and JOIN
                CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
                Root<TypZlavyEntity> rootType = criteriaQuery.from(TypZlavyEntity.class);
                Root<ZlavaEntity> rootDiscount = criteriaQuery.from(ZlavaEntity.class);

                Predicate predicate = null;
                switch (discountType) {
                    case PRICE_DISCOUNT:
                        Root<CenovaZlavaEntity> rootPriceDiscount = criteriaQuery.from(CenovaZlavaEntity.class);
                        criteriaQuery.multiselect(rootType, rootDiscount, rootPriceDiscount);
                        predicate = builder.equal(rootDiscount.get(ZlavaEntity.Fields.idCenovejZlavy.name()), rootPriceDiscount.get(CenovaZlavaEntity.Fields.idCenovejZlavy.name()));
                        break;
                    case QUANTITY_DISCOUNT:
                        Root<KvantitovaZlavaEntity> rootQuantityDiscount = criteriaQuery.from(KvantitovaZlavaEntity.class);
                        criteriaQuery.multiselect(rootType, rootDiscount, rootQuantityDiscount);
                        predicate = builder.equal(rootDiscount.get(ZlavaEntity.Fields.idKvantity.name()), rootQuantityDiscount.get(KvantitovaZlavaEntity.Fields.idKvantity.name()));
                        break;
                    case PERCENT_DISCOUNT:
                        Root<PercentualnaZlavaEntity> rootPercentDiscount = criteriaQuery.from(PercentualnaZlavaEntity.class);
                        criteriaQuery.multiselect(rootType, rootDiscount, rootPercentDiscount);
                        predicate = builder.equal(rootDiscount.get(ZlavaEntity.Fields.idPerZlavy.name()), rootPercentDiscount.get(PercentualnaZlavaEntity.Fields.idPerZlavy.name()));
                        break;
                    case DATE_DISCOUNT:
                        Root<DatumovaZlavaEntity> rootDateDiscount = criteriaQuery.from(DatumovaZlavaEntity.class);
                        criteriaQuery.multiselect(rootType, rootDiscount, rootDateDiscount);
                        predicate = builder.equal(rootDiscount.get(ZlavaEntity.Fields.idDatumu.name()), rootDateDiscount.get(DatumovaZlavaEntity.Fields.idDatumu.name()));
                        break;
                }

                criteriaQuery.where(
                        builder.equal(rootType.get(TypZlavyEntity.Fields.idTypu.name()), rootDiscount.get(ZlavaEntity.Fields.idTypu.name())),
                        predicate
                );

                Object[] resultList = session.createQuery(criteriaQuery).getResultList().toArray();

                for (Object item : resultList) {
                    Object[] objects = (Object[]) item;
                    Discount discount = new Discount((ZlavaEntity) objects[1], (TypZlavyEntity) objects[0]);
                    discount.setDiscountType(objects[2]);

                    list.add(discount);
                }

            } catch (NoResultException exception) {
                System.out.println("Object not found"
                        + exception.getMessage());
                return null;
            } catch (Exception exception) {
                System.out.println("Exception occred while reading user data: "
                        + exception.getMessage());
                return null;
            } finally {
                session.close();
            }
        } else {
            System.out.println("DB server down.....");
        }
        return list;
    }

    @Override
    public Map<String, String> insertMainDiscountType(Map<String, String> data) {
        Map<String, String> response = new HashMap<>();

        try (Session session = HibernateUtil.getSessionByTenant(getStringId())) {
            TypZlavyEntity newMainCategory = new TypZlavyEntity();
            newMainCategory.setNazovTypu(data.get("discount-type-name"));
            System.out.println("Inserting: " + newMainCategory.getNazovTypu());
            session.beginTransaction();
            session.save(newMainCategory);
            session.getTransaction().commit();
        } catch (Exception exception) {
            System.out.println("Exception occurred while reading discount data: "
                    + exception.getMessage());
            response.put("err", "Nepodarilo sa vlozit hlavnu zlavu.");
            return response;
        }

        return response;
    }

    @Override
    public Map<String, String> updateMainDiscountType(String mainCategoryId, Map<String, String> data) {
        Map<String, String> response = new HashMap<>();

        try (Session session = HibernateUtil.getSessionByTenant(getStringId())) {
            TypZlavyEntity mainDiscountType = new TypZlavyEntity();
            mainDiscountType.setNazovTypu(data.get("discount-type-name"));
            mainDiscountType.setIdTypu(Integer.parseInt(data.get("discount-type-id")));

            System.out.println("Updating: " + mainDiscountType.getNazovTypu());
            session.beginTransaction();
            session.update(mainDiscountType);
            session.getTransaction().commit();
        } catch (Exception exception) {
            System.out.println("Exception occurred while reading discount data: "
                    + exception.getMessage());
            response.put("err", "Nepodarilo sa editovat hlavnu zlavu.");
            return response;
        }

        return response;
    }

    @Override
    public Map<String, String> deleteMainDiscountType(int mainDiscountTypeId) {
        Map<String, String> response = new HashMap<>();
        try (Session session = HibernateUtil.getSessionByTenant(getStringId())) {
            CriteriaBuilder builder = session.getCriteriaBuilder();

            // Using FROM and JOIN
            CriteriaQuery<TypZlavyEntity> criteriaQuery = builder.createQuery(TypZlavyEntity.class);
            Root<TypZlavyEntity> typeRoot = criteriaQuery.from(TypZlavyEntity.class);
            criteriaQuery.select(typeRoot)
                    .where(builder.equal(typeRoot.get("idTypu"), mainDiscountTypeId));

            TypZlavyEntity mainDiscountType = session.createQuery(criteriaQuery).getSingleResult();
            System.out.println("Deleting: " + mainDiscountType.getNazovTypu());
            session.beginTransaction();
            session.delete(mainDiscountType);
            session.getTransaction().commit();
        } catch (Exception exception) {
            System.out.println("Exception occurred while trying delete discout type: "
                    + exception.getMessage());
            response.put("err", "Nepodarilo sa vymazat zlavu.");
            return response;
        }

        return response;
    }

    @Override
    public TypZlavyEntity getMainDiscountType(int mainDiscountTypeId) {
        Session session = HibernateUtil.getSessionByTenant(getStringId());

        TypZlavyEntity mainDiscountType = null;
        if (session != null) {
            try {
                CriteriaBuilder builder = session.getCriteriaBuilder();

                CriteriaQuery<TypZlavyEntity> criteriaQuery = builder.createQuery(TypZlavyEntity.class);
                Root<TypZlavyEntity> typeRoot = criteriaQuery.from(TypZlavyEntity.class);
                criteriaQuery.select(typeRoot)
                        .where(builder.equal(typeRoot.get("idTypu"), mainDiscountTypeId));

                mainDiscountType = session.createQuery(criteriaQuery).getSingleResult();
            } catch (NoResultException exception) {
                System.out.println("Object not found"
                        + exception.getMessage());
                return null;
            } catch (Exception exception) {
                System.out.println("Exception occred while reading user data: "
                        + exception.getMessage());
                return null;
            } finally {
                session.close();
            }

        } else {
            System.out.println("DB server down.....");
        }
        return mainDiscountType;
    }

    @Override
    public Map<String, String> insertDiscount(Map<String, String> data) {
        Map<String, String> response = new HashMap<>();

        try (Session session = HibernateUtil.getSessionByTenant(getStringId())) {
            ZlavaEntity newDiscount = prepareDiscount(data);

            System.out.println("Inserting: zlavu s id-" + newDiscount.getIdZlavy());
            session.beginTransaction();
            session.save(newDiscount);
            session.getTransaction().commit();
        } catch (Exception exception) {
            System.out.println("Exception occurred while reading discount data: "
                    + exception.getMessage());
            response.put("err", "Nepodarilo sa vlozit zlavu.");
            return response;
        }

        return response;
    }

    private ZlavaEntity prepareDiscount(Map<String, String> data) {
        ZlavaEntity newDiscount = new ZlavaEntity();
        newDiscount.setIdTypu(Integer.parseInt(data.get("mainDiscountType")));
        try {
            newDiscount.setDatOd(DateUtil.createSqlDate(data.get("discount-from")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            newDiscount.setDatDo(DateUtil.createSqlDate(data.get("discount-to")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        double discountValue = Double.parseDouble(data.get("discount-value"));

        switch (DiscountTypes.getIfExists(data.get("discountType"))) {
            case DATE_DISCOUNT:
                //TODO: implementuj
                break;
            case PRICE_DISCOUNT:
                CenovaZlavaEntity newPriceDiscount = new CenovaZlavaEntity();
                newPriceDiscount.setHodnotaZlavy(discountValue);

                CenovaZlavaEntity priceDiscount = insertPriceDiscountIfNotExist(newPriceDiscount);
                newDiscount.setIdCenovejZlavy(priceDiscount.getIdCenovejZlavy());
                break;
            case PERCENT_DISCOUNT:
                PercentualnaZlavaEntity newPercentDiscount = new PercentualnaZlavaEntity();
                newPercentDiscount.setPercentZlavy(discountValue);

                PercentualnaZlavaEntity percentDiscount = insertPercentDiscountIfNotExist(newPercentDiscount);
                newDiscount.setIdPerZlavy(percentDiscount.getIdPerZlavy());
                break;
            case QUANTITY_DISCOUNT:
                KvantitovaZlavaEntity newQuantityDiscount = new KvantitovaZlavaEntity();
                newQuantityDiscount.setMnozstvo(discountValue);

                KvantitovaZlavaEntity quantityDiscount = insertQuantityDiscountIfNotExist(newQuantityDiscount);
                newDiscount.setIdKvantity(quantityDiscount.getIdKvantity());
                break;
        }

        return newDiscount;
    }

    @Override
    public Discount getDiscount(int discountId) {
        Session session = HibernateUtil.getSessionByTenant(getStringId());

        ZlavaEntity zlavaEntity = checkIfDiscountExists(discountId);
        if (zlavaEntity == null) {
            return null;
        }

        Discount result = null;

        if (session != null) {
            try {
                CriteriaBuilder builder = session.getCriteriaBuilder();

                // Using FROM and JOIN
                CriteriaQuery<Tuple> criteriaQuery = builder.createTupleQuery();
                Root<TypZlavyEntity> rootType = criteriaQuery.from(TypZlavyEntity.class);
                Root<ZlavaEntity> rootDiscount = criteriaQuery.from(ZlavaEntity.class);

                Predicate predicate = null;
                if (zlavaEntity.getIdCenovejZlavy() != null) {
                    Root<CenovaZlavaEntity> rootPriceDiscount = criteriaQuery.from(CenovaZlavaEntity.class);
                    criteriaQuery.multiselect(rootType, rootDiscount, rootPriceDiscount);
                    predicate = builder.equal(rootDiscount.get(ZlavaEntity.Fields.idCenovejZlavy.name()), rootPriceDiscount.get(CenovaZlavaEntity.Fields.idCenovejZlavy.name()));
                } else if (zlavaEntity.getIdKvantity() != null) {
                    Root<KvantitovaZlavaEntity> rootQuantityDiscount = criteriaQuery.from(KvantitovaZlavaEntity.class);
                    criteriaQuery.multiselect(rootType, rootDiscount, rootQuantityDiscount);
                    predicate = builder.equal(rootDiscount.get(ZlavaEntity.Fields.idKvantity.name()), rootQuantityDiscount.get(KvantitovaZlavaEntity.Fields.idKvantity.name()));
                } else if (zlavaEntity.getIdPerZlavy() != null) {
                    Root<PercentualnaZlavaEntity> rootPercentDiscount = criteriaQuery.from(PercentualnaZlavaEntity.class);
                    criteriaQuery.multiselect(rootType, rootDiscount, rootPercentDiscount);
                    predicate = builder.equal(rootDiscount.get(ZlavaEntity.Fields.idPerZlavy.name()), rootPercentDiscount.get(PercentualnaZlavaEntity.Fields.idPerZlavy.name()));
                } else if (zlavaEntity.getIdDatumu() != null) {
                    Root<DatumovaZlavaEntity> rootDateDiscount = criteriaQuery.from(DatumovaZlavaEntity.class);
                    criteriaQuery.multiselect(rootType, rootDiscount, rootDateDiscount);
                    predicate = builder.equal(rootDiscount.get(ZlavaEntity.Fields.idDatumu.name()), rootDateDiscount.get(DatumovaZlavaEntity.Fields.idDatumu.name()));
                }

                criteriaQuery.where(
                        builder.equal(rootType.get(ZlavaEntity.Fields.idTypu.name()), rootDiscount.get("idTypu")),
                        builder.equal(rootDiscount.get(ZlavaEntity.Fields.idZlavy.name()), discountId),
                        predicate
                );

                Tuple queryResult = session.createQuery(criteriaQuery).getSingleResult();

                result = new Discount((ZlavaEntity) queryResult.get(1), (TypZlavyEntity) queryResult.get(0));
                result.setDiscountType(queryResult.get(2));

            } catch (NoResultException exception) {
                System.out.println("Object not found"
                        + exception.getMessage());
                return null;
            } catch (Exception exception) {
                System.out.println("Exception occred while reading user data: "
                        + exception.getMessage());
                return null;
            } finally {
                session.close();
            }

        } else {
            System.out.println("DB server down.....");
        }
        return result;
    }

    @Override
    public Map<String, String> updateDiscount(int discountIdNumber, Map<String, String> data) {
        Map<String, String> response = new HashMap<>();

        try (Session session = HibernateUtil.getSessionByTenant(getStringId())) {
            ZlavaEntity discount = prepareDiscount(data);
            discount.setIdZlavy(discountIdNumber);

            System.out.println("Updating discount id: " + discount.getIdZlavy());
            session.beginTransaction();
            session.update(discount);
            session.getTransaction().commit();
        } catch (Exception exception) {
            System.out.println("Exception occurred while reading user data: "
                    + exception.getMessage());
            response.put("err", "Nepodarilo sa editovat zlavu.");
            return response;
        }

        return response;
    }

    @Override
    public List<Discount> getAppliedDiscounts(int id, AppliedDiscountTypes appliedDiscountType) {
        Session session = HibernateUtil.getSessionByTenant(getStringId());

        List<Discount> list = new ArrayList<>();
        if (session != null) {
            try {
                CriteriaBuilder builder = session.getCriteriaBuilder();

                // Using FROM and JOIN
                CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
                Root<KumulaciaZliavEntity> rootAppliedDiscount = criteriaQuery.from(KumulaciaZliavEntity.class);
                Root<ZlavaEntity> rootDiscount = criteriaQuery.from(ZlavaEntity.class);
                Root<TypZlavyEntity> rootType = criteriaQuery.from(TypZlavyEntity.class);

                Predicate predicate = null;
                switch (appliedDiscountType) {
                    case productCategory:
                        predicate = builder.equal(rootAppliedDiscount.get(KumulaciaZliavEntity.Fields.idKategorie.name()), id);
                        break;
                    case product:
                        predicate = builder.equal(rootAppliedDiscount.get(KumulaciaZliavEntity.Fields.idPredmetu.name()), id);
                        break;
                }
                criteriaQuery.multiselect(rootAppliedDiscount, rootDiscount, rootType);
                criteriaQuery.where(
                        builder.equal(rootType.get(ZlavaEntity.Fields.idTypu.name()), rootDiscount.get("idTypu")),
                        builder.equal(rootAppliedDiscount.get(ZlavaEntity.Fields.idZlavy.name()), rootDiscount.get(KumulaciaZliavEntity.Fields.idZlavy.name())),
                        predicate
                );

                Object[] resultList = session.createQuery(criteriaQuery).getResultList().toArray();

                for (Object item : resultList) {
                    Object[] objects = (Object[]) item;
                    Discount discount = new Discount((ZlavaEntity) objects[1], (TypZlavyEntity) objects[2]);
                    discount.setKumulaciaZliavEntity((KumulaciaZliavEntity) objects[0]);

                    if (discount.getZlavaEntity().getIdPerZlavy() != null) {
                        discount.setPercentualnaZlavaEntity(getPercentDiscount(discount.getZlavaEntity().getIdPerZlavy()));
                    } else if (discount.getZlavaEntity().getIdCenovejZlavy() != null) {
                        discount.setCenovaZlavaEntity(getPriceDiscount(discount.getZlavaEntity().getIdCenovejZlavy()));
                    } else if (discount.getZlavaEntity().getIdKvantity() != null) {
                        discount.setKvantitovaZlavaEntity(getQuantityDiscount(discount.getZlavaEntity().getIdKvantity()));
                    }
                    list.add(discount);
                }

            } catch (NoResultException exception) {
                System.out.println("Object not found"
                        + exception.getMessage());
                return null;
            } catch (Exception exception) {
                System.out.println("Exception occred while reading user data: "
                        + exception.getMessage());
                return null;
            } finally {
                session.close();
            }
        } else {
            System.out.println("DB server down.....");
        }
        return list;
    }

    @Override
    public Map<String, String> applyOnlyDiscounts(int id, List<Integer> discounts, AppliedDiscountTypes appliedDiscountType) {
        Map<String, String> response = new HashMap<>();

        List<Discount> appliedDiscounts = getAppliedDiscounts(id, appliedDiscountType);
        List<KumulaciaZliavEntity> excludedDiscounts = appliedDiscounts.stream()
                .filter(item -> !discounts.contains(item.getZlavaEntity().getIdZlavy()))
                .map(Discount::getKumulaciaZliavEntity)
                .collect(Collectors.toList());

        String excludedDiscountsResponse = removeAppliedDiscounts(excludedDiscounts).get("err");
        if(excludedDiscountsResponse != null) {
            response.put("err", excludedDiscountsResponse);
        }

        discounts.forEach(discountsId -> {
            try (Session session = HibernateUtil.getSessionByTenant(getStringId())) {
                KumulaciaZliavEntity entity = new KumulaciaZliavEntity();
                entity.setIdZlavy(discountsId);
                switch (appliedDiscountType) {
                    case productCategory:
                        entity.setIdKategorie(id);
                        break;
                    case product:
                        entity.setIdPredmetu(id);
                        break;
                }

                if (checkIfDiscountIsApplied(id, discountsId, appliedDiscountType) == null) {
                    System.out.println("Inserting discount id: " + entity.getIdZlavy());
                    session.beginTransaction();
                    session.save(entity);
                    session.getTransaction().commit();
                }
            } catch (Exception exception) {
                System.out.println("Exception occurred while reading user data: "
                        + exception.getMessage());
                response.put("err", "Nepodarilo sa aplikovat zlavu s id: " + discountsId);
            }
        });

        return response;
    }

    private Map<String, String> removeAppliedDiscounts(List<KumulaciaZliavEntity> excludedDiscountsIds) {
        Map<String, String> response = new HashMap<>();

        excludedDiscountsIds.forEach( item -> {
            try (Session session = HibernateUtil.getSessionByTenant(getStringId())) {

                System.out.println("Deleting plikovanu zlavu s id: " + item.getIdZlavy());
                session.beginTransaction();
                session.delete(item);
                session.getTransaction().commit();
            } catch (Exception exception) {
                System.out.println("Exception occurred while trying delete discout type: "
                        + exception.getMessage());
                response.put("err", "Nepodarilo sa odobrat zlavu s id: " + item.getIdZlavy());
            }
        });


        return response;
    }

    private ZlavaEntity checkIfDiscountExists(int discountId) {
        Session session = HibernateUtil.getSessionByTenant(getStringId());

        ZlavaEntity result = null;
        if (session != null) {
            try {
                CriteriaBuilder builder = session.getCriteriaBuilder();

                CriteriaQuery<ZlavaEntity> criteriaQuery = builder.createQuery(ZlavaEntity.class);
                Root<ZlavaEntity> rootDiscount = criteriaQuery.from(ZlavaEntity.class);

                criteriaQuery.select(rootDiscount)
                        .where(builder.equal(rootDiscount.get("idZlavy"), discountId));

                result = session.createQuery(criteriaQuery).getSingleResult();

            } catch (NoResultException exception) {
                System.out.println("Object not found"
                        + exception.getMessage());
                return null;
            } catch (Exception exception) {
                System.out.println("Exception occred while reading user data: "
                        + exception.getMessage());
                return null;
            } finally {
                session.close();
            }

        } else {
            System.out.println("DB server down.....");
        }
        return result;
    }

    private KumulaciaZliavEntity checkIfDiscountIsApplied(int categoryOrProductId, int discountId, AppliedDiscountTypes appliedDiscountType) {
        Session session = HibernateUtil.getSessionByTenant(getStringId());

        KumulaciaZliavEntity result = null;
        if (session != null) {
            try {
                CriteriaBuilder builder = session.getCriteriaBuilder();

                CriteriaQuery<KumulaciaZliavEntity> criteriaQuery = builder.createQuery(KumulaciaZliavEntity.class);
                Root<KumulaciaZliavEntity> rootDiscount = criteriaQuery.from(KumulaciaZliavEntity.class);

                Predicate predicate = null;
                switch (appliedDiscountType) {
                    case productCategory:
                        predicate = builder.equal(rootDiscount.get(KumulaciaZliavEntity.Fields.idKategorie.name()), categoryOrProductId);
                        break;
                    case product:
                        predicate = builder.equal(rootDiscount.get(KumulaciaZliavEntity.Fields.idPredmetu.name()), categoryOrProductId);
                        break;
                }

                criteriaQuery.select(rootDiscount)
                        .where(
                                builder.equal(rootDiscount.get(KumulaciaZliavEntity.Fields.idZlavy.name()), discountId),
                                predicate
                        );

                result = session.createQuery(criteriaQuery).getSingleResult();

            } catch (NoResultException exception) {
                System.out.println("Object not found"
                        + exception.getMessage());
                return null;
            } catch (Exception exception) {
                System.out.println("Exception occred while reading user data: "
                        + exception.getMessage());
                return null;
            } finally {
                session.close();
            }

        } else {
            System.out.println("DB server down.....");
        }
        return result;
    }

    private CenovaZlavaEntity insertPriceDiscountIfNotExist(CenovaZlavaEntity newDiscount) {
        Session session = HibernateUtil.getSessionByTenant(getStringId());
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<CenovaZlavaEntity> query = builder.createQuery(CenovaZlavaEntity.class);
        Root<CenovaZlavaEntity> root = query.from(CenovaZlavaEntity.class);
        query.select(root).where(builder.equal(root.get("hodnotaZlavy"), newDiscount.getHodnotaZlavy()));

        CenovaZlavaEntity cenovaZlavaEntity;

        try {
            cenovaZlavaEntity = session.createQuery(query).getSingleResult();
        } catch (NoResultException exception) {
            System.out.println("Object not found"
                    + exception.getMessage());

            System.out.println("Inserting nova cenova zlava s hodnotou: " + newDiscount.getHodnotaZlavy());
            session.beginTransaction();
            session.save(newDiscount);
            session.getTransaction().commit();

            // after insert return new entity
            return session.createQuery(query).getSingleResult();
        } catch (Exception exception) {
            System.out.println("Exception occurred while reading user data: "
                    + exception.getMessage());
            return null;
        } finally {
            session.close();
        }

        return cenovaZlavaEntity;
    }

    private PercentualnaZlavaEntity insertPercentDiscountIfNotExist(PercentualnaZlavaEntity newDiscount) {
        Session session = HibernateUtil.getSessionByTenant(getStringId());
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<PercentualnaZlavaEntity> query = builder.createQuery(PercentualnaZlavaEntity.class);
        Root<PercentualnaZlavaEntity> root = query.from(PercentualnaZlavaEntity.class);
        query.select(root).where(builder.equal(root.get("percentZlavy"), newDiscount.getPercentZlavy()));

        PercentualnaZlavaEntity percentualnaZlavaEntity;

        try {
            percentualnaZlavaEntity = session.createQuery(query).getSingleResult();
        } catch (NoResultException exception) {
            System.out.println("Object not found"
                    + exception.getMessage());

            System.out.println("Inserting nova cenova zlava s hodnotou: " + newDiscount.getPercentZlavy());
            session.beginTransaction();
            session.save(newDiscount);
            session.getTransaction().commit();

            // after insert return new entity
            return session.createQuery(query).getSingleResult();
        } catch (Exception exception) {
            System.out.println("Exception occurred while reading user data: "
                    + exception.getMessage());
            return null;
        } finally {
            session.close();
        }

        return percentualnaZlavaEntity;
    }

    private KvantitovaZlavaEntity insertQuantityDiscountIfNotExist(KvantitovaZlavaEntity newDiscount) {
        Session session = HibernateUtil.getSessionByTenant(getStringId());
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<KvantitovaZlavaEntity> query = builder.createQuery(KvantitovaZlavaEntity.class);
        Root<KvantitovaZlavaEntity> root = query.from(KvantitovaZlavaEntity.class);
        query.select(root).where(builder.equal(root.get("mnozstvo"), newDiscount.getMnozstvo()));

        KvantitovaZlavaEntity kvantitovaZlavaEntity;

        try {
            kvantitovaZlavaEntity = session.createQuery(query).getSingleResult();
        } catch (NoResultException exception) {
            System.out.println("Object not found"
                    + exception.getMessage());

            System.out.println("Inserting nova cenova zlava s hodnotou: " + newDiscount.getMnozstvo());
            session.beginTransaction();
            session.save(newDiscount);
            session.getTransaction().commit();

            // after insert return new entity
            return session.createQuery(query).getSingleResult();
        } catch (Exception exception) {
            System.out.println("Exception occurred while reading user data: "
                    + exception.getMessage());
            return null;
        } finally {
            session.close();
        }

        return kvantitovaZlavaEntity;
    }

    private CenovaZlavaEntity getPriceDiscount(int id) {
        Session session = HibernateUtil.getSessionByTenant(getStringId());

        CenovaZlavaEntity result = null;
        if (session != null) {
            try {
                CriteriaBuilder builder = session.getCriteriaBuilder();

                CriteriaQuery<CenovaZlavaEntity> criteriaQuery = builder.createQuery(CenovaZlavaEntity.class);
                Root<CenovaZlavaEntity> root = criteriaQuery.from(CenovaZlavaEntity.class);
                criteriaQuery.select(root).where(builder.equal(root.get(CenovaZlavaEntity.Fields.idCenovejZlavy.name()), id));

                result = session.createQuery(criteriaQuery).getSingleResult();
            } catch (NoResultException exception) {
                System.out.println("Object not found"
                        + exception.getMessage());
                return null;
            } catch (Exception exception) {
                System.out.println("Exception occred while reading user data: "
                        + exception.getMessage());
                return null;
            } finally {
                session.close();
            }

        } else {
            System.out.println("DB server down.....");
        }
        return result;
    }

    private PercentualnaZlavaEntity getPercentDiscount(int id) {
        Session session = HibernateUtil.getSessionByTenant(getStringId());

        PercentualnaZlavaEntity result = null;
        if (session != null) {
            try {
                CriteriaBuilder builder = session.getCriteriaBuilder();

                CriteriaQuery<PercentualnaZlavaEntity> criteriaQuery = builder.createQuery(PercentualnaZlavaEntity.class);
                Root<PercentualnaZlavaEntity> root = criteriaQuery.from(PercentualnaZlavaEntity.class);
                criteriaQuery.select(root).where(builder.equal(root.get(PercentualnaZlavaEntity.Fields.idPerZlavy.name()), id));

                result = session.createQuery(criteriaQuery).getSingleResult();
            } catch (NoResultException exception) {
                System.out.println("Object not found"
                        + exception.getMessage());
                return null;
            } catch (Exception exception) {
                System.out.println("Exception occred while reading user data: "
                        + exception.getMessage());
                return null;
            } finally {
                session.close();
            }

        } else {
            System.out.println("DB server down.....");
        }
        return result;
    }

    private KvantitovaZlavaEntity getQuantityDiscount(int id) {
        Session session = HibernateUtil.getSessionByTenant(getStringId());

        KvantitovaZlavaEntity result = null;
        if (session != null) {
            try {
                CriteriaBuilder builder = session.getCriteriaBuilder();

                CriteriaQuery<KvantitovaZlavaEntity> criteriaQuery = builder.createQuery(KvantitovaZlavaEntity.class);
                Root<KvantitovaZlavaEntity> root = criteriaQuery.from(KvantitovaZlavaEntity.class);
                criteriaQuery.select(root).where(builder.equal(root.get(KvantitovaZlavaEntity.Fields.idKvantity.name()), id));

                result = session.createQuery(criteriaQuery).getSingleResult();
            } catch (NoResultException exception) {
                System.out.println("Object not found"
                        + exception.getMessage());
                return null;
            } catch (Exception exception) {
                System.out.println("Exception occred while reading user data: "
                        + exception.getMessage());
                return null;
            } finally {
                session.close();
            }

        } else {
            System.out.println("DB server down.....");
        }
        return result;
    }

    @Override
    public boolean hasAppliedDiscount(int id, AppliedDiscountTypes appliedDiscountType) {
        return !getAppliedDiscounts(id, appliedDiscountType).isEmpty();
    }

    @Override
    public Map<String, String> insertProduct(Map<String, String> data) {
        Map<String, String> response = new HashMap<>();

        try (Session session = HibernateUtil.getSessionByTenant(getStringId())) {
            PredmetPredajaEntity newPredmet = prepareProduct(data);

            System.out.println("Inserting: product s id-" + newPredmet.getIdPredmetu());
            session.beginTransaction();
            session.save(newPredmet);
            session.getTransaction().commit();
        } catch (Exception exception) {
            System.out.println("Exception occurred while reading product data: "
                    + exception.getMessage());
            response.put("err", "Nepodarilo sa vlozit product.");
            return response;
        }

        return response;
    }

    @Override
    public Map<String, String> updateProduct(int productIdNumber, Map<String, String> data) {
        Map<String, String> response = new HashMap<>();

        try (Session session = HibernateUtil.getSessionByTenant(getStringId())) {
            PredmetPredajaEntity product = prepareProduct(data);
            product.setIdPredmetu(productIdNumber);

            System.out.println("Updating product id: " + product.getIdPredmetu());
            session.beginTransaction();
            session.update(product);
            session.getTransaction().commit();
        } catch (Exception exception) {
            System.out.println("Exception occurred while update product data: "
                    + exception.getMessage());
            response.put("err", "Nepodarilo sa editovat product.");
            return response;
        }

        return response;
    }

    private PredmetPredajaEntity prepareProduct(Map<String,String> data) {
        PredmetPredajaEntity newProduct = new PredmetPredajaEntity();
        newProduct.setIdKategorie(Integer.parseInt(data.get("categoryId")));
        newProduct.setCena(Double.parseDouble(data.get("price")));
        newProduct.setNazov(data.get("name"));
        newProduct.setJednotka(data.get("unit"));
        try {
            newProduct.setDatumExpiracie(DateUtil.createSqlDate(data.get("date-expiracion")));
            newProduct.setSerioveCislo(data.get("serial-number"));
            newProduct.setPopis(data.get("description"));
            newProduct.setZnacka(data.get("brand"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return newProduct;
    }
}

