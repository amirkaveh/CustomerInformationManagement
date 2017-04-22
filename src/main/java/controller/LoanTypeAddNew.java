package controller;

import dao.Database;
import model.GrantCondition;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by $Hamid on 4/18/2017.
 */
@WebServlet("/addNewLoanType")
public class LoanTypeAddNew extends HttpServlet {

//    private ServletContext context;


    @Override
    public void init() throws ServletException {
//        context = getServletContext();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        context.log("LoanType: doPost");
        PrintWriter out = response.getWriter();
        request.setAttribute("pageTitle", "Add Loan Type");
        request.getRequestDispatcher("header.jsp").include(request, response);
        out.println("<body>");
        request.getRequestDispatcher("nav.html").include(request, response);

        Session session = Database.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            List grantConditions = session.createQuery("FROM GrantCondition").list();
            for (Object grantConditionObject : grantConditions) {
                GrantCondition grantCondition = (GrantCondition) grantConditionObject;
                out.println("<h1>" + grantCondition.getName() + "</h1>");
            }
            transaction.commit();
        }catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
        }finally {
            session.close();
        }

        request.getRequestDispatcher("footer.html").include(request, response);
        out.println("</body>");
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        request.setAttribute("pageTitle", "Add Loan Type");
        request.getRequestDispatcher("header.jsp").include(request, response);
        out.println("<body>");
        request.getRequestDispatcher("nav.html").include(request, response);
        request.getRequestDispatcher("loan-type-add-form.html").include(request, response);
        request.getRequestDispatcher("footer.html").include(request, response);
        out.println("</body>");
        out.close();
    }

}
