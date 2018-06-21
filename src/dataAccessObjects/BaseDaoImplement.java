package dataAccessObjects;

import entities.UcetEntity;
import org.hibernate.Session;
import utilities.HashPasswordUtil;
import utilities.HibernateUtil;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class BaseDaoImplement implements BaseDao{
    @Override
    public UcetEntity login(String username, String password) {
        Session session = HibernateUtil.getSession();
        if (session != null) {
            try {
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<UcetEntity> query = builder.createQuery(UcetEntity.class);
                Root<UcetEntity> root = query.from(UcetEntity.class);
                query.select(root).where(builder.equal(root.get("email"), username));

                UcetEntity user = session.createQuery(query).getSingleResult();
                String hashPass =  HashPasswordUtil.hashPassword(password);
                if (HashPasswordUtil.hashPassword(password).equals(user.getHeslo())) {
                    return user;
                }
            }
            catch (NoResultException exception) {
                System.out.println("Object not found"
                        + exception.getMessage());
                return null;
            }
            catch (Exception exception) {
                System.out.println("Exception occred while reading user data: "
                        + exception.getMessage());
                return null;
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
}
