package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import services.BaseService;
import services.BaseServiceImplement;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "productsController")
public class productsController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = null;
        String id_admin = request.getParameter("id_admin").trim();
        int id_adminInteger = 0;

        try {
            id_adminInteger = Integer.parseInt(id_admin);
        }catch (NumberFormatException e){
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(json = new Gson().toJson(null));
            return;
        }

        BaseService baseService = new BaseServiceImplement();
        List<String> productItems = baseService.getProductCategories(id_adminInteger);
        json = new Gson().toJson(productItems);


        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
