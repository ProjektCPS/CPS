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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "discountsAsyncController")
public class discountsAsyncController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String discountType = request.getParameter("type").trim();
        List<Discount> discounts = new ArrayList<>();
        if(!Validator.isStringNullOrEmpty(discountType) && DiscountTypes.contains(discountType)){
            HttpSession currentSession = request.getSession(false);
            BaseService baseService = new BaseServiceImplement((Integer) currentSession.getAttribute(Constants.TENANT_ID));
            discounts = baseService.getDiscounts(DiscountTypes.getIfExists(discountType));
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        String json = new Gson().toJson(discounts);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
