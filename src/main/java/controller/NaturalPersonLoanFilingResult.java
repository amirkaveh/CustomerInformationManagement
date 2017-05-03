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
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by $Hamid on 3/13/2017.
 */
@WebServlet("/resultFilingNaturalPersonLoan")
public class NaturalPersonLoanFilingResult extends HttpServlet {


    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        request.setAttribute("pageTitle", "Filing Natural Person Loans");
        request.getRequestDispatcher("header.jsp").include(request, response);
        out.println("<body>");
        request.getRequestDispatcher("nav.html").include(request, response);

        //TODO: saving customers' loan to related database table has yet to be implemented
        try {
            Integer customerID = RequestParserUtils.getInteger(request, "customerID");//make use of this variable to save loan for this customer.
            Integer loanTypeID = RequestParserUtils.getInteger(request, "loanTypeID");
            Integer contractDuration = RequestParserUtils.getInteger(request, "contractDuration");
            BigDecimal contractAmount = RequestParserUtils.getBigDecimal(request, "contractAmount");
            if (LoanTypeLogic.checkLoanCriteria(loanTypeID, contractDuration, contractAmount)) {
                request.setAttribute("result", "Success");
                request.setAttribute("info","The request meets grant condition criteria.");
            } else {
                request.setAttribute("result", "Fail");
                request.setAttribute("info","The request DOESN'T meet grant condition criteria.");
            }
            request.getRequestDispatcher("natural-loan-file-result.jsp").include(request, response);
        }catch (Exception e){
            request.setAttribute("errorTitle", "Exception Occurred!");
            request.setAttribute("info", e.toString());
            request.getRequestDispatcher("error.jsp").include(request, response);
        }
        request.getRequestDispatcher("footer.html").include(request, response);
        out.println("</body>");
        out.close();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

