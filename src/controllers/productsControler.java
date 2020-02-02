package controllers;

import config.Constants;
import dataAccessObjects.AppliedDiscountTypes;
import entities.customEntities.Discount;
import entities.customEntities.Product;
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

@WebServlet(name = "productsControler")
public class productsControler extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryName = request.getParameter("categoryName").trim();
        String productCategoryId = request.getParameter("productCategoryId") != null ? request.getParameter("productCategoryId").trim() : null;

        List<Product> productsItems = new ArrayList<>();
        List<Discount> appliedDiscounts = new ArrayList<>();
        List<String> appliedCategoryDiscountsTypes = new ArrayList<>();
        if (!Validator.isStringNullOrEmpty(categoryName) && !Validator.isStringNullOrEmpty(productCategoryId) && Validator.isStringNumber(productCategoryId)) {
            HttpSession currentSession = request.getSession(false);
            BaseService baseService = new BaseServiceImplement((Integer) currentSession.getAttribute(Constants.TENANT_ID));
            productsItems = baseService.getProducts(categoryName);
            appliedDiscounts = baseService.getAppliedDiscounts(Integer.parseInt(productCategoryId), AppliedDiscountTypes.productCategory);
            appliedCategoryDiscountsTypes = baseService.getAppliedDiscountTypes(Integer.parseInt(productCategoryId), AppliedDiscountTypes.productCategory);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        request.setAttribute("productsItems", productsItems);
        request.setAttribute("appliedCategoryDiscounts", appliedDiscounts);
        request.setAttribute("appliedCategoryDiscountsTypes", appliedCategoryDiscountsTypes);

        request.getRequestDispatcher("/account/products.jsp").forward(request, response);
    }
}
