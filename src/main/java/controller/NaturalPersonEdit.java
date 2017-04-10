package controller;

import dao.NaturalPersonDAO;
import model.NaturalPersonCustomer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by $Hamid on 3/13/2017.
 */
@WebServlet("/editNaturalPerson")
public class NaturalPersonEdit extends HttpServlet {

    private ServletContext context;

    @Override
    public void init() throws ServletException {
        context = getServletContext();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        context.log("Edit: doPost");

        if(request.getParameter("cancel")!=null)
            request.getRequestDispatcher("/").forward(request,response);
        if(request.getParameter("ok")!=null)
        {
            onEdit(request,response);
        }
    }

    private void onEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        request.setAttribute("pageTitle","Edit Natural Customer");
        request.getRequestDispatcher("header.jsp").include(request,response);
        out.println("<body>");
        request.getRequestDispatcher("nav.html").include(request,response);

        String customerIDString = request.getParameter("customerID");
        Integer customerID = null;
        try {
            customerID = Integer.parseInt(customerIDString);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        NaturalPersonCustomer naturalPerson = new NaturalPersonCustomer();
        naturalPerson.setCustomerID(customerID);
        if(NaturalPersonAddNew.getParametersAndValidate(request,naturalPerson) && NaturalPersonDAO.edit(naturalPerson)) {
            request.getRequestDispatcher("customer-edited.html").include(request, response);
        }
        else request.getRequestDispatcher("error.html").include(request,response);

        request.getRequestDispatcher("footer.html").include(request,response);
        out.println("</body>");
        out.close();

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        context.log("Edit: doGet");
        PrintWriter out = response.getWriter();
        request.setAttribute("pageTitle","Edit Natural Customer");
        request.getRequestDispatcher("header.jsp").include(request,response);
        out.println("<body>");
        request.getRequestDispatcher("nav.html").include(request,response);

        String customerIDString = request.getParameter("customerID");
        Integer customerID = null;
        try {
            customerID = Integer.parseInt(customerIDString);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
//        context.log("customerID: "+ customerID);
        NaturalPersonCustomer searchPerson = new NaturalPersonCustomer();
        searchPerson.setCustomerID(customerID);
        NaturalPersonCustomer naturalPerson = NaturalPersonDAO.search(searchPerson).get(0);
        request.setAttribute("naturalPerson",naturalPerson);

        request.getRequestDispatcher("natural-edit.jsp").include(request,response);
        request.getRequestDispatcher("footer.html").include(request,response);
        out.println("</body>");
        out.close();
    }

}

