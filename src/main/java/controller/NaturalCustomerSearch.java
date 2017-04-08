package controller;

import dao.CustomerDAO;
import dao.NaturalCustomerDAO;
import model.Customer;
import model.NaturalPersonCustomer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by $Hamid on 3/13/2017.
 */
@WebServlet("/searchNaturalCustomer")
public class NaturalCustomerSearch extends HttpServlet {

//    private ServletContext context;

    @Override
    public void init() throws ServletException {
//        context = getServletContext();
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        context.log("post method");

//        TODO: may make code more modular and maintainable!

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        request.setAttribute("pageTitle","Search Natural Customer");
        request.getRequestDispatcher("header.jsp").include(request, response);
        out.println("<body>");
        request.getRequestDispatcher("nav.html").include(request, response);
        request.getRequestDispatcher("natural-search-form.html").include(request, response);

        NaturalPersonCustomer naturalPerson = new NaturalPersonCustomer();
        getParameters(request,naturalPerson);
        ResultSet resultSet = NaturalCustomerDAO.search(naturalPerson);

//        try {
//            while(resultSet.next()) {
//
//                String name = resultSet.getString("person_name");
//                Integer customerID  = resultSet.getInt("customer_id");
////                context.log("row: "+ name+ " "+ customerID);
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


        request.getRequestDispatcher("footer.html").include(request, response);
        out.println("</body>");
        out.close();


    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        //context.log("get method");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        request.setAttribute("pageTitle","Search Natural Customer");
        request.getRequestDispatcher("header.jsp").include(request, response);
        out.println("<body>");
        request.getRequestDispatcher("nav.html").include(request, response);
        request.getRequestDispatcher("natural-search-form.html").include(request, response);
        request.getRequestDispatcher("footer.html").include(request, response);
        out.println("</body>");
        out.close();
    }

    private void getParameters(HttpServletRequest request, NaturalPersonCustomer naturalPerson) {
        //context.log("validation");
        String name = request.getParameter("name");
        String family = request.getParameter("family");
        String nationalIDString = request.getParameter("nationalID");
        Long nationalID=null;
        try {
            nationalID = Long.parseLong(nationalIDString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String customerIDString = request.getParameter("customerID");
        Integer customerID=null;
        try {
            customerID = Integer.parseInt(customerIDString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(name!=null && !name.equals(""))
            naturalPerson.setName(name);
        if(family!=null && !family.equals(""))
            naturalPerson.setFamily(family);
        if(nationalID!=null)
            naturalPerson.setNationalID(nationalID);
        if(customerID!=null)
            naturalPerson.setCustomerID(customerID);
    }
}

