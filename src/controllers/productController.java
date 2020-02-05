package controllers;

import config.Constants;
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

@WebServlet(name = "productController")
public class productController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productCategoryId = request.getParameter("productCategoryId") != null ? request.getParameter("productCategoryId").trim() : null;

        if (Validator.isStringNullOrEmpty(productCategoryId) || !Validator.isStringNumber(productCategoryId)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.getRequestDispatcher("/account/home.jsp").forward(request, response);
        } else {
            request.setAttribute("productCategoryId", productCategoryId);
            request.getRequestDispatcher("/account/AddOrUpdateProduct.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        int productIdNumber = Validator.isStringNumber(productId) ? Integer.parseInt(productId) : -1;

        Map<String, String> data = new HashMap<>();
        request.getParameterMap().forEach((key, item) -> data.put(key, item[0]));

        String name = data.get("name");
        String unit = data.get("unit");
        String price = data.get("price");
        String categoryId = data.get("categoryId");
        if (Validator.isStringNullOrEmpty(name)
                || Validator.isStringNullOrEmpty(unit)
                || Validator.isStringNullOrEmpty(price)
                || Validator.isStringNullOrEmpty(categoryId)
                || !Validator.isStringDouble(price)
                || !Validator.isStringNumber(categoryId)
                || Double.parseDouble(price) < 0 || Integer.parseInt(categoryId) < 1)  {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        HttpSession currentSession = request.getSession(false);
        BaseService baseService = new BaseServiceImplement((Integer) currentSession.getAttribute(Constants.TENANT_ID));

        Map<String, String> serviceResponse;

        if (productIdNumber == -1) {
            serviceResponse = baseService.insertProduct(data);
        } else {
            serviceResponse = baseService.updateProduct(productIdNumber, data);
        }
        if (serviceResponse.get("err") != null) {
            PrintWriter pw = response.getWriter();
            response.setContentType("application/json");
            response.setStatus(500);
            pw.print(serviceResponse.get("err"));
            pw.flush();
        }
    }
}
