package crud;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by $Hamid on 3/29/2017.
 */
public class DatabaseUtils {

    private static SessionFactory SESSION_FACTORY;

    public static Connection getConnection() {
        Connection connection;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cim", "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
        return connection;
    }

    public static Session openSession() {
        if (SESSION_FACTORY == null) {
            try {
                SESSION_FACTORY = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            } catch (Throwable e) {
                throw new ExceptionInInitializerError(e);
            }
        }
        return SESSION_FACTORY.openSession();
    }

}
