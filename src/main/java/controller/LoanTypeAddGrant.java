package controller;


import dao.Database;
import model.GrantCondition;
import model.LoanType;
import org.apache.regexp.RE;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import javax.servlet.ServletContext;
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
public class LoanTypeAddGrant extends HttpServlet {

    private ServletContext context;

    @Override
    public void init() throws ServletException {
        context = getServletContext();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        context.log("loan type: do get");
        PrintWriter out = response.getWriter();
        request.setAttribute("pageTitle", "Define Grant Conditions");
        request.getRequestDispatcher("header.jsp").include(request, response);
        out.println("<body>");
        request.getRequestDispatcher("nav.html").include(request, response);

        String loanTypeName = RequestParser.getString(request,"name");
        Double interestRate = RequestParser.getDouble(request,"interestRate");
        request.setAttribute("loanTypeName",loanTypeName);
        request.setAttribute("interestRate",interestRate);
        request.getRequestDispatcher("loan-type-add-grants.jsp").include(request, response);

        request.getRequestDispatcher("footer.html").include(request, response);
        out.println("</body>");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        context.log("loan type: do post");
        try {

            PrintWriter out = response.getWriter();
            request.setAttribute("pageTitle", "Define Grant Conditions");
            request.getRequestDispatcher("header.jsp").include(request, response);
            out.println("<body>");
            request.getRequestDispatcher("nav.html").include(request, response);

            String loanTypeName = RequestParser.getString(request, "loanTypeName");
            Double interestRate = RequestParser.getDouble(request, "interestRate");
            List<String> grantConditionNames = RequestParser.getStringList(request, "grantConditionNames[]");
            List<Integer> minContractDurations = RequestParser.getIntegerList(request, "minContractDurations[]");
            List<Integer> maxContractDurations = RequestParser.getIntegerList(request, "maxContractDurations[]");
            List<BigDecimal> minContractAmount = RequestParser.getBigDecimalList(request, "minContractAmounts[]");
            List<BigDecimal> maxContractAmount = RequestParser.getBigDecimalList(request, "maxContractAmounts[]");

            Set<GrantCondition> grantConditionSet = new HashSet<>();
            for (int i = 0; i < grantConditionNames.size(); i++) {
                GrantCondition grantCondition = new GrantCondition(grantConditionNames.get(i), minContractDurations.get(i), maxContractDurations.get(i), minContractAmount.get(i), maxContractAmount.get(i));
                grantConditionSet.add(grantCondition);
            }
            LoanType loanType = new LoanType(loanTypeName, interestRate, grantConditionSet);


            Session session = Database.openSession();
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
//            List grantConditions = session.createQuery("FROM GrantCondition").list();
//            for (Object grantConditionObject : grantConditions) {
//                GrantCondition grantCondition = (GrantCondition) grantConditionObject;
//                out.println("<h1>" + grantCondition.getName() + "</h1>");
//            }
                session.save(loanType);
                transaction.commit();
                request.getRequestDispatcher("loan-type-added.jsp").include(request,response);
            } catch (HibernateException e) {
                if (transaction != null) transaction.rollback();
                context.log("error occurred", e);
                request.setAttribute("errorTitle", "Database Error.");
                request.setAttribute("info", e.toString());
                request.getRequestDispatcher("alert-error.jsp").include(request,response);
            } finally {
                session.close();
            }


            request.getRequestDispatcher("footer.html").include(request, response);
            out.println("</body>");
            out.close();
        }catch (Throwable t){
            context.log("error occurred", t);
            throw t;
        }


    }





}
