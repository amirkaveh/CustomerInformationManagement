package crud;

import exception.DatabaseQueryException;
import model.NaturalPersonCustomerModel;
import utils.DatabaseUtils;

import java.sql.*;
import java.util.*;

/**
 * Created by $Hamid on 4/8/2017.
 */
public class NaturalPersonCRUD {
    public static void insert(NaturalPersonCustomerModel naturalPerson) throws DatabaseQueryException {
        Connection connection = DatabaseUtils.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("insert into natural_persons(customer_id,person_name,person_family,father_name,birth_date,national_id) VALUES (?,?,?,?,?,?)");
            statement.setInt(1, naturalPerson.getCustomerID());
            statement.setString(2, naturalPerson.getName());
            statement.setString(3, naturalPerson.getFamily());
            statement.setString(4, naturalPerson.getFatherName());
            //Warning: this date conversion may be incorrect. It works fine for now.
            statement.setDate(5, new java.sql.Date(naturalPerson.getBirthDate().getTime()));
            statement.setLong(6, naturalPerson.getNationalID());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseQueryException(e);
        } finally {
            DatabaseUtils.closeConnection(connection);
        }
    }

    public static void delete(Integer customerID) throws DatabaseQueryException {
        Connection connection = DatabaseUtils.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM natural_persons WHERE customer_id=?");
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

    public static void update(NaturalPersonCustomerModel naturalPerson) throws DatabaseQueryException {
        Connection connection = DatabaseUtils.getConnection();
        String queryString = "";
        Boolean first = true;
        if (naturalPerson.getName() != null) {
            queryString += ((first) ? " " : ", ") + "person_name = '" + naturalPerson.getName() + "'";
            first = false;
        }
        if (naturalPerson.getFamily() != null) {
            queryString += ((first) ? " " : ", ") + "person_family = '" + naturalPerson.getFamily() + "'";
            first = false;
        }
        if (naturalPerson.getFatherName() != null) {
            queryString += ((first) ? " " : ", ") + "father_name = '" + naturalPerson.getFatherName() + "'";
            first = false;
        }
        if (naturalPerson.getBirthDate() != null) {
            queryString += ((first) ? ", " : ", ") + "birth_date = '" + new java.sql.Date(naturalPerson.getBirthDate().getTime()) + "'";
            first = false;
        }
        if (naturalPerson.getNationalID() != null) {
            queryString += ((first) ? " " : ", ") + "national_id = '" + naturalPerson.getNationalID() + "'";
//            first = false;
        }

        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE natural_persons SET" + queryString + " WHERE customer_id = ?");
            statement.setInt(1, naturalPerson.getCustomerID());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseQueryException(e);
        } finally {
            DatabaseUtils.closeConnection(connection);
        }
    }

    public static List<NaturalPersonCustomerModel> search(NaturalPersonCustomerModel naturalPerson) throws DatabaseQueryException {
        Connection connection = DatabaseUtils.getConnection();

        String selectQuery = "SELECT * FROM natural_persons";
        Boolean first = true;
        if (naturalPerson.getName() != null) {
            selectQuery += ((first) ? " WHERE " : " AND ") + "person_name = '" + naturalPerson.getName() + "'";
            first = false;
        }
        if (naturalPerson.getFamily() != null) {
            selectQuery += ((first) ? " WHERE " : " AND ") + "person_family = '" + naturalPerson.getFamily() + "'";
            first = false;
        }
        if (naturalPerson.getNationalID() != null) {
            selectQuery += ((first) ? " WHERE " : " AND ") + "national_id = '" + naturalPerson.getNationalID() + "'";
            first = false;
        }
        if (naturalPerson.getCustomerID() != null) {
            selectQuery += ((first) ? " WHERE " : " AND ") + "customer_id = '" + naturalPerson.getCustomerID() + "'";
//            first = false;
        }

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);
            return makeNaturalPersonList(resultSet);
        } catch (SQLException e) {
            throw new DatabaseQueryException(e);
        } finally {
            DatabaseUtils.closeConnection(connection);
        }

    }

    private static List<NaturalPersonCustomerModel> makeNaturalPersonList(ResultSet resultSet) throws SQLException {
        List<NaturalPersonCustomerModel> naturalPersons = new ArrayList<>();

        while (resultSet.next()) {
            Integer customerID = resultSet.getInt("customer_id");
            String name = resultSet.getString("person_name");
            String family = resultSet.getString("person_family");
            String father = resultSet.getString("father_name");
            java.util.Date birthDate = resultSet.getDate("birth_date");
            Long nationalID = resultSet.getLong("national_id");
            NaturalPersonCustomerModel naturalPerson = new NaturalPersonCustomerModel(customerID, name, family, father, birthDate, nationalID);
            naturalPersons.add(naturalPerson);
        }

        return naturalPersons;
    }
}
