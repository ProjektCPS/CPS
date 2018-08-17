package controllers;

import com.google.gson.Gson;
import entities.KategorieEntity;
import services.BaseService;
import services.BaseServiceImplement;
import utilities.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "productsCategoriesController")
public class productsCategoriesController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = null;
        String id_admin = request.getParameter("id_admin").trim();
        String categoryName = request.getParameter("categoryName").trim();
        List<String> categoryItems = new ArrayList<>();
        if(Validator.isStringNumber(id_admin)!= null && !Validator.isStringNullOrEmpty(categoryName)){
            BaseService baseService = new BaseServiceImplement();
            List<KategorieEntity> listCategoriesObjects = baseService.getProductCategories(Integer.parseInt(id_admin),categoryName);
            for (KategorieEntity category: listCategoriesObjects) {
                categoryItems.add(category.getNazov());
            }
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        request.setAttribute("categories", categoryItems);
        request.setAttribute("id_admin",request.getParameter("id_admin"));
        request.getRequestDispatcher("/account/categories.jsp").forward(request, response);
    }
}
