package controllers.adminConrollers;

import dataAccessObjects.BusinessStates;
import entities.*;
import services.BaseAdminService;
import services.BaseAdminServiceImpl;
import utilities.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "accountController")
public class accountController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String adminId = request.getParameter("accountId");
        int adminIdNumber = Validator.isStringNumber(adminId) ? Integer.parseInt(adminId) : -1;

        Map<String, String> accountData = new HashMap<>();
        request.getParameterMap().forEach((key, item) -> accountData.put(key, item[0]));

        BaseAdminService baseService = new BaseAdminServiceImpl();

        Map<String, String> serviceResponse;

        if(adminIdNumber == -1) {
            serviceResponse = baseService.insertAccount(accountData);
        } else {
            serviceResponse = baseService.updateAccount(adminIdNumber, accountData);
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
        BaseAdminService baseService = new BaseAdminServiceImpl();

        String accountId = request.getParameter("accountId") != null ?  request.getParameter("accountId").trim() : null;

        if (!Validator.isStringNullOrEmpty(accountId) && Validator.isStringNumber(accountId)) {
            Object[] userAllInformation = baseService.getAdminAccount(Integer.parseInt(accountId), BusinessStates.PERSON);

            for (Object o : userAllInformation) {
                if (o instanceof UcetEntity) {
                    request.setAttribute("account", o);
                }
                if (o instanceof OsobaEntity) {
                    request.setAttribute("person", o);
                }
                if (o instanceof FirmaEntity) {
                    request.setAttribute("company", o);
                }
                if (o instanceof MestoEntity) {
                    request.setAttribute("city", o);
                }
                if (o instanceof OkresEntity) {
                    request.setAttribute("region", o);
                }
                if (o instanceof KrajEntity) {
                    request.setAttribute("state", o);
                }
                if (o instanceof KrajinaEntity) {
                    request.setAttribute("country", o);
                }
            }
        }

        List<TenantEntity> tenants = baseService.getTenants();

        request.setAttribute("tenants", tenants);
        request.getRequestDispatcher("/account/admin/account.jsp").forward(request, response);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
