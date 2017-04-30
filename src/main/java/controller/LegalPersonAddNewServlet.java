package controller;

import exception.DatabaseQueryException;
import exception.InputDataException;
import logic.LegalPersonLogic;
import model.LegalPersonCustomerModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Created by $Hamid on 3/13/2017.
 */
@WebServlet("/addNewLegalPerson")
public class LegalPersonAddNewServlet extends HttpServlet {

//    private ServletContext context;

    @Override
    public void init() throws ServletException {
//        context = getServletContext();
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        context.log("post method");

        PrintWriter out = response.getWriter();
        request.setAttribute("pageTitle", "Add Legal Customer");
        request.getRequestDispatcher("header.jsp").include(request, response);
        out.println("<body>");
        request.getRequestDispatcher("nav.html").include(request, response);
        request.getRequestDispatcher("legal-add-form.html").include(request, response);

        LegalPersonCustomerModel legalPerson = RequestParserUtils.makeLegalPersonFromRequestParameters(request);
        try {
            LegalPersonLogic.addPerson(legalPerson);
            request.setAttribute("customerID", legalPerson.getCustomerID());
            request.getRequestDispatcher("customer-added.jsp").include(request, response);
        } catch (InputDataException e) {
            request.setAttribute("errorTitle", "Input Error:");
            request.setAttribute("info", "There is some error in input data. Enter all required data correctly.");
            request.getRequestDispatcher("alert-error.jsp").include(request, response);
        } catch (DatabaseQueryException e) {
            if (e.getCause() != null && e.getCause().getClass().isInstance(new SQLIntegrityConstraintViolationException())) {
                request.setAttribute("errorTitle", "Economical ID Duplication");
            } else {
                request.setAttribute("errorTitle", "Database Error");
            }
            request.setAttribute("info", e.toString());
            request.getRequestDispatcher("alert-error.jsp").include(request, response);
        }

        request.getRequestDispatcher("footer.html").include(request, response);
        out.println("</body>");
        out.close();


    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //context.log("get method");
        PrintWriter out = response.getWriter();
        request.setAttribute("pageTitle", "Add Legal Customer");
        request.getRequestDispatcher("header.jsp").include(request, response);
        out.println("<body>");
        request.getRequestDispatcher("nav.html").include(request, response);
        request.getRequestDispatcher("legal-add-form.html").include(request, response);
        request.getRequestDispatcher("footer.html").include(request, response);
        out.println("</body>");
        out.close();
    }


}

