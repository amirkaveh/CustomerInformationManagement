package crud;

import exception.DatabaseQueryException;
import model.LoanTypeModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.DatabaseUtils;

import java.util.List;

/**
 * Created by $Hamid on 4/30/2017.
 */
public class LoanTypeCRUD {
    public static void insert(LoanTypeModel loanType) throws DatabaseQueryException {
        Session session = DatabaseUtils.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(loanType);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            throw new DatabaseQueryException(e);
        } finally {
            DatabaseUtils.closeSession(session);
        }
    }

    public static List<String> getLoanTypeNameList() throws DatabaseQueryException {
        Session session = DatabaseUtils.openSession();
        try {
            String queryString = "SELECT L.name FROM LoanTypeModel L";
            Query query = session.createQuery(queryString);
            return query.list();
        } catch (HibernateException e) {
            throw new DatabaseQueryException(e);
        } finally {
            DatabaseUtils.closeSession(session);
        }
    }

    public static List<LoanTypeModel> getLoanTypeList() throws DatabaseQueryException {
        Session session = DatabaseUtils.openSession();
        try {
            String queryString = "FROM LoanTypeModel";
            Query query = session.createQuery(queryString);
            return query.list();
        } catch (Exception e) {
            throw new DatabaseQueryException(e);
        } finally {
            DatabaseUtils.closeSession(session);
        }
    }

    public static LoanTypeModel getLoanType(Integer id) throws DatabaseQueryException {
        Session session = DatabaseUtils.openSession();
        try {
            return session.get(LoanTypeModel.class, id);
        } catch (Exception e) {
            throw new DatabaseQueryException(e);
        } finally {
            DatabaseUtils.closeSession(session);
        }
    }

}
