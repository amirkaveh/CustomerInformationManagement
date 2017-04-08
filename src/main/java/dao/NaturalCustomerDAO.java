package dao;

import model.NaturalPersonCustomer;

import javax.xml.crypto.Data;
import java.sql.*;

/**
 * Created by $Hamid on 4/8/2017.
 */
public class NaturalCustomerDAO {
    public static boolean insert(NaturalPersonCustomer naturalCustomer) {
        Connection connection = Database.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("insert into natural_persons(customer_id,person_name,person_family,father_name,birth_date,national_id) VALUES (?,?,?,?,?,?)");
            statement.setInt(1, naturalCustomer.getCustomerID());
            statement.setString(2, naturalCustomer.getName());
            statement.setString(3, naturalCustomer.getFamily());
            statement.setString(4, naturalCustomer.getFatherName());
            //Warning: this date conversion may be incorrect. It works fine for now.
            statement.setDate(5, new java.sql.Date(naturalCustomer.getBirthDate().getTime()));
            statement.setLong(6, naturalCustomer.getNationalID());
            statement.executeUpdate();

            connection.close();
            return true;

        } catch (SQLException e) {
            //e.printStackTrace();
            return false;
        }
    }

    public static ResultSet search(NaturalPersonCustomer naturalCustomer) {
        Connection connection = Database.getConnection();

        String selectQuery = "SELECT * FROM natural_persons";
        Boolean first = true;
        if (naturalCustomer.getName() != null) {
            selectQuery += (first) ? " WHERE " : " AND " + "person_name = " + naturalCustomer.getName();
            first = false;
        }
        if (naturalCustomer.getFamily() != null) {
            selectQuery += (first) ? " WHERE " : " AND " + "person_family = " + naturalCustomer.getFamily();
            first = false;
        }
        if (naturalCustomer.getNationalID() != null) {
            selectQuery += (first) ? " WHERE " : " AND " + "national_id = " + naturalCustomer.getNationalID();
            first = false;
        }
        if (naturalCustomer.getName() != null) {
            selectQuery += (first) ? " WHERE " : " AND " + "customer_id = " + naturalCustomer.getCustomerID();
//            first = false;
        }

        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(selectQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
