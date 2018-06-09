package controllers;

import services.BaseService;
import services.BaseServiceImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "loginController")
public class loginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String page = "/login.jsp";
        if (username.trim().length() >= 0 && username != null && password.trim().length() >= 0 && password != null) {
            BaseService loginService = new BaseServiceImplement();
            boolean flag = loginService.login(username, password);
            if (flag) {
                System.out.println("Login success!!!");
                request.setAttribute("username", username);
                request.setAttribute("msg", "Login Success.....");
                page = "/index.jsp";
            } else {
                request.setAttribute("msg", "Wrong Username or Password, Try again!!!");
            }
        } else {
            request.setAttribute("msg", "Please enter username and password...");
        }
        request.getRequestDispatcher(page).forward(request, response);
}

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.sendRedirect("/login.jsp");
    }
}
