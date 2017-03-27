package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by $Hamid on 3/13/2017.
 */
public class Home extends HttpServlet {


    @Override
    public void init() throws ServletException {
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("home-header-part1.html").include(request,response);
        out.println("Customer Information Management");//page title
        request.getRequestDispatcher("home-header-part2.html").include(request,response);
        out.println("<body>");
        request.getRequestDispatcher("nav.html").include(request,response);
        request.getRequestDispatcher("home.html").include(request,response);
        request.getRequestDispatcher("footer.html").include(request,response);
        out.println("</body>");
        out.close();
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}

