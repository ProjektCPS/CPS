package controllers;

import config.Constants;
import entities.UcetEntity;
import services.BaseService;
import services.BaseServiceImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
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
            UcetEntity user = loginService.login(username, password);
            if (user != null) {
                //get the old session and invalidate
                HttpSession oldSession = request.getSession(false);
                if (oldSession != null) {
                    oldSession.invalidate();
                }
                //generate a new session
                HttpSession newSession = request.getSession(true);

                //setting session to expiry in 30 mins
                newSession.setMaxInactiveInterval(30*60);
                newSession.setAttribute(Constants.USER_NAME, username);
                newSession.setAttribute(Constants.TENANT_ID, user.getUzivatel());
                newSession.setAttribute(Constants.ADMIN_ID, user.getIdAdmin());
                newSession.setAttribute(Constants.ROLE, user.getRola());


                Cookie message = new Cookie("message", "Welcome");
                response.addCookie(message);

                request.setAttribute("username", username);
                page = "account/index.jsp";
                request.setAttribute("id_admin", user.getIdAdmin());
            } else {
                request.setAttribute("msg", "Nesprávny email alebo heslo!");
            }
        } else {
            request.setAttribute("msg", "Prosím zadajte email a heslo..");
        }
        request.getRequestDispatcher(page).forward(request, response);
}

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.sendRedirect("/login.jsp");
    }
}
