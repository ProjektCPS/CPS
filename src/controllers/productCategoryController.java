package controllers;

import com.google.gson.Gson;
import config.Constants;
import entities.KategorieEntity;
import entities.TypPredmetuEntity;
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
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "productCategoryController")
public class productCategoryController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productCategoryId = request.getParameter("productCategoryId");
        int productCategoryIdNumber = Validator.isStringNumber(productCategoryId) ? Integer.parseInt(productCategoryId) : -1;

        Map<String, String> data = new HashMap<>();
        request.getParameterMap().forEach((key, item) -> data.put(key, item[0]));

        HttpSession currentSession = request.getSession(false);
        BaseService baseService = new BaseServiceImplement((Integer) currentSession.getAttribute(Constants.TENANT_ID));

        Map<String, String> serviceResponse;

        if(productCategoryIdNumber == -1) {
            serviceResponse = baseService.insertProductCategory(data);
        } else {
            serviceResponse = baseService.updateProductCategory(productCategoryIdNumber, data);
        }
        if(serviceResponse.get("err") != null) {
            PrintWriter pw = response.getWriter();
            response.setContentType("application/json");
            response.setStatus(500);
            pw.print(serviceResponse.get("err"));
            pw.flush();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession currentSession = request.getSession(false);
        BaseService baseService = new BaseServiceImplement((Integer) currentSession.getAttribute(Constants.TENANT_ID));

        String productCategoryId = request.getParameter("productCategoryId") != null ?  request.getParameter("productCategoryId").trim() : null;

        if (!Validator.isStringNullOrEmpty(productCategoryId) && Validator.isStringNumber(productCategoryId)) {
            KategorieEntity kategorieEntity = baseService.getProductCategory(Integer.parseInt(productCategoryId));
            String json = new Gson().toJson(kategorieEntity);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }
}
