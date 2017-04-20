package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by $Hamid on 4/20/2017.
 */
public class HibernateUtil {
    private static final SessionFactory SESSION_FACTORY;
    static {
        try {
            SESSION_FACTORY = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        }catch (Throwable e){
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Session openSession(){
        return SESSION_FACTORY.openSession();
    }

}
