package controller;

import model.NaturalPersonCustomer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by $Hamid on 3/13/2017.
 */
@WebServlet("/addNewNaturalCustomer")
public class NaturalCustomerAddNew extends HttpServlet {

    private ServletContext context;

    @Override
    public void init() throws ServletException {
        context = getServletContext();
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //context.log("post method");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("header-part1.html").include(request, response);
        out.println("Add New Natural Customer");//page title
        request.getRequestDispatcher("header-part2.html").include(request, response);
        out.println("<body>");
        request.getRequestDispatcher("nav.html").include(request, response);
        request.getRequestDispatcher("natural-add-form.html").include(request, response);

        NaturalPersonCustomer naturalPerson = new NaturalPersonCustomer();

        if(requestValidate(request,naturalPerson)){
            //context.log("true occurred");
            //TODO: some DataBase work for adding new customer
            request.getRequestDispatcher("add-alert-success.html").include(request,response);
        }
        else {
            //context.log("false occurred");
            request.getRequestDispatcher("add-alert-input-error.html").include(request,response);
        }
        request.getRequestDispatcher("footer.html").include(request, response);
        out.println("</body>");
        out.close();


    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        //context.log("get method");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("header-part1.html").include(request, response);
        out.println("Add New Natural Customer");//page title
        request.getRequestDispatcher("header-part2.html").include(request, response);
        out.println("<body>");
        request.getRequestDispatcher("nav.html").include(request, response);
        request.getRequestDispatcher("natural-add-form.html").include(request, response);
        request.getRequestDispatcher("footer.html").include(request, response);
        out.println("</body>");
        out.close();
    }

    private Boolean requestValidate(HttpServletRequest request, NaturalPersonCustomer naturalPerson) {
        //context.log("validation");
        String name = request.getParameter("name");
        String family = request.getParameter("family");
        String fatherName = request.getParameter("father");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String birthDateString = request.getParameter("birth");
        Date birthDate=null;
        try {
            birthDate = dateFormat.parse(birthDateString);
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
        String nationalIDString = request.getParameter("nationalID");
        Long nationalID=null;
        try {
            nationalID = Long.parseLong(nationalIDString);
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
        if( name==null || name.equals("") || family==null || family.equals("") || fatherName==null || fatherName.equals("")
                || birthDate==null || nationalID==null )
            return false;
        naturalPerson.setName(name);
        naturalPerson.setFamily(family);
        naturalPerson.setFatherName(fatherName);
        naturalPerson.setBirthDate(birthDate);
        naturalPerson.setNationalID(nationalID);
        return true;
    }
}

