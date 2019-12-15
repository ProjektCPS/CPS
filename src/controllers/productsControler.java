package controllers;

import com.google.gson.Gson;
import config.Constants;
import entities.PredmetPredajaEntity;
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
        List<PredmetPredajaEntity> productsItems = new ArrayList<>();
        if(!Validator.isStringNullOrEmpty(categoryName)){
            HttpSession curentSession = request.getSession(false);

            BaseService baseService = new BaseServiceImplement((Integer) curentSession.getAttribute(Constants.TENANT_ID));
            productsItems = baseService.getProduct(categoryName);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
        }

        String json = new Gson().toJson(productsItems);
        request.setAttribute("productsItems", productsItems);
        request.getRequestDispatcher("/account/products.jsp").forward(request, response);
    }

}
