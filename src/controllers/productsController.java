package controllers;

import com.google.gson.Gson;
import services.BaseService;
import services.BaseServiceImplement;
import utilities.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

        if(Validator.isStringNumber(id_admin)!= null){
            BaseService baseService = new BaseServiceImplement();
            List<String> productItems = baseService.getProductType(Integer.parseInt(id_admin));
            json = new Gson().toJson(productItems);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
            json = new Gson().toJson(null);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
