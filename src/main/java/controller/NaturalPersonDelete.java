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
@WebServlet("/deleteNaturalPerson")
public class NaturalPersonDelete extends HttpServlet {

    private ServletContext context;

    @Override
    public void init() throws ServletException {
        context = getServletContext();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        context.log("Delete: doPost");
        context.log("delete: " + request.getParameter("delete"));
        context.log("delete: " + request.getParameter("cancel"));
        context.log("id: "+ request.getParameter("customerID"));

        if(request.getParameter("cancel")!=null)
            request.getRequestDispatcher("/").forward(request,response);
        if(request.getParameter("delete")!=null)
        {
            onDelete(request,response);
        }


    }

    private void onDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        request.setAttribute("pageTitle","Delete Natural Customer");
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
        if(NaturalPersonDAO.delete(naturalPerson))
            request.getRequestDispatcher("customer-deleted.html").include(request,response);
        else request.getRequestDispatcher("error.html").include(request,response);
        request.getRequestDispatcher("footer.html").include(request,response);
        out.println("</body>");
        out.close();

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        context.log("Delete: doGet");
        PrintWriter out = response.getWriter();
        request.setAttribute("pageTitle","Delete Natural Customer");
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
        context.log("customerID: "+ customerID);
        NaturalPersonCustomer searchPerson = new NaturalPersonCustomer();
        searchPerson.setCustomerID(customerID);
        NaturalPersonCustomer naturalPerson = NaturalPersonDAO.search(searchPerson).get(0);
        request.setAttribute("naturalPerson",naturalPerson);

        request.getRequestDispatcher("natural-delete.jsp").include(request,response);
        request.getRequestDispatcher("footer.html").include(request,response);
        out.println("</body>");
        out.close();
    }

}

