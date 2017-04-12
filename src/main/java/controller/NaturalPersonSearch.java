package controller;

import dao.NaturalPersonDAO;
import model.NaturalPersonCustomer;

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
@WebServlet("/searchNaturalPerson")
public class NaturalPersonSearch extends HttpServlet {

//    private ServletContext context;

    @Override
    public void init() throws ServletException {
//        context = getServletContext();
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        context.log("post method");

        PrintWriter out = response.getWriter();
        request.setAttribute("pageTitle", "Search Natural Customer");
        request.getRequestDispatcher("header.jsp").include(request, response);
        out.println("<body>");
        request.getRequestDispatcher("nav.html").include(request, response);
        request.getRequestDispatcher("natural-search-form.html").include(request, response);

        NaturalPersonCustomer naturalPerson = new NaturalPersonCustomer();
        getParameters(request, naturalPerson);
        List<NaturalPersonCustomer> naturalPersons = NaturalPersonDAO.search(naturalPerson);
        request.setAttribute("naturalPersons", naturalPersons);
        request.getRequestDispatcher("natural-search-result-table.jsp").include(request,response);

        request.getRequestDispatcher("footer.html").include(request, response);
        out.println("</body>");
        out.close();


    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        context.log("get method");
        PrintWriter out = response.getWriter();
        request.setAttribute("pageTitle", "Search Natural Customer");
        request.getRequestDispatcher("header.jsp").include(request, response);
        out.println("<body>");
        request.getRequestDispatcher("nav.html").include(request, response);
        request.getRequestDispatcher("natural-search-form.html").include(request, response);
        request.getRequestDispatcher("footer.html").include(request, response);
        out.println("</body>");
        out.close();
    }



    private void getParameters(HttpServletRequest request, NaturalPersonCustomer naturalPerson) {
//        context.log("getParameters");
        String name = RequestParser.getString(request,"name");
        String family = RequestParser.getString(request,"family");
        Long nationalID;
        try {
            nationalID = RequestParser.getLong(request,"nationalID");
        } catch (Exception e) {
            nationalID = null;
        }
        Integer customerID;
        try {
            customerID = RequestParser.getInteger(request,"customerID");
        } catch (Exception e) {
            customerID = null;
        }

        if (name != null && !name.equals(""))
            naturalPerson.setName(name);
        if (family != null && !family.equals(""))
            naturalPerson.setFamily(family);
        if (nationalID != null)
            naturalPerson.setNationalID(nationalID);
        if (customerID != null)
            naturalPerson.setCustomerID(customerID);
    }
}

