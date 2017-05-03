package crud;

import exception.DatabaseQueryException;
import model.LegalPersonCustomerModel;
import org.apache.bcel.generic.DUP;
import utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by $Hamid on 4/8/2017.
 */
public class LegalPersonCRUD {
    public static void insert(LegalPersonCustomerModel legalPerson) throws DatabaseQueryException {
        Connection connection = DatabaseUtils.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("insert into legal_persons(customer_id,company_name,registration_date,economical_id) VALUES (?,?,?,?)");
            statement.setInt(1, legalPerson.getCustomerID());
            statement.setString(2, legalPerson.getName());
            //Warning: this date conversion may be incorrect. It works fine for now.
            statement.setDate(3, new java.sql.Date(legalPerson.getRegistrationDate().getTime()));
            statement.setLong(4, legalPerson.getEconomicalID());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseQueryException(e);
        } finally {
            DatabaseUtils.closeConnection(connection);
        }
    }

    public static List<LegalPersonCustomerModel> search(LegalPersonCustomerModel legalPerson) throws DatabaseQueryException {
        Connection connection = DatabaseUtils.getConnection();

        String selectQuery = "SELECT * FROM legal_persons";
        Boolean first = true;
        if (legalPerson.getName() != null) {
            selectQuery += ((first) ? " WHERE " : " AND ") + "company_name = '" + legalPerson.getName() + "'";
            first = false;
        }
        if (legalPerson.getEconomicalID() != null) {
            selectQuery += ((first) ? " WHERE " : " AND ") + "economical_id = '" + legalPerson.getEconomicalID() + "'";
            first = false;
        }
        if (legalPerson.getCustomerID() != null) {
            selectQuery += ((first) ? " WHERE " : " AND ") + "customer_id = '" + legalPerson.getCustomerID() + "'";
//            first = false;
        }

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);
            return makeLegalPersonList(resultSet);
        } catch (SQLException e) {
            throw new DatabaseQueryException(e);
        } finally {
            DatabaseUtils.closeConnection(connection);
        }

    }

    private static List<LegalPersonCustomerModel> makeLegalPersonList(ResultSet resultSet) throws SQLException {
        List<LegalPersonCustomerModel> legalPersons = new ArrayList<>();
        while (resultSet.next()) {
            Integer customerID = resultSet.getInt("customer_id");
            String name = resultSet.getString("company_name");
            java.util.Date registrationDate = resultSet.getDate("registration_date");
            Long economicalId = resultSet.getLong("economical_id");
            LegalPersonCustomerModel legalPerson = new LegalPersonCustomerModel(customerID, name, registrationDate, economicalId);
            legalPersons.add(legalPerson);
        }

        return legalPersons;
    }

    public static void delete(Integer customerID) throws DatabaseQueryException {
        Connection connection = DatabaseUtils.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM legal_persons WHERE customer_id=?");
            statement.setInt(1, customerID);
            Integer affectedRows = statement.executeUpdate();

            statement = connection.prepareStatement("DELETE FROM customers WHERE customer_id=?");
            statement.setInt(1, customerID);
            affectedRows += statement.executeUpdate();

            if (affectedRows < 2) {
                throw new DatabaseQueryException("customerID is not present in either or both tables. Sum of affected rows is: " + affectedRows);
            }

        } catch (SQLException e) {
            throw new DatabaseQueryException(e);
        } finally {
            DatabaseUtils.closeConnection(connection);
        }
    }

    public static void update(LegalPersonCustomerModel legalPerson) throws DatabaseQueryException {
        Connection connection = DatabaseUtils.getConnection();
        String queryString = "";
        Boolean first = true;
        if (legalPerson.getName() != null) {
            queryString += ((first) ? " " : ", ") + "company_name = '" + legalPerson.getName() + "'";
            first = false;
        }
        if (legalPerson.getRegistrationDate() != null) {
            queryString += ((first) ? ", " : ", ") + "registration_date = '" + new java.sql.Date(legalPerson.getRegistrationDate().getTime()) + "'";
            first = false;
        }
        if (legalPerson.getEconomicalID() != null) {
            queryString += ((first) ? " " : ", ") + "economical_id = '" + legalPerson.getEconomicalID() + "'";
//            first = false;
        }

        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE legal_persons SET" + queryString + " WHERE customer_id = ?");
            statement.setInt(1, legalPerson.getCustomerID());
            Integer affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new DatabaseQueryException("No row affected!");
            }

        } catch (SQLException e) {
            throw new DatabaseQueryException(e);
        } finally {
            DatabaseUtils.closeConnection(connection);
        }
    }
}
