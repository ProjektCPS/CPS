package dataAccessObjects;

import entities.RegistrovanyUzivatelEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import utilities.HibernateUtil;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class BaseDaoImplement implements BaseDao{
    @Override
    public boolean login(String username, String password) {
        Session session = HibernateUtil.getSession();
        if (session != null) {
            try {
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<RegistrovanyUzivatelEntity> query = builder.createQuery(RegistrovanyUzivatelEntity.class);
                Root<RegistrovanyUzivatelEntity> root = query.from(RegistrovanyUzivatelEntity.class);
                query.select(root).where(builder.equal(root.get("email"), username));

                RegistrovanyUzivatelEntity user = session.createQuery(query).getSingleResult();
                if (password.equals(user.getHeslo())) {
                    System.out.println("User: " + user.toString());
                    return true;
                }
            }
            catch (NoResultException exception) {
                System.out.println("Object not found"
                        + exception.getMessage());
                return false;
            }
            catch (Exception exception) {
                System.out.println("Exception occred while reading user data: "
                        + exception.getMessage());
                return false;
            }

        } else {
            System.out.println("DB server down.....");
        }
        return false;
    }

    @Override
    public String register(RegistrovanyUzivatelEntity user) {
        return null;
    }
}
