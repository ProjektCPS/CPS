package dataAccessObjects;

import entities.*;
import org.hibernate.Session;
import utilities.HibernateUtil;
import utilities.multitenancy.CurrentTenantIdentifierResolverImpl;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImplement implements AdminDao {
    @Override
    public List<UcetEntity> getAccounts() {
        Session session = HibernateUtil.getSessionByTenant(CurrentTenantIdentifierResolverImpl.DEFAULT_TENANT_ID);
        List<UcetEntity> list = new ArrayList<>();
        if (session != null) {
            try {
                CriteriaBuilder builder = session.getCriteriaBuilder();

                // Using FROM
                CriteriaQuery<UcetEntity> criteriaQuery = builder.createQuery(UcetEntity.class);
                Root<UcetEntity> root = criteriaQuery.from(UcetEntity.class);
                criteriaQuery.select(root).orderBy((builder.asc(root.get("uzivatel"))));

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
    public Object[] getAccount(int adminId, BusinessStates state) {
        Session session = HibernateUtil.getSessionByTenant(CurrentTenantIdentifierResolverImpl.DEFAULT_TENANT_ID);
        Object[] userAllInformation = null;
        if (session != null) {
            try {
                CriteriaBuilder builder = session.getCriteriaBuilder();

                // Using FROM
                CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
                Root<UcetEntity> rootAccount = criteriaQuery.from(UcetEntity.class);
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
                                , builder.equal(rootAccount.get("idAdmin"), adminId)
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
                                , builder.equal(rootAccount.get("idAdmin"), adminId)
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
                                , builder.equal(rootAccount.get("idAdmin"), adminId)
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
                if (session != null) {
                    session.close();
                }
            }

        } else {
            System.out.println("DB server down.....");
        }
        return userAllInformation;
    }
}
