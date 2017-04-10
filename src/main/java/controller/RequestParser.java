package controller;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by $Hamid on 4/10/2017.
 */
public class RequestParser {

    public static Integer getInteger(HttpServletRequest request, String parameter) {
        String resultString = request.getParameter(parameter);
        return Integer.parseInt(resultString);
    }

    public static Long getLong(HttpServletRequest request, String parameter) {
        String resultString = request.getParameter(parameter);
        return Long.parseLong(resultString);
    }

    public static Date getDate(HttpServletRequest request, String parameter) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = request.getParameter(parameter);
        return dateFormat.parse(dateString);
    }

    public static String getString(HttpServletRequest request, String parameter){
        return request.getParameter(parameter);
    }

}
