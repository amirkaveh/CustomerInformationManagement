package dao;

import model.Customer;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by $Hamid on 3/29/2017.
 */
public class CustomerDAO {
    public static void insert(Customer customer){
        Connection connection=Database.getConnection();
        try {
//            TODO: needs more work here for getting and returning customer id
            PreparedStatement statement = connection.prepareStatement("insert into customers(customer_type) VALUES (?)");
            statement.setString(1, customer.getType().toString());
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
