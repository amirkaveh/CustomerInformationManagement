package crud;

import exception.DatabaseQueryException;
import model.CustomerModel;

import java.sql.*;

/**
 * Created by $Hamid on 3/29/2017.
 */
public class CustomerCRUD {
    public static void insert(CustomerModel customer) throws DatabaseQueryException {
        Connection connection= DatabaseUtils.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("insert into customers(customer_type) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, customer.getType().toString());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            customer.setCustomerID(generatedKeys.getInt(1));

            connection.close();
        } catch (SQLException e) {
            throw new DatabaseQueryException(e);
        }
    }
    public static Boolean delete(CustomerModel customerModel){
        Connection connection= DatabaseUtils.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("delete from customers where customer_id=?");
            statement.setInt(1, customerModel.getCustomerID());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
}
