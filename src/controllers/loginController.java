package controllers;

import config.Constants;
import entities.UcetEntity;
import services.BaseAdminService;
import services.BaseAdminServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "loginController")
public class loginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String pageLogin = "/login.jsp";
        if (username != null && username.trim().length() >= 0 && password != null && password.trim().length() >= 0) {
            BaseAdminService loginService = new BaseAdminServiceImpl();
            UcetEntity user = loginService.login(username, password);
            if (user != null) {
                if(user.getActive() == 1) {
                    //get the old session and invalidate
                    HttpSession oldSession = request.getSession(false);
                    if (oldSession != null) {
                        oldSession.invalidate();
                    }
                    //generate a new session
                    HttpSession newSession = request.getSession(true);

                    //setting session to expiry in 30 mins
                    newSession.setMaxInactiveInterval(30 * 60);
                    newSession.setAttribute(Constants.USER_NAME, username);
                    newSession.setAttribute(Constants.TENANT_ID, user.getTenantId());
                    newSession.setAttribute(Constants.ADMIN_ID, user.getIdAdmin());
                    newSession.setAttribute(Constants.ROLE, user.getRola());

                    Cookie message = new Cookie("message", "Welcome");
                    response.addCookie(message);

                    request.setAttribute("username", username);
                    request.setAttribute("id_admin", user.getIdAdmin());
                } else {
                    request.setAttribute("msg", "Používateľ nieje aktívny. Kontaktujte prosím poskytovateľa.");
                    request.getRequestDispatcher(pageLogin).forward(request, response);
                }
            } else {
                request.setAttribute("msg", "Nesprávny email alebo heslo!");
                request.getRequestDispatcher(pageLogin).forward(request, response);
            }
        } else {
            request.setAttribute("msg", "Prosím zadajte email a heslo..");
            request.getRequestDispatcher(pageLogin).forward(request, response);
        }

        response.sendRedirect("/account/home.jsp");

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.sendRedirect("/account/home.jsp");
    }
}
