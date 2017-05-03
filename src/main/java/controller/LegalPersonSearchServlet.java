package controller;

import exception.DatabaseQueryException;
import logic.LegalPersonLogic;
import model.LegalPersonCustomerModel;
import utils.RequestParserUtils;

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
@WebServlet("/searchLegalPerson")
public class LegalPersonSearchServlet extends HttpServlet {

//    private ServletContext context;

    @Override
    public void init() throws ServletException {
//        context = getServletContext();
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        context.log("post method");

        PrintWriter out = response.getWriter();
        request.setAttribute("pageTitle", "Search Legal Customer");
        request.getRequestDispatcher("header.jsp").include(request, response);
        out.println("<body>");
        request.getRequestDispatcher("nav.html").include(request, response);
        request.getRequestDispatcher("legal-search-form.html").include(request, response);

        LegalPersonCustomerModel legalPerson = RequestParserUtils.makeLegalPersonFromRequestParameters(request);
        try {
            List<LegalPersonCustomerModel> legalPersons = LegalPersonLogic.searchPerson(legalPerson);
            request.setAttribute("legalPersons", legalPersons);
            request.getRequestDispatcher("legal-search-result-table.jsp").include(request, response);
        }catch (DatabaseQueryException e){
            request.setAttribute("errorTitle", "Database Error");
            request.setAttribute("info", e.toString());
            request.getRequestDispatcher("error.jsp").include(request,response);
        }

        request.getRequestDispatcher("footer.html").include(request, response);
        out.println("</body>");
        out.close();


    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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


}

