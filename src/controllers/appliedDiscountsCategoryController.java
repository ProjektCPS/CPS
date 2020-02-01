package controllers;

import com.google.gson.Gson;
import config.Constants;
import dataAccessObjects.AppliedDiscountTypes;
import entities.customEntities.Discount;
import services.BaseService;
import services.BaseServiceImplement;
import utilities.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class appliedDiscountsCategoryController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession currentSession = request.getSession(false);
        BaseService baseService = new BaseServiceImplement((Integer) currentSession.getAttribute(Constants.TENANT_ID));

        String productCategoryId = request.getParameter("productCategoryId") != null ?  request.getParameter("productCategoryId").trim() : null;

        if (!Validator.isStringNullOrEmpty(productCategoryId) && Validator.isStringNumber(productCategoryId)) {
            List<Discount> appliedDiscounts = baseService.getAppliedDiscounts(Integer.parseInt(productCategoryId), AppliedDiscountTypes.productCategory);
            String json = new Gson().toJson(appliedDiscounts);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

    }
}
