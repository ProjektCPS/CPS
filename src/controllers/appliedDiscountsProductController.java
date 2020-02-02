package controllers;

import com.google.gson.Gson;
import config.Constants;
import dataAccessObjects.AppliedDiscountTypes;
import entities.customEntities.Discount;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "appliedDiscountsProductController")
public class appliedDiscountsProductController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession currentSession = request.getSession(false);
        BaseService baseService = new BaseServiceImplement((Integer) currentSession.getAttribute(Constants.TENANT_ID));

        String productyId = request.getParameter("productId") != null ? request.getParameter("productId").trim() : null;

        if (!Validator.isStringNullOrEmpty(productyId) && Validator.isStringNumber(productyId)) {
            List<Discount> appliedDiscounts = baseService.getAppliedDiscounts(Integer.parseInt(productyId), AppliedDiscountTypes.product);
            String json = new Gson().toJson(appliedDiscounts);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId") != null ? request.getParameter("productId").trim() : null;

        if (Validator.isStringNullOrEmpty(productId) || !Validator.isStringNumber(productId)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        List<Integer> discountsIDs = new ArrayList<>();

        request.getParameterMap().forEach((key, item) -> {
            if (key.equals("ids[]")) {
                for (String id : item) {
                    discountsIDs.add(Integer.parseInt(id));
                }
            }
        });

        HttpSession currentSession = request.getSession(false);
        BaseService baseService = new BaseServiceImplement((Integer) currentSession.getAttribute(Constants.TENANT_ID));

        Map<String, String> serviceResponse = baseService.applyOnlyDiscounts(Integer.parseInt(productId), discountsIDs, AppliedDiscountTypes.product);

        if (serviceResponse.get("err") != null) {
            PrintWriter pw = response.getWriter();
            response.setContentType("application/json");
            response.setStatus(500);
            pw.print(serviceResponse.get("err"));
            pw.flush();
        }
    }
}
