package controller;

import crud.LegalPersonCRUD;
import exception.DatabaseQueryException;
import logic.LegalPersonLogic;
import model.LegalPersonCustomerModel;

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
public class LegalPersonDeleteServlet extends HttpServlet {

//    private ServletContext context;

    @Override
    public void init() throws ServletException {
//        context = getServletContext();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("cancel") != null)
            request.getRequestDispatcher("/").forward(request, response);
        if (request.getParameter("delete") != null) {
            onDelete(request, response);
        }


    }

    private void onDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        request.setAttribute("pageTitle", "Delete Legal Customer");
        request.getRequestDispatcher("header.jsp").include(request, response);
        out.println("<body>");
        request.getRequestDispatcher("nav.html").include(request, response);

        try {
            Integer customerID = RequestParserUtils.getInteger(request, "customerID");
            LegalPersonLogic.deletePerson(customerID);
            request.getRequestDispatcher("customer-deleted.html").include(request, response);
        } catch (DatabaseQueryException e) {
            request.setAttribute("errorTitle", "Database Error");
            request.setAttribute("info", e.toString());
            request.getRequestDispatcher("error.jsp").include(request, response);
        } catch (Exception e) {
            request.setAttribute("errorTitle", "Customer ID Problem ");
            request.setAttribute("info", e.toString());
            request.getRequestDispatcher("error.jsp").include(request, response);
        }

        request.getRequestDispatcher("footer.html").include(request, response);
        out.println("</body>");
        out.close();

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        request.setAttribute("pageTitle", "Delete Legal Customer");
        request.getRequestDispatcher("header.jsp").include(request, response);
        out.println("<body>");
        request.getRequestDispatcher("nav.html").include(request, response);

        try {
            Integer customerID = RequestParserUtils.getInteger(request, "customerID");
            LegalPersonCustomerModel legalPerson = LegalPersonLogic.getPersonByCustomerID(customerID);
            request.setAttribute("legalPerson", legalPerson);
            request.getRequestDispatcher("legal-delete.jsp").include(request, response);
        } catch (DatabaseQueryException e) {
            request.setAttribute("errorTitle", "Database Error");
            request.setAttribute("info", e.toString());
            request.getRequestDispatcher("error.jsp").include(request, response);
        } catch (Exception e) {
            request.setAttribute("errorTitle", "Customer ID Problem");
            request.setAttribute("info", e.toString());
            request.getRequestDispatcher("error.jsp").include(request, response);
        }
        request.getRequestDispatcher("footer.html").include(request, response);
        out.println("</body>");
        out.close();
    }

}

