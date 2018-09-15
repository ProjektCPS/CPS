package controllers.adminConrollers;

import entities.UcetEntity;
import services.BaseService;
import services.BaseServiceImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "accountsController")
public class accountsController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UcetEntity> accountsList = new ArrayList<>();
        BaseService baseService = new BaseServiceImplement();
        accountsList = baseService.getAccounts();
        request.setAttribute("accountsList", accountsList);
        request.getRequestDispatcher("/account/admin/accounts.jsp").forward(request, response);
    }
}
