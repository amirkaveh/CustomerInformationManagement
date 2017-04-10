package controller;

import dao.LegalPersonDAO;
import model.LegalPersonCustomer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by $Hamid on 3/13/2017.
 */
@WebServlet("/searchLegalCustomer")
public class LegalPersonSearch extends HttpServlet {

//    private ServletContext context;

    @Override
    public void init() throws ServletException {
//        context = getServletContext();
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        context.log("post method");

//        TODO: may make code more modular and maintainable!

        PrintWriter out = response.getWriter();
        request.setAttribute("pageTitle", "Search Legal Customer");
        request.getRequestDispatcher("header.jsp").include(request, response);
        out.println("<body>");
        request.getRequestDispatcher("nav.html").include(request, response);
        request.getRequestDispatcher("legal-search-form.html").include(request, response);

        LegalPersonCustomer legalPerson = new LegalPersonCustomer();
        getParameters(request, legalPerson);
        List<LegalPersonCustomer> legalPersons = LegalPersonDAO.search(legalPerson);
        request.setAttribute("legalPersons", legalPersons);
        request.getRequestDispatcher("legal-search-result-table.jsp").include(request,response);


        request.getRequestDispatcher("footer.html").include(request, response);
        out.println("</body>");
        out.close();


    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        //context.log("get method");
        PrintWriter out = response.getWriter();
        request.setAttribute("pageTitle", "Search Legal Customer");
        request.getRequestDispatcher("header.jsp").include(request, response);
        out.println("<body>");
        request.getRequestDispatcher("nav.html").include(request, response);
        request.getRequestDispatcher("legal-search-form.html").include(request, response);
        request.getRequestDispatcher("footer.html").include(request, response);
        out.println("</body>");
        out.close();
    }



    private void getParameters(HttpServletRequest request, LegalPersonCustomer legalPerson) {
//        context.log("getParameters");
        String name = request.getParameter("name");
        String economicalIDString = request.getParameter("economicalID");
        Long economicalID = null;
        try {
            economicalID = Long.parseLong(economicalIDString);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        String customerIDString = request.getParameter("customerID");
        Integer customerID = null;
        try {
            customerID = Integer.parseInt(customerIDString);
        } catch (Exception e) {
            //e.printStackTrace();
        }

        if (name != null && !name.equals(""))
            legalPerson.setName(name);
        if (economicalID != null)
            legalPerson.setEconomicalID(economicalID);
        if (customerID != null)
            legalPerson.setCustomerID(customerID);
    }
}

