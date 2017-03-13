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
public class MyServlet extends HttpServlet {

    private String message;

    @Override
    public void init() throws ServletException {
        //super.init();
        message = "Hello World by Hamid";
    }

    @Override
    public void destroy() {
        //super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //super.doGet(req, resp);
        response.setContentType("text/html");

        PrintWriter printWriter = response.getWriter();
        printWriter.println("<h1>"+message+"</h1>");

    }
}

