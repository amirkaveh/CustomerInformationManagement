package controller;

import exception.DatabaseQueryException;
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
import java.util.List;

/**
 * Created by $Hamid on 3/13/2017.
 */
@WebServlet("/searchNaturalPerson")
public class NaturalPersonSearchServlet extends HttpServlet {

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

        NaturalPersonCustomerModel naturalPerson = RequestParserUtils.makeNaturalPersonFromRequestParameters(request);
        try {
            List<NaturalPersonCustomerModel> naturalPersons = NaturalPersonLogic.searchPerson(naturalPerson);
            request.setAttribute("naturalPersons", naturalPersons);
            request.getRequestDispatcher("natural-search-result-table.jsp").include(request, response);
        }catch (DatabaseQueryException e){
            request.setAttribute("errorTitle", "Database Error");
            request.setAttribute("info", e.toString());
            request.getRequestDispatcher("error.jsp").include(request,response);
        }

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


}

