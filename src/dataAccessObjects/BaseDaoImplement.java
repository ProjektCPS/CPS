package dataAccessObjects;

import entities.*;
import org.hibernate.Session;

import utilities.HibernateUtil;

import javax.persistence.NoResultException;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<String> getProductsType() {
        Session session = HibernateUtil.getSessionByTenant(getStringId());

        List<String> list = new ArrayList<>();
        if (session != null) {
            try {
                CriteriaBuilder builder = session.getCriteriaBuilder();

                // Using FROM and JOIN
                CriteriaQuery<String> criteriaQuery = builder.createQuery(String.class);
                Root<TypPredmetuEntity> typeRoot = criteriaQuery.from(TypPredmetuEntity.class);
                criteriaQuery.select(typeRoot.get("nazov"));

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
    public List<KategorieEntity> getProductCategories(String categoryName) {
        Session session = HibernateUtil.getSessionByTenant(getStringId());
        List<KategorieEntity> list = new ArrayList<>();
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
    public List<PredmetPredajaEntity> getProduct(String categoryName) {
        Session session = HibernateUtil.getSessionByTenant(getStringId());
        List<PredmetPredajaEntity> list = new ArrayList<>();
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
                                builder.equal(rootAccount.get("rodCislo"), rootPerson.get("rodCislo"))
                                , builder.equal(rootPerson.get("psc"), rootCity.get("psc"))
                                , builder.equal(rootCity.get("idOkresu"), rootRegion.get("idOkresu"))
                                , builder.equal(rootRegion.get("idKraja"), rootState.get("idKraja"))
                                , builder.equal(rootState.get("idKrajiny"), rootCountry.get("idKrajiny"))
                                , builder.equal(rootAccount.get("idUzivatela"), adminId)
                        );
                        break;
                    case COMPANY:
                        Root<FirmaEntity> rootCompany = criteriaQuery.from(FirmaEntity.class);
                        criteriaQuery.multiselect(rootAccount, rootCompany, rootCity, rootRegion, rootState, rootCountry);
                        criteriaQuery.where(
                                builder.equal(rootAccount.get("ico"), rootCompany.get("ico"))
                                , builder.equal(rootCompany.get("psc"), rootCity.get("psc"))
                                , builder.equal(rootCity.get("idOkresu"), rootRegion.get("idOkresu"))
                                , builder.equal(rootRegion.get("idKraja"), rootState.get("idKraja"))
                                , builder.equal(rootState.get("idKrajiny"), rootCountry.get("idKrajiny"))
                                , builder.equal(rootAccount.get("idUzivatela"), adminId)
                        );
                        break;
                    case SELF_EMPLOYED:
                        rootCompany = criteriaQuery.from(FirmaEntity.class);
                        criteriaQuery.multiselect(rootAccount, rootPerson, rootCompany, rootCity, rootRegion, rootState, rootCountry);
                        criteriaQuery.where(
                                builder.equal(rootAccount.get("rodCislo"), rootPerson.get("rodCislo"))
                                , builder.equal(rootAccount.get("ico"), rootCompany.get("ico"))
                                , builder.equal(rootCompany.get("ico"), rootPerson.get("ico"))
                                , builder.equal(rootPerson.get("psc"), rootCity.get("psc"))
                                , builder.equal(rootCity.get("idOkresu"), rootRegion.get("idOkresu"))
                                , builder.equal(rootRegion.get("idKraja"), rootState.get("idKraja"))
                                , builder.equal(rootState.get("idKrajiny"), rootCountry.get("idKrajiny"))
                                , builder.equal(rootAccount.get("idUzivatela"), adminId)
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
    public Map<String, String> updateMainCategory(String mainCategoryId, Map<String, String> data) {
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
}
