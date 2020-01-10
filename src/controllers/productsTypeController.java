package controllers;

import com.google.gson.Gson;
import config.Constants;
import entities.TypPredmetuEntity;
import services.BaseService;
import services.BaseServiceImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "productsTypeController")
public class productsTypeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession curentSession = request.getSession(false);

        BaseService baseService = new BaseServiceImplement((Integer) curentSession.getAttribute(Constants.TENANT_ID));

        List<TypPredmetuEntity> productItems = baseService.getProductTypes();
        String json = new Gson().toJson(productItems);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
