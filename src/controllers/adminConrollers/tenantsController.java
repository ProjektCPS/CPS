package controllers.adminConrollers;

import entities.TenantEntity;
import services.BaseAdminService;
import services.BaseAdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "tenantsController")
public class tenantsController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TenantEntity> tenants;
        BaseAdminService baseService = new BaseAdminServiceImpl();
        tenants =  baseService.getTenants();
        request.setAttribute("tenants", tenants);
        request.getRequestDispatcher("/account/admin/tenants.jsp").forward(request, response);
    }
}
