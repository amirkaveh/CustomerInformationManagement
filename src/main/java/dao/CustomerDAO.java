package dao;

import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by $Hamid on 3/29/2017.
 */
public class CustomerDAO {
    public static Boolean insert(Customer customer){
        Connection connection=Database.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("insert into customers(customer_type) VALUES (?)");
            statement.setString(1, customer.getType().toString());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            customer.setCustomerID(generatedKeys.getInt(1));

            connection.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static Boolean delete(Customer customer){
        Connection connection=Database.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("delete from customers where customer_id=?");
            statement.setInt(1,customer.getCustomerID());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
}
