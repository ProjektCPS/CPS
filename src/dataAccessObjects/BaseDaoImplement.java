package dataAccessObjects;

import entities.KategorieEntity;
import entities.PredmetPredajaEntity;
import entities.TypPredmetuEntity;
import entities.UcetEntity;
import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.query.Query;
import utilities.HashPasswordUtil;
import utilities.HibernateUtil;
import utilities.multitenancy.CurrentTenantIdentifierResolverImpl;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDaoImplement implements BaseDao {
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
    public List<String> getProductsType(int id_admin) {
        Session session = HibernateUtil.getSession();
        List<String> list = new ArrayList<>();
        if (session != null) {
            try {
                CriteriaBuilder builder = session.getCriteriaBuilder();

                // Using FROM and JOIN
                CriteriaQuery<String> criteriaQuery = builder.createQuery(String.class);
                Root<TypPredmetuEntity> typeRoot = criteriaQuery.from(TypPredmetuEntity.class);
                Root<KategorieEntity> catRoot = criteriaQuery.from(KategorieEntity.class);
                criteriaQuery.select(typeRoot.get("nazov"));
                criteriaQuery.where(builder.equal(catRoot.get("idAdmin"), id_admin)
                        , builder.equal(typeRoot.get("idTypu"), catRoot.get("idTypu")))
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
    public List<KategorieEntity> getProductCategories(int id_admin, String categoryName) {
        Session session = HibernateUtil.getSession();
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
                        builder.equal(catRoot.get("idAdmin"), id_admin),
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
    public List<PredmetPredajaEntity> getProduct(int id_admin, String categoryName) {
        Session session = HibernateUtil.getSession();
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
                        builder.equal(catRoot.get("idAdmin"), id_admin),
                        builder.equal(predmetRoot.get("idKategorie"), catRoot.get("idKategorie")),
                        builder.equal(predmetRoot.get("idAdmin"), catRoot.get("idAdmin")),
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
                if (session != null) {
                    session.close();
                }
            }

        } else {
            System.out.println("DB server down.....");
        }
        return list;
    }
}
