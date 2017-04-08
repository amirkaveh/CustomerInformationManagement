package dao;

import model.LegalPersonCustomer;
import model.NaturalPersonCustomer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by $Hamid on 4/8/2017.
 */
public class LegalCustomerDAO {
    public static boolean insert(LegalPersonCustomer legalCustomer){
        Connection connection = Database.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("insert into legal_persons(customer_id,company_name,registration_date,economical_id) VALUES (?,?,?,?)");
            statement.setInt(1,legalCustomer.getCustomerID());
            statement.setString(2,legalCustomer.getName());
            //Warning: this date conversion may be incorrect. It works fine for now.
            statement.setDate(3,new java.sql.Date(legalCustomer.getRegistrationDate().getTime()));
            statement.setLong(4,legalCustomer.getEconomicalID());
            statement.executeUpdate();

            connection.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
