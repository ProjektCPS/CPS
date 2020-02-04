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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "productsControler")
public class productsControler extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        int productIdNumber = Validator.isStringNumber(productId) ? Integer.parseInt(productId) : -1;

        Map<String, String> data = new HashMap<>();
        request.getParameterMap().forEach((key, item) -> data.put(key, item[0]));

        String name = data.get("nazov");
        String unit = data.get("jednotka");
        double price = Double.parseDouble(data.get("cena"));
        int categoryId = Integer.parseInt(data.get("categoryId"));
        if (Validator.isStringNullOrEmpty(name) || Validator.isStringNullOrEmpty(unit) || price < 0 || categoryId < 1)  {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        HttpSession currentSession = request.getSession(false);
        BaseService baseService = new BaseServiceImplement((Integer) currentSession.getAttribute(Constants.TENANT_ID));

        Map<String, String> serviceResponse;

        if (productIdNumber == -1) {
            serviceResponse = baseService.insertProduct(data);
        } else {
            serviceResponse = baseService.updateProduct(productIdNumber, data);
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
