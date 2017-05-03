package controller;


import exception.DatabaseQueryException;
import logic.LoanTypeLogic;
import model.GrantConditionModel;
import utils.RequestParserUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by $Hamid on 4/22/2017.
 */
@WebServlet("/addGrantsToLoanType")
public class LoanTypeAddGrantServlet extends HttpServlet {

//    private ServletContext context;

    @Override
    public void init() throws ServletException {
//        context = getServletContext();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        context.log("loan type: do get");
        PrintWriter out = response.getWriter();
        request.setAttribute("pageTitle", "Define Grant Conditions");
        request.getRequestDispatcher("header.jsp").include(request, response);
        out.println("<body>");
        request.getRequestDispatcher("nav.html").include(request, response);

        String loanTypeName = RequestParserUtils.getString(request, "name");
        Double interestRate = RequestParserUtils.getDouble(request, "interestRate");
        request.setAttribute("loanTypeName", loanTypeName);
        request.setAttribute("interestRate", interestRate);
        request.getRequestDispatcher("loan-type-add-grants.jsp").include(request, response);

        request.getRequestDispatcher("footer.html").include(request, response);
        out.println("</body>");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        context.log("loan type: do post");

        PrintWriter out = response.getWriter();
        request.setAttribute("pageTitle", "Define Grant Conditions");
        request.getRequestDispatcher("header.jsp").include(request, response);
        out.println("<body>");
        request.getRequestDispatcher("nav.html").include(request, response);

        String loanTypeName = RequestParserUtils.getString(request, "loanTypeName");
        Double interestRate = RequestParserUtils.getDouble(request, "interestRate");
        List<String> grantConditionNames = RequestParserUtils.getStringList(request, "grantConditionNames[]");
        List<Integer> minContractDurations = RequestParserUtils.getIntegerList(request, "minContractDurations[]");
        List<Integer> maxContractDurations = RequestParserUtils.getIntegerList(request, "maxContractDurations[]");
        List<BigDecimal> minContractAmount = RequestParserUtils.getBigDecimalList(request, "minContractAmounts[]");
        List<BigDecimal> maxContractAmount = RequestParserUtils.getBigDecimalList(request, "maxContractAmounts[]");

        Set<GrantConditionModel> grantConditionSet = makeGrantConditionSet(grantConditionNames, minContractDurations, maxContractDurations, minContractAmount, maxContractAmount);

        try {
            LoanTypeLogic.saveLoanTypeAndGrants(loanTypeName, interestRate, grantConditionSet);
            request.getRequestDispatcher("loan-type-added.jsp").include(request, response);
        } catch (DatabaseQueryException e) {
            request.setAttribute("errorTitle", "Database Error.");
            request.setAttribute("info", e.toString());
            request.getRequestDispatcher("alert-error.jsp").include(request, response);
        }

        request.getRequestDispatcher("footer.html").include(request, response);
        out.println("</body>");
        out.close();
    }

    private Set<GrantConditionModel> makeGrantConditionSet(List<String> grantConditionNames, List<Integer> minContractDurations, List<Integer> maxContractDurations, List<BigDecimal> minContractAmount, List<BigDecimal> maxContractAmount) {
        Set<GrantConditionModel> grantConditionModelSet = new HashSet<>();
        for (int i = 0; i < grantConditionNames.size(); i++) {
            GrantConditionModel grantConditionModel = new GrantConditionModel(grantConditionNames.get(i), minContractDurations.get(i), maxContractDurations.get(i), minContractAmount.get(i), maxContractAmount.get(i));
            grantConditionModelSet.add(grantConditionModel);
        }
        return grantConditionModelSet;
    }


}
