package controllers;

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

@WebServlet(name = "productsControler")
public class productsControler extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = null;
        String id_admin = request.getParameter("id_admin").trim();
        String categoryName = request.getParameter("categoryName").trim();
        List<String> productsItems = new ArrayList<>();
        if(Validator.isStringNumber(id_admin)!= null && !Validator.isStringNullOrEmpty(categoryName)){
            BaseService baseService = new BaseServiceImplement();
            productsItems = baseService.getProduct(Integer.parseInt(id_admin),categoryName);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
        }

        request.setAttribute("nazov", productsItems.get(0));
        request.setAttribute("znacka", productsItems.get(1));
        request.setAttribute("nazovCat", productsItems.get(2));
        request.setAttribute("cena", productsItems.get(3));
        request.setAttribute("id_admin",request.getParameter("id_admin"));
        request.getRequestDispatcher("/products.jsp").forward(request, response);
    }

}
