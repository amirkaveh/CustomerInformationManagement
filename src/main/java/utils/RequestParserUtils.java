package utils;

import model.LegalPersonCustomerModel;
import model.NaturalPersonCustomerModel;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by $Hamid on 4/10/2017.
 */
public class RequestParserUtils {

    public static Integer getInteger(HttpServletRequest request, String parameter) {
        String resultString = request.getParameter(parameter);
        return Integer.parseInt(resultString);
    }

    public static List<Integer> getIntegerList(HttpServletRequest request, String parameter) {
        String resultStringArray[] = request.getParameterValues(parameter);
        List<Integer> resultList = new ArrayList<>();
        for (String string : resultStringArray) resultList.add(Integer.parseInt(string));
        return resultList;
    }

    public static Double getDouble(HttpServletRequest request, String parameter) {
        String resultString = request.getParameter(parameter);
        return Double.parseDouble(resultString);
    }

    public static Long getLong(HttpServletRequest request, String parameter) {
        String resultString = request.getParameter(parameter);
        return Long.parseLong(resultString);
    }

    public static BigDecimal getBigDecimal(HttpServletRequest request, String parameter) {
        String resultString = request.getParameter(parameter);
        return new BigDecimal(resultString);
    }

    public static Date getDate(HttpServletRequest request, String parameter) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = request.getParameter(parameter);
        return dateFormat.parse(dateString);
    }

    public static String getString(HttpServletRequest request, String parameter) {
        return request.getParameter(parameter);
    }

    public static List<String> getStringList(HttpServletRequest request, String parameter) {
        String stringArray[] = request.getParameterValues(parameter);
        List<String> resultList = new ArrayList<>();
        for (String string : stringArray) resultList.add(string);
        return resultList;
    }

    public static List<BigDecimal> getBigDecimalList(HttpServletRequest request, String parameter) {
        String stringArray[] = request.getParameterValues(parameter);
        List<BigDecimal> resultList = new ArrayList<>();
        for (String string : stringArray) resultList.add(new BigDecimal(string));
        return resultList;
    }

    public static NaturalPersonCustomerModel makeNaturalPersonFromRequestParameters(HttpServletRequest request) {
        Integer customerID;
        try {
            customerID = getInteger(request, "customerID");
        } catch (Exception e) {
            customerID = null;
        }
        String name = RequestParserUtils.getString(request, "name");
        String family = RequestParserUtils.getString(request, "family");
        String fatherName = RequestParserUtils.getString(request, "father");
        Date birthDate;
        try {
            birthDate = RequestParserUtils.getDate(request, "birth");
        } catch (Exception e) {
            birthDate = null;
        }
        Long nationalID;
        try {
            nationalID = RequestParserUtils.getLong(request, "nationalID");
        } catch (Exception e) {
            nationalID = null;
        }

        return new NaturalPersonCustomerModel(customerID, name, family, fatherName, birthDate, nationalID);
    }

    public static LegalPersonCustomerModel makeLegalPersonFromRequestParameters(HttpServletRequest request) {
        Integer customerID;
        try {
            customerID = getInteger(request, "customerID");
        } catch (Exception e) {
            customerID = null;
        }
        String name = getString(request, "name");
        Date registrationDate;
        try {
            registrationDate = getDate(request, "registration");
        } catch (Exception e) {
            registrationDate = null;
        }
        Long economicalID;
        try {
            economicalID = getLong(request, "economicalID");
        } catch (Exception e) {
            economicalID = null;
        }

        return new LegalPersonCustomerModel(customerID, name, registrationDate, economicalID);
    }

}
