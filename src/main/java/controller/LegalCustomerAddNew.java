package controller;

import dao.CustomerDAO;
import dao.LegalCustomerDAO;
import model.Customer;
import model.LegalPersonCustomer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by $Hamid on 3/13/2017.
 */
@WebServlet("/addNewLegalCustomer")
public class LegalCustomerAddNew extends HttpServlet {

    private ServletContext context;

    @Override
    public void init() throws ServletException {
//        context = getServletContext();
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        context.log("post method");

//        TODO: may make code more modular and maintainable!

        PrintWriter out = response.getWriter();
        request.setAttribute("pageTitle", "Add Legal Customer");
        request.getRequestDispatcher("header.jsp").include(request, response);
        out.println("<body>");
        request.getRequestDispatcher("nav.html").include(request, response);
        request.getRequestDispatcher("legal-add-form.html").include(request, response);

        LegalPersonCustomer legalPerson = new LegalPersonCustomer();

        if (!getParametersAndValidate(request, legalPerson)) {
            //context.log("false occurred");
            request.setAttribute("errorTitle", "Input Error:");
            request.setAttribute("info", "There is some error in input data. Enter all required data correctly.");
            request.getRequestDispatcher("alert-error.jsp").include(request, response);
        } else {
            Customer customer = new Customer();
            customer.setType(Customer.CustomerType.legalPerson);
            if (!CustomerDAO.insert(customer)) {
                request.setAttribute("errorTitle", "Database Error:");
                request.setAttribute("info", "there is some database error, can't insert new customer to \"customers\" table.");
                request.getRequestDispatcher("alert-error.jsp").include(request, response);
            } else {
                legalPerson.setCustomerID(customer.getCustomerID());
                if (LegalCustomerDAO.insert(legalPerson)) {
                    request.setAttribute("customerID", customer.getCustomerID());
                    request.getRequestDispatcher("alert-success.jsp").include(request, response);
                } else {
                    CustomerDAO.delete(customer);
                    request.setAttribute("errorTitle", "Database Error:");
                    request.setAttribute("info", "there is an economical ID duplicate or other database error.");
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
        request.setAttribute("pageTitle", "Add Natural Customer");
        request.getRequestDispatcher("header.jsp").include(request, response);
        out.println("<body>");
        request.getRequestDispatcher("nav.html").include(request, response);
        request.getRequestDispatcher("legal-add-form.html").include(request, response);
        request.getRequestDispatcher("footer.html").include(request, response);
        out.println("</body>");
        out.close();
    }

    private Boolean getParametersAndValidate(HttpServletRequest request, LegalPersonCustomer legalPerson) {
        //context.log("validation");
        String name = request.getParameter("name");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String registrationDateString = request.getParameter("registration");
        Date registraionDate = null;
        try {
            registraionDate = dateFormat.parse(registrationDateString);
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
        String economicalIDString = request.getParameter("economicalID");
        Long economicalID = null;
        try {
            economicalID = Long.parseLong(economicalIDString);
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
        if (name == null || name.equals("") || registraionDate == null || economicalID == null)
            return false;

        legalPerson.setName(name);
        legalPerson.setRegistrationDate(registraionDate);
        legalPerson.setEconomicalID(economicalID);

        return true;
    }
}

