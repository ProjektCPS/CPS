package controllers;

import com.google.gson.Gson;
import config.Constants;
import dataAccessObjects.DiscountTypes;
import entities.customEntities.Discount;
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
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "discountController")
public class discountController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String discountId = request.getParameter("discountId");
        int discountIdNumber = Validator.isStringNumber(discountId) ? Integer.parseInt(discountId) : -1;

        Map<String, String> data = new HashMap<>();
        request.getParameterMap().forEach((key, item) -> data.put(key, item[0]));

        String requireDiscountType = data.get("discountType");
        if (Validator.isStringNullOrEmpty(requireDiscountType) || !DiscountTypes.contains(requireDiscountType)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        HttpSession currentSession = request.getSession(false);
        BaseService baseService = new BaseServiceImplement((Integer) currentSession.getAttribute(Constants.TENANT_ID));

        Map<String, String> serviceResponse;

        if (discountIdNumber == -1) {
            serviceResponse = baseService.insertDiscount(data);
        } else {
            serviceResponse = baseService.updateDiscount(discountIdNumber, data);
        }
        if (serviceResponse.get("err") != null) {
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

        String discountId = request.getParameter("discountId") != null ?  request.getParameter("discountId").trim() : null;

        if (!Validator.isStringNullOrEmpty(discountId) && Validator.isStringNumber(discountId)) {
            Discount zlavaEntity = baseService.getDiscount(Integer.parseInt(discountId));
            String json = new Gson().toJson(zlavaEntity);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

    }
}
