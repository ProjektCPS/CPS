package controllers.externalSystemAccountsControllers;

import config.Constants;
import entities.RegistrovanyUzivatelEntity;
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

@WebServlet(name = "externalSystemAccountsController")
public class externalSystemAccountsController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession currentSession = request.getSession(false);

        BaseService baseService = new BaseServiceImplement((Integer) currentSession.getAttribute(Constants.TENANT_ID));

        List<RegistrovanyUzivatelEntity> accountsList;
        accountsList = baseService.getExternalSystemAccounts();
        request.setAttribute("accountsList", accountsList);
        request.getRequestDispatcher("/account/externalSystemAccounts/externalSystemAccounts.jsp").forward(request, response);
    }
}
