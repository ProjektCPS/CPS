package controllers;

import com.google.gson.Gson;
import config.Constants;
import entities.TypZlavyEntity;
import services.BaseService;
import services.BaseServiceImplement;
import utilities.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "discountTypeControler")
public class discountTypeControler extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mainDiscountTzpe = request.getParameter("discount-type-id");
        int mainDiscountTzpeNumber = Validator.isStringNumber(mainDiscountTzpe) ? Integer.parseInt(mainDiscountTzpe) : -1;

        Map<String, String> data = new HashMap<>();
        request.getParameterMap().forEach((key, item) -> data.put(key, item[0]));

        HttpSession currentSession = request.getSession(false);
        BaseService baseService = new BaseServiceImplement((Integer) currentSession.getAttribute(Constants.TENANT_ID));

        Map<String, String> serviceResponse;


        if(mainDiscountTzpeNumber == -1) {
            serviceResponse = baseService.insertMainDiscountType(data);
        } else {
            serviceResponse = baseService.updateMainDiscountType(mainDiscountTzpe, data);
        }
        if(serviceResponse.get("err") != null) {
            PrintWriter pw = response.getWriter();
            response.setContentType("application/json");
            response.setStatus(500);
            pw.print(serviceResponse.get("err"));
            pw.flush();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession currentSession = request.getSession(false);
        BaseService baseService = new BaseServiceImplement((Integer) currentSession.getAttribute(Constants.TENANT_ID));

        String mainDiscountTzpe = request.getParameter("discount-type-id") != null ?  request.getParameter("discount-type-id").trim() : null;

        if (!Validator.isStringNullOrEmpty(mainDiscountTzpe) && Validator.isStringNumber(mainDiscountTzpe)) {
            TypZlavyEntity typPredmetuEntity = baseService.getMainDiscountType(Integer.parseInt(mainDiscountTzpe));
            String json = new Gson().toJson(typPredmetuEntity);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession currentSession = request.getSession(false);
        BaseService baseService = new BaseServiceImplement((Integer) currentSession.getAttribute(Constants.TENANT_ID));
        String mainDiscountTzpe = request.getParameter("discount-type-id");
        if(Validator.isStringNullOrEmpty(mainDiscountTzpe) && !Validator.isStringNumber(mainDiscountTzpe))
        {
            PrintWriter pw = response.getWriter();
            response.setContentType("application/json");
            response.setStatus(400);
            pw.flush();
        }

        Map<String, String> serviceResponse;
        serviceResponse = baseService.deleteMainDiscountType(Integer.parseInt(mainDiscountTzpe));

        if(serviceResponse.get("err") != null) {
            PrintWriter pw = response.getWriter();
            response.setContentType("application/json");
            response.setStatus(500);
            pw.print(serviceResponse.get("err"));
            pw.flush();
        }

    }
}
