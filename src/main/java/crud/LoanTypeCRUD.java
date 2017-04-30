package crud;

import exception.DatabaseQueryException;
import model.LoanTypeModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
            session.close();
        }
    }
}
