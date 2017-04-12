package controller;

import dao.CustomerDAO;
import dao.NaturalPersonDAO;
import model.Customer;
import model.NaturalPersonCustomer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by $Hamid on 3/13/2017.
 */
@WebServlet("/addNewNaturalPerson")
public class NaturalPersonAddNew extends HttpServlet {

//    private ServletContext context;

    @Override
    public void init() throws ServletException {
//        context = getServletContext();
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        context.log("post method");

        PrintWriter out = response.getWriter();
        request.setAttribute("pageTitle","Add Natural Customer");
        request.getRequestDispatcher("header.jsp").include(request, response);
        out.println("<body>");
        request.getRequestDispatcher("nav.html").include(request, response);
        request.getRequestDispatcher("natural-add-form.html").include(request, response);

        NaturalPersonCustomer naturalPerson = new NaturalPersonCustomer();

        if(!getParametersAndValidate(request,naturalPerson)){
            //context.log("false occurred");
            request.setAttribute("errorTitle","Input Error:");
            request.setAttribute("info","There is some error in input data. Enter all required data correctly.");
            request.getRequestDispatcher("alert-error.jsp").include(request,response);
        }
        else {
            Customer customer = new Customer();
            customer.setType(Customer.CustomerType.naturalPerson);
            if(!CustomerDAO.insert(customer)){
                request.setAttribute("errorTitle", "Database Error:");
                request.setAttribute("info", "there is some database error, can't insert new customer to \"customers\" table.");
                request.getRequestDispatcher("alert-error.jsp").include(request, response);
            }else {
                naturalPerson.setCustomerID(customer.getCustomerID());
                if (NaturalPersonDAO.insert(naturalPerson)) {
                    request.setAttribute("customerID", customer.getCustomerID());
                    request.getRequestDispatcher("alert-success.jsp").include(request, response);
                } else {
                    CustomerDAO.delete(customer);
                    request.setAttribute("errorTitle", "Database Error:");
                    request.setAttribute("info", "there is a national ID duplicate or other database error.");
                    request.getRequestDispatcher("alert-error.jsp").include(request, response);
                }
            }
        }
        request.getRequestDispatcher("footer.html").include(request, response);
        out.println("</body>");
        out.close();


    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        //context.log("get method");
        PrintWriter out = response.getWriter();
        request.setAttribute("pageTitle","Add Natural Customer");
        request.getRequestDispatcher("header.jsp").include(request, response);
        out.println("<body>");
        request.getRequestDispatcher("nav.html").include(request, response);
        request.getRequestDispatcher("natural-add-form.html").include(request, response);
        request.getRequestDispatcher("footer.html").include(request, response);
        out.println("</body>");
        out.close();
    }

    public static Boolean getParametersAndValidate(HttpServletRequest request, NaturalPersonCustomer naturalPerson) {
        //context.log("validation");
        String name = RequestParser.getString(request,"name");
        String family = RequestParser.getString(request,"family");
        String fatherName = RequestParser.getString(request, "father");
        Date birthDate;
        try {
            birthDate = RequestParser.getDate(request,"birth");
        } catch (Exception e) {
            return false;
        }
        Long nationalID;
        try {
            nationalID = RequestParser.getLong(request,"nationalID");
        } catch (Exception e) {
            return false;
        }
        if( name==null || name.equals("") || family==null || family.equals("") || fatherName==null || fatherName.equals(""))
            return false;

        naturalPerson.setName(name);
        naturalPerson.setFamily(family);
        naturalPerson.setFatherName(fatherName);
        naturalPerson.setBirthDate(birthDate);
        naturalPerson.setNationalID(nationalID);
        return true;
    }
}

