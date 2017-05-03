package controller;

import exception.DatabaseQueryException;
import javafx.util.Pair;
import logic.LoanTypeLogic;
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
@WebServlet("/fileNaturalPersonLoan")
public class NaturalPersonLoanFiling extends HttpServlet {


    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            Integer customerID = RequestParserUtils.getInteger(request, "customerID");
            NaturalPersonCustomerModel naturalPerson = NaturalPersonLogic.getPersonByCustomerID(customerID);
            out.println("ok:" + naturalPerson.getName() + ":" + naturalPerson.getFamily());
        } catch (DatabaseQueryException e) {
            out.println("error:Database Error: " + e);
        } catch (Exception e) {
            out.println("error:Customer ID problem, the customer not found.");
        }
        out.close();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        request.setAttribute("pageTitle", "Filing Natural Person Loans");
        request.getRequestDispatcher("header.jsp").include(request, response);
        out.println("<body>");
        request.getRequestDispatcher("nav.html").include(request, response);

        try {
            Pair<List<String>, List<Integer>> listOfNameAndID = LoanTypeLogic.getListOfNameAndID();
            request.setAttribute("loanTypeNameList", listOfNameAndID.getKey());
            request.setAttribute("loanTypeIDList", listOfNameAndID.getValue());
            request.getRequestDispatcher("natural-loan-file.jsp").include(request, response);
        } catch (Exception e) {
            request.setAttribute("errorTitle", "Exception Occurred!");
            request.setAttribute("info", e.toString());
            request.getRequestDispatcher("error.jsp").include(request, response);
        }

        request.getRequestDispatcher("footer.html").include(request, response);
        out.println("</body>");
        out.close();
    }
}

