package controllers;

import com.google.gson.Gson;
import config.Constants;
import entities.TenantEntity;
import entities.TypPredmetuEntity;
import services.BaseAdminService;
import services.BaseAdminServiceImpl;
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

@WebServlet(name = "productTypeController")
public class productTypeController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mainCategoryId = request.getParameter("mainCategoryId");
        int mainCategoryIdNumber = Validator.isStringNumber(mainCategoryId) ? Integer.parseInt(mainCategoryId) : -1;

        Map<String, String> data = new HashMap<>();
        request.getParameterMap().forEach((key, item) -> data.put(key, item[0]));

        HttpSession currentSession = request.getSession(false);
        BaseService baseService = new BaseServiceImplement((Integer) currentSession.getAttribute(Constants.TENANT_ID));

        Map<String, String> serviceResponse;


        if(mainCategoryIdNumber == -1) {
            serviceResponse = baseService.insertMainCategory(data);
        } else {
            serviceResponse = baseService.updateMainCategory(mainCategoryId, data);
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

        String mainCategoryId = request.getParameter("mainCategoryId") != null ?  request.getParameter("mainCategoryId").trim() : null;

        if (!Validator.isStringNullOrEmpty(mainCategoryId) && Validator.isStringNumber(mainCategoryId)) {
            TypPredmetuEntity typPredmetuEntity = baseService.getMainCategory(Integer.parseInt(mainCategoryId));
            String json = new Gson().toJson(typPredmetuEntity);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }
}
