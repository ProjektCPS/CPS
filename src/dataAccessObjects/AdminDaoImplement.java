package dataAccessObjects;

import entities.*;
import org.hibernate.Session;
import utilities.HashPasswordUtil;
import utilities.HibernateUtil;
import utilities.multitenancy.CurrentTenantIdentifierResolverImpl;

import javax.json.JsonObject;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.xml.transform.Result;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utilities.HashPasswordUtil.hashPassword;
import static utilities.Validator.isStringNumber;

public class AdminDaoImplement implements AdminDao {

    @Override
    public UcetEntity login(String username, String password) {
        Session session = HibernateUtil.getSessionByTenant(CurrentTenantIdentifierResolverImpl.DEFAULT_TENANT_ID);
        if (session != null) {
            try {
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<UcetEntity> query = builder.createQuery(UcetEntity.class);
                Root<UcetEntity> root = query.from(UcetEntity.class);
                query.select(root).where(builder.equal(root.get("email"), username));

                UcetEntity user = session.createQuery(query).getSingleResult();
                String hashPass = HashPasswordUtil.hashPassword(password);
                if (HashPasswordUtil.hashPassword(password).equals(user.getHeslo())) {
                    return user;
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
                if (session != null) {
                    session.close();
                }
            }
        } else {
            System.out.println("DB server down.....");
        }
        return null;
    }

    @Override
    public String register(UcetEntity user) {
        return null;
    }

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
                System.out.println("Exception occurred while reading user data: "
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
    public Map<String, String> insertOrUpdateAccount(int adminId, Map<String, String> accountData) {
        boolean isUpdate = adminId != -1;
        //TODO: neda sa zmenit nazov Mesta pre jedinece PSC. Asi treba spravit kompozitny primarny kluc (Nazov mesta, psc)
        Session session = HibernateUtil.getSessionByTenant(CurrentTenantIdentifierResolverImpl.DEFAULT_TENANT_ID);
        Map<String, String> response = new HashMap<>();

        // create entities
        KrajinaEntity newKrajinaEntity = new KrajinaEntity();
        KrajEntity newKrajEntity = new KrajEntity();
        OkresEntity newOkresEntity = new OkresEntity();
        MestoEntity newMestoEntity = new MestoEntity();
        OsobaEntity newOsobaEntity = new OsobaEntity();
        UcetEntity newUcetEntity = new UcetEntity();

        newKrajinaEntity.setNazov(accountData.get("country"));
        newKrajEntity.setNazov(accountData.get("state"));
        newOkresEntity.setNazov(accountData.get("region"));

        newMestoEntity.setNazov(accountData.get("city"));
        newMestoEntity.setUlica(accountData.get("street"));
        newMestoEntity.setCisloDomu(isStringNumber(accountData.get("streetNumber")) ? Integer.parseInt(accountData.get("streetNumber")) : -1);
        newMestoEntity.setPsc(accountData.get("postcode"));

        newOsobaEntity.setMeno(accountData.get("first-name"));
        newOsobaEntity.setPriezvisko(accountData.get("last-name"));
        newOsobaEntity.setPsc(accountData.get("postcode"));
        newOsobaEntity.setRodCislo(accountData.get("RC"));

        newUcetEntity.setRodCislo(accountData.get("RC"));
        newUcetEntity.setTenantId(isStringNumber(accountData.get("tenant-id")) ? Integer.parseInt(accountData.get("tenant-id")) : null);
        newUcetEntity.setEmail(accountData.get("email"));
        newUcetEntity.setRola(accountData.get("role"));
        newUcetEntity.setHeslo(hashPassword(accountData.get("password") == null ? "" : accountData.get("password")));
        newUcetEntity.setActive(isStringNumber(accountData.get("active")) ? Integer.parseInt(accountData.get("active")) : 0);

        if (session != null) {
            // if adminId == -1 ,then insert
            if (!isUpdate) {

                UcetEntity ucetEntity = checkIfAccountExists(newUcetEntity);
                if (ucetEntity != null) {
                    //Todo: send to client
                    System.out.println("Ucet uz existuje. Zadajte jedinecy email, rodne cislo a tenant id");
                    response.put("err", "Ucet uz existuje. Zadajte jedinecy email, rodne cislo a tenant id");
                    return response;
                }

                KrajinaEntity country = insertCountryIfNotExistByName(newKrajinaEntity);

                newKrajEntity.setIdKrajiny(country.getIdKrajiny());
                KrajEntity state = insertStateIfNotExistByName(newKrajEntity);

                newOkresEntity.setIdKraja(state.getIdKraja());
                OkresEntity region = insertRegionIfNotExistByName(newOkresEntity);

                newMestoEntity.setIdOkresu(region.getIdOkresu());
                MestoEntity mesto = insertCityIfNotExistByName(newMestoEntity);

                newOsobaEntity.setPsc(mesto.getPsc());
                boolean personInserted = insertPerson(newOsobaEntity);
                if (!personInserted) {
                    System.out.println("Nepodarilo sa insertovat do tabulky osoba - osobu s rodnym cislom : " + newOsobaEntity.getRodCislo());
                    response.put("err", "Nepodarilo sa vytvorit ucet");
                    return response;
                }

                boolean accountInserted = insertAccount(newUcetEntity);
                if (!accountInserted) {
                    System.out.println("Nepodarilo sa insertovat do tabulky ucet - osobu s rodnym cislom : " + newOsobaEntity.getRodCislo());
                    response.put("err", "Nepodarilo sa vytvorit ucet");
                    return response;
                }

            } else {
                Object[] userAllInformation = getAccount(adminId, BusinessStates.PERSON);

                // create entities
                KrajinaEntity krajinaEntity = new KrajinaEntity();
                KrajEntity krajEntity = new KrajEntity();
                OkresEntity okresEntity = new OkresEntity();
                MestoEntity mestoEntity = new MestoEntity();
                OsobaEntity osobaEntity = new OsobaEntity();
                UcetEntity ucetEntity = new UcetEntity();

                for (Object o : userAllInformation) {
                    if (o instanceof UcetEntity) {
                        ucetEntity = (UcetEntity) o;
                    }
                    if (o instanceof OsobaEntity) {
                        osobaEntity = (OsobaEntity) o;
                    }
                    if (o instanceof MestoEntity) {
                        mestoEntity = (MestoEntity) o;
                    }
                    if (o instanceof OkresEntity) {
                        okresEntity = (OkresEntity) o;
                    }
                    if (o instanceof KrajEntity) {
                        krajEntity = (KrajEntity) o;
                    }
                    if (o instanceof KrajinaEntity) {
                        krajinaEntity = (KrajinaEntity) o;
                    }
                }

                KrajinaEntity country = insertCountryIfNotExistByName(newKrajinaEntity);

                newKrajEntity.setIdKrajiny(country.getIdKrajiny());
                KrajEntity state = insertStateIfNotExistByName(newKrajEntity);

                newOkresEntity.setIdKraja(state.getIdKraja());
                OkresEntity region = insertRegionIfNotExistByName(newOkresEntity);

                newMestoEntity.setIdOkresu(region.getIdOkresu());
                MestoEntity mesto = insertCityIfNotExistByName(newMestoEntity);

                newOsobaEntity.setPsc(mesto.getPsc());
                newOsobaEntity.setRodCislo(osobaEntity.getRodCislo()); // TODO: treba vymysliset co s rodnym cislom ak sa zmeni, zatial som to zakazal.
                boolean personUpdated = updatePerson(newOsobaEntity);
                if (!personUpdated) {
                    System.out.println("Nepodarilo sa updatnut tabulku osoba - osobu s rodnym cislom : " + newOsobaEntity.getRodCislo());
                    response.put("err", "Nepodarilo sa updatnut ucet");
                    return response;
                }

                newUcetEntity.setHeslo(ucetEntity.getHeslo());
                newUcetEntity.setRodCislo(ucetEntity.getRodCislo());
                newUcetEntity.setIdAdmin(ucetEntity.getIdAdmin());
                newUcetEntity.setUzivatel(ucetEntity.getUzivatel());
                boolean accountInserted = updateAccount(newUcetEntity);
                if (!accountInserted) {
                    System.out.println("Nepodarilo sa updatnut tabulku ucet - osobu s rodnym cislom : " + newOsobaEntity.getRodCislo());
                    response.put("err", "Nepodarilo sa updatnut ucet");
                    return response;
                }
            }
        } else {
            System.out.println("DB server down.....");
        }

        return response;
    }

    @Override
    public List<TenantEntity> getTenants() {
        Session session = HibernateUtil.getSessionByTenant(CurrentTenantIdentifierResolverImpl.DEFAULT_TENANT_ID);

        List<TenantEntity> list = new ArrayList<>();
        if (session != null) {
            try {
                CriteriaBuilder builder = session.getCriteriaBuilder();

                CriteriaQuery<TenantEntity> criteriaQuery = builder.createQuery(TenantEntity.class);
                Root<TenantEntity> root = criteriaQuery.from(TenantEntity.class);
                criteriaQuery.select(root);

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
    public TenantEntity getTenant(int tenantId) {
        Session session = HibernateUtil.getSessionByTenant(CurrentTenantIdentifierResolverImpl.DEFAULT_TENANT_ID);

        TenantEntity tenant = null;
        if (session != null) {
            try {
                CriteriaBuilder builder = session.getCriteriaBuilder();

                CriteriaQuery<TenantEntity> criteriaQuery = builder.createQuery(TenantEntity.class);
                Root<TenantEntity> root = criteriaQuery.from(TenantEntity.class);
                criteriaQuery.select(root).where(builder.equal(root.get("tenantId"), tenantId));

                tenant = session.createQuery(criteriaQuery).getSingleResult();
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
        return tenant;
    }

    private boolean updateAccount(UcetEntity newUcetEntity) {
        Session session = HibernateUtil.getSessionByTenant(CurrentTenantIdentifierResolverImpl.DEFAULT_TENANT_ID);

        try {
            System.out.println("Updating: " + newUcetEntity.getRodCislo());
            session.beginTransaction();
            session.update(newUcetEntity);
            session.getTransaction().commit();
        } catch (Exception exception) {
            System.out.println("Exception occurred while updating user data: "
                    + exception.getMessage());
            return false;
        } finally {
            session.close();
        }


        return true;
    }


    private boolean updatePerson(OsobaEntity newOsobaEntity) {
        Session session = HibernateUtil.getSessionByTenant(CurrentTenantIdentifierResolverImpl.DEFAULT_TENANT_ID);

        try {
            System.out.println("Updating: " + newOsobaEntity.getRodCislo());
            session.beginTransaction();
            session.update(newOsobaEntity);
            session.getTransaction().commit();
        } catch (Exception exception) {
            System.out.println("Exception occurred while reading user data: "
                    + exception.getMessage());
            return false;
        } finally {
            session.close();
        }

        return true;
    }

    private boolean insertAccount(UcetEntity newUcetEntity) {
        Session session = HibernateUtil.getSessionByTenant(CurrentTenantIdentifierResolverImpl.DEFAULT_TENANT_ID);

        try {
            System.out.println("Inserting: " + newUcetEntity.getRodCislo());
            session.beginTransaction();
            session.save(newUcetEntity);
            session.getTransaction().commit();
        } catch (Exception exception) {
            System.out.println("Exception occurred while reading user data: "
                    + exception.getMessage());
            return false;
        } finally {
            session.close();
        }

        return true;
    }

    private boolean insertPerson(OsobaEntity newOsobaEntity) {
        Session session = HibernateUtil.getSessionByTenant(CurrentTenantIdentifierResolverImpl.DEFAULT_TENANT_ID);

        try {
            System.out.println("Inserting: " + newOsobaEntity.getRodCislo());
            session.beginTransaction();
            session.save(newOsobaEntity);
            session.getTransaction().commit();
        } catch (Exception exception) {
            System.out.println("Exception occurred while reading user data: "
                    + exception.getMessage());
            return false;
        } finally {
            session.close();
        }

        return true;
    }

    private UcetEntity checkIfAccountExists(UcetEntity newUcetEntity) {
        Session session = HibernateUtil.getSessionByTenant(CurrentTenantIdentifierResolverImpl.DEFAULT_TENANT_ID);
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<UcetEntity> query = builder.createQuery(UcetEntity.class);
        Root<UcetEntity> root = query.from(UcetEntity.class);
        query.select(root).where(
                builder.or(
                        builder.equal(root.get("rodCislo"), newUcetEntity.getRodCislo()),
                        builder.equal(root.get("uzivatel"), newUcetEntity.getRodCislo()),
                        builder.equal(root.get("email"), newUcetEntity.getRodCislo())
                )
        );

        UcetEntity ucetEntity;

        try {
            ucetEntity = session.createQuery(query).getSingleResult();
        } catch (NoResultException exception) {
            System.out.println("Object not found"
                    + exception.getMessage());

            return null;
        } catch (Exception exception) {
            System.out.println("Exception occurred while reading user data: "
                    + exception.getMessage());
            return null;
        } finally {
            session.close();
        }

        return ucetEntity;
    }

    private KrajinaEntity insertCountryIfNotExistByName(KrajinaEntity newKrajinaEntity) {
        Session session = HibernateUtil.getSessionByTenant(CurrentTenantIdentifierResolverImpl.DEFAULT_TENANT_ID);
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<KrajinaEntity> query = builder.createQuery(KrajinaEntity.class);
        Root<KrajinaEntity> root = query.from(KrajinaEntity.class);
        query.select(root).where(builder.equal(root.get("nazov"), newKrajinaEntity.getNazov()));

        KrajinaEntity krajinaEntity;

        try {
            krajinaEntity = session.createQuery(query).getSingleResult();
        } catch (NoResultException exception) {
            System.out.println("Object not found"
                    + exception.getMessage());

            System.out.println("Inserting: " + newKrajinaEntity.getNazov());
            session.beginTransaction();
            session.save(newKrajinaEntity);
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

        return krajinaEntity;
    }

    private KrajEntity insertStateIfNotExistByName(KrajEntity newKrajEntity) {
        Session session = HibernateUtil.getSessionByTenant(CurrentTenantIdentifierResolverImpl.DEFAULT_TENANT_ID);
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<KrajEntity> query = builder.createQuery(KrajEntity.class);
        Root<KrajEntity> root = query.from(KrajEntity.class);
        query.select(root).where(
                builder.equal(root.get("nazov"), newKrajEntity.getNazov()),
                builder.equal(root.get("idKrajiny"), newKrajEntity.getIdKrajiny())
        );

        KrajEntity krajEntity;

        try {
            krajEntity = session.createQuery(query).getSingleResult();
        } catch (NoResultException exception) {
            System.out.println("Object not found"
                    + exception.getMessage());

            System.out.println("Inserting: " + newKrajEntity.getNazov());
            session.beginTransaction();
            session.save(newKrajEntity);
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

        return krajEntity;
    }

    private OkresEntity insertRegionIfNotExistByName(OkresEntity newOkresEntity) {
        Session session = HibernateUtil.getSessionByTenant(CurrentTenantIdentifierResolverImpl.DEFAULT_TENANT_ID);
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<OkresEntity> query = builder.createQuery(OkresEntity.class);
        Root<OkresEntity> root = query.from(OkresEntity.class);
        query.select(root).where(
                builder.equal(root.get("nazov"), newOkresEntity.getNazov()),
                builder.equal(root.get("idKraja"), newOkresEntity.getIdKraja())
        );

        OkresEntity okresEntity;

        try {
            okresEntity = session.createQuery(query).getSingleResult();
        } catch (NoResultException exception) {
            System.out.println("Object not found"
                    + exception.getMessage());

            System.out.println("Inserting: " + newOkresEntity.getNazov());
            session.beginTransaction();
            session.save(newOkresEntity);
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

        return okresEntity;
    }

    private MestoEntity insertCityIfNotExistByName(MestoEntity newMestoEntity) {
        Session session = HibernateUtil.getSessionByTenant(CurrentTenantIdentifierResolverImpl.DEFAULT_TENANT_ID);
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<MestoEntity> query = builder.createQuery(MestoEntity.class);
        Root<MestoEntity> root = query.from(MestoEntity.class);
        query.select(root).where(
                builder.equal(root.get("nazov"), newMestoEntity.getNazov()),
                builder.equal(root.get("idOkresu"), newMestoEntity.getIdOkresu()),
                builder.equal(root.get("psc"), newMestoEntity.getPsc())
        );

        MestoEntity mestoEntity;

        try {
            mestoEntity = session.createQuery(query).getSingleResult();
        } catch (NoResultException exception) {
            System.out.println("Object not found"
                    + exception.getMessage());

            System.out.println("Inserting: " + newMestoEntity.getNazov());
            session.beginTransaction();
            session.save(newMestoEntity);
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

        return mestoEntity;
    }
}
