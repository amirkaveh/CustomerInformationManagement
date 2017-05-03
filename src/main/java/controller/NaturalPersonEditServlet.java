package controller;

import exception.DatabaseQueryException;
import exception.InputDataException;
import logic.NaturalPersonLogic;
import model.NaturalPersonCustomerModel;
import utils.RequestParserUtils;

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
@WebServlet("/editNaturalPerson")
public class NaturalPersonEditServlet extends HttpServlet {

//    private ServletContext context;

    @Override
    public void init() throws ServletException {
//        context = getServletContext();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        context.log("Edit: doPost");

        if(request.getParameter("cancel")!=null)
            response.sendRedirect("/");
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

        NaturalPersonCustomerModel naturalPerson = RequestParserUtils.makeNaturalPersonFromRequestParameters(request);
        try {
            NaturalPersonLogic.updatePerson(naturalPerson);
            request.getRequestDispatcher("customer-edited.html").include(request, response);
        } catch (InputDataException e) {
            request.setAttribute("errorTitle", "Input Error");
            request.setAttribute("info", "There is some error in input data. Enter all required data correctly.");
            request.getRequestDispatcher("error.jsp").include(request,response);
        } catch (DatabaseQueryException e) {
            if (e.getCause() != null && e.getCause().getClass().isInstance(new SQLIntegrityConstraintViolationException())) {
                request.setAttribute("errorTitle", "National ID Duplication");
            } else {
                request.setAttribute("errorTitle", "Database Error");
            }
            request.setAttribute("info", e.toString());
            request.getRequestDispatcher("error.jsp").include(request, response);
        }

        request.getRequestDispatcher("footer.html").include(request,response);
        out.println("</body>");
        out.close();

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        context.log("Edit: doGet");
        PrintWriter out = response.getWriter();
        request.setAttribute("pageTitle","Edit Natural Customer");
        request.getRequestDispatcher("header.jsp").include(request,response);
        out.println("<body>");
        request.getRequestDispatcher("nav.html").include(request,response);

        try {
            Integer customerID = RequestParserUtils.getInteger(request, "customerID");
            NaturalPersonCustomerModel naturalPerson = NaturalPersonLogic.getPersonByCustomerID(customerID);
            request.setAttribute("naturalPerson", naturalPerson);
            request.getRequestDispatcher("natural-edit.jsp").include(request, response);
        }catch (DatabaseQueryException e){
            request.setAttribute("errorTitle", "Database Error");
            request.setAttribute("info", e.toString());
            request.getRequestDispatcher("error.jsp").include(request, response);
        }catch (Exception e){
            request.setAttribute("errorTitle", "customer ID problem.");
            request.setAttribute("info", e.toString());
            request.getRequestDispatcher("error.jsp").include(request, response);
        }

        request.getRequestDispatcher("footer.html").include(request,response);
        out.println("</body>");
        out.close();
    }



}

