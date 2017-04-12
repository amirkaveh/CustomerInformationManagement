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

/**
 * Created by $Hamid on 3/13/2017.
 */
@WebServlet("/deleteLegalPerson")
public class LegalPersonDelete extends HttpServlet {

//    private ServletContext context;

    @Override
    public void init() throws ServletException {
//        context = getServletContext();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("cancel")!=null)
            request.getRequestDispatcher("/").forward(request,response);
        if(request.getParameter("delete")!=null)
        {
            onDelete(request,response);
        }


    }

    private void onDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        request.setAttribute("pageTitle","Delete Legal Customer");
        request.getRequestDispatcher("header.jsp").include(request,response);
        out.println("<body>");
        request.getRequestDispatcher("nav.html").include(request,response);

        Integer customerID = RequestParser.getInteger(request,"customerID");//may produce exception. it's ok

        LegalPersonCustomer legalPerson = new LegalPersonCustomer();
        legalPerson.setCustomerID(customerID);
        if(LegalPersonDAO.delete(legalPerson))
            request.getRequestDispatcher("customer-deleted.html").include(request,response);
        else request.getRequestDispatcher("error.html").include(request,response);
        request.getRequestDispatcher("footer.html").include(request,response);
        out.println("</body>");
        out.close();

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        request.setAttribute("pageTitle","Delete Legal Customer");
        request.getRequestDispatcher("header.jsp").include(request,response);
        out.println("<body>");
        request.getRequestDispatcher("nav.html").include(request,response);

        Integer customerID = RequestParser.getInteger(request,"customerID");//may produce exception. it's ok.

        LegalPersonCustomer searchPerson = new LegalPersonCustomer();
        searchPerson.setCustomerID(customerID);
        LegalPersonCustomer legalPerson = LegalPersonDAO.search(searchPerson).get(0);
        request.setAttribute("legalPerson",legalPerson);

        request.getRequestDispatcher("legal-delete.jsp").include(request,response);
        request.getRequestDispatcher("footer.html").include(request,response);
        out.println("</body>");
        out.close();
    }

}

