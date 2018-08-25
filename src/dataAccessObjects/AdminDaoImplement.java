package dataAccessObjects;

import entities.UcetEntity;
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
}
