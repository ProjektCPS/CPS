package controllers.adminConrollers;

import com.google.gson.Gson;
import entities.TenantEntity;
import services.BaseAdminService;
import services.BaseAdminServiceImpl;
import utilities.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "tenantController")
public class tenantController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BaseAdminService baseService = new BaseAdminServiceImpl();

        String tenantId = request.getParameter("tenantId") != null ? request.getParameter("tenantId").trim() : null;

        if (!Validator.isStringNullOrEmpty(tenantId) && Validator.isStringNumber(tenantId)) {
            TenantEntity tenantEntity = baseService.getTenant(Integer.parseInt(tenantId));
            String json = new Gson().toJson(tenantEntity);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }
}
