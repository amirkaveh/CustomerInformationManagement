package dao;

import model.LegalPersonCustomer;
import model.LegalPersonCustomer;
import model.LegalPersonCustomer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by $Hamid on 4/8/2017.
 */
public class LegalPersonDAO {
    public static boolean insert(LegalPersonCustomer legalPerson) {
        Connection connection = Database.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("insert into legal_persons(customer_id,company_name,registration_date,economical_id) VALUES (?,?,?,?)");
            statement.setInt(1, legalPerson.getCustomerID());
            statement.setString(2, legalPerson.getName());
            //Warning: this date conversion may be incorrect. It works fine for now.
            statement.setDate(3, new java.sql.Date(legalPerson.getRegistrationDate().getTime()));
            statement.setLong(4, legalPerson.getEconomicalID());
            statement.executeUpdate();

            connection.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<LegalPersonCustomer> search(LegalPersonCustomer legalPerson) {
        Connection connection = Database.getConnection();

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
            e.printStackTrace();
            return null;
        }

    }

    private static List<LegalPersonCustomer> makeLegalPersonList(ResultSet resultSet) {
        List<LegalPersonCustomer> legalPersons = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Integer customerID = resultSet.getInt("customer_id");
                String name = resultSet.getString("company_name");
                java.util.Date registrationDate = resultSet.getDate("registration_date");
                Long economicalId = resultSet.getLong("economical_id");
                LegalPersonCustomer legalPerson = new LegalPersonCustomer(customerID, name, registrationDate, economicalId);
                legalPersons.add(legalPerson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return legalPersons;
    }

    public static Boolean delete(LegalPersonCustomer legalPerson) {
        Connection connection = Database.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM legal_persons WHERE customer_id=?");
            statement.setInt(1, legalPerson.getCustomerID());
            Integer affectedRows = statement.executeUpdate();
            Boolean result = true;
            if (affectedRows == 0)
                result = false;

            statement = connection.prepareStatement("DELETE FROM customers WHERE customer_id=?");
            statement.setInt(1, legalPerson.getCustomerID());
            affectedRows = statement.executeUpdate();
            if (affectedRows == 0)
                result = false;
            connection.close();
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Boolean edit(LegalPersonCustomer legalPerson) {
        Connection connection = Database.getConnection();
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
            connection.close();
            if (affectedRows == 0)
                return false;
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
