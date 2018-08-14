package utilities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import utilities.multitenancy.CurrentTenantIdentifierResolverImpl;
import utilities.multitenancy.MultiTenantConnectionProviderImpl;

import javax.persistence.criteria.CriteriaBuilder;

public class HibernateUtil {
    private static Configuration configuration;
    private static SessionFactory sessionFactory;

    static {
        configuration = new Configuration().configure("config/hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();
    }

    public static Session getSession() {
        Session session = null;
        if (sessionFactory != null) {
            session = sessionFactory.openSession();
        }
        return session;
    }

    public static Session getSessionByTenant(String tenantID) {
        Session session = null;
        if (sessionFactory != null) {
            session = sessionFactory.withOptions().tenantIdentifier(tenantID).openSession();
        }
        return session;
    }
}
