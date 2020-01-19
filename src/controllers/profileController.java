package controllers;

import config.Constants;
import dataAccessObjects.BusinessStates;
import entities.*;
import services.BaseAdminService;
import services.BaseAdminServiceImpl;
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

@WebServlet(name = "profileController")
public class profileController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession currentSession = request.getSession(false);
        BaseService baseService = new BaseServiceImplement((Integer) currentSession.getAttribute(Constants.TENANT_ID));
        BaseAdminService baseAdminService = new BaseAdminServiceImpl();

        Object[] userAllInformation = baseAdminService.getAdminAccount((Integer) currentSession.getAttribute(Constants.ADMIN_ID), BusinessStates.PERSON);
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

        List<TypPredmetuEntity> mainCategories =  baseService.getProductTypes();
        List<TypZlavyEntity> mainDiscounts =  baseService.getMainDiscountTypes();
        request.setAttribute("mainCategories", mainCategories);
        request.setAttribute("discountsType", mainDiscounts);
        request.getRequestDispatcher("/account/profile.jsp").forward(request, response);
    }
}
