package controllers.adminConrollers;

import com.google.gson.Gson;
import dataAccessObjects.BusinessStates;
import entities.*;
import services.BaseService;
import services.BaseServiceImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "accountController")
public class accountController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BaseService baseService = new BaseServiceImplement();
        Object[] userAllInformation = baseService.getAccount(1, BusinessStates.PERSON);
        for (Object o :userAllInformation) {
            if(o instanceof UcetEntity){
                request.setAttribute("account", o);
            }
            if(o instanceof OsobaEntity){
                request.setAttribute("person", o);
            }
            if(o instanceof FirmaEntity){
                request.setAttribute("company", o);
            }
            if(o instanceof MestoEntity){
                request.setAttribute("city", o);
            }
            if(o instanceof OkresEntity){
                request.setAttribute("region", o);
            }
            if(o instanceof KrajEntity){
                request.setAttribute("state", o);
            }
            if(o instanceof KrajinaEntity){
                request.setAttribute("country", o);
            }
        }
        request.getRequestDispatcher("/account/admin/account.jsp").forward(request, response);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
