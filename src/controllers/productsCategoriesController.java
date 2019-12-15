package controllers;

import config.Constants;
import entities.KategorieEntity;
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

@WebServlet(name = "productsCategoriesController")
public class productsCategoriesController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = null;
        String categoryName = request.getParameter("categoryName").trim();
        List<String> categoryItems = new ArrayList<>();
        if(!Validator.isStringNullOrEmpty(categoryName)){
            HttpSession curentSession = request.getSession(false);
            BaseService baseService = new BaseServiceImplement((Integer) curentSession.getAttribute(Constants.TENANT_ID));
            List<KategorieEntity> listCategoriesObjects = baseService.getProductCategories(categoryName);
            for (KategorieEntity category: listCategoriesObjects) {
                categoryItems.add(category.getNazov());
            }
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        request.setAttribute("categories", categoryItems);
        request.getRequestDispatcher("/account/categories.jsp").forward(request, response);
    }
}
