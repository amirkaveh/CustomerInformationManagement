package controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by $Hamid on 4/22/2017.
 */
@WebServlet("/addGrantsToLoanType")
public class LoanTypeAddGrant extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        request.setAttribute("pageTitle", "Define Grant Conditions");
        request.getRequestDispatcher("header.jsp").include(request, response);
        out.println("<body>");
        request.getRequestDispatcher("nav.html").include(request, response);

        String loanTypeName = RequestParser.getString(request,"name");
        Double interestRate = RequestParser.getDouble(request,"interestRate");
        request.setAttribute("name",loanTypeName);
        request.setAttribute("interestRate",interestRate);
        request.getRequestDispatcher("loan-type-add-grants.jsp").include(request, response);

        request.getRequestDispatcher("footer.html").include(request, response);
        out.println("</body>");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }





}
