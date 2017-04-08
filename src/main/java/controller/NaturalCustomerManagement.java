package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by $Hamid on 3/13/2017.
 */
@WebServlet("/naturalCustomerManagement")
public class NaturalCustomerManagement extends HttpServlet {


    @Override
    public void init() throws ServletException {
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        request.setAttribute("pageTitle","Natural Customer Management");
        request.getRequestDispatcher("header.jsp").include(request,response);
        out.println("<body>");
        request.getRequestDispatcher("nav.html").include(request,response);
        request.getRequestDispatcher("natural.html").include(request,response);
        request.getRequestDispatcher("footer.html").include(request,response);
        out.println("</body>");
        out.close();
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}

