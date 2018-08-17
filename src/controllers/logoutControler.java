package controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "logoutControler")
public class logoutControler extends HttpServlet {
    String page = "/login.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //invalidate the session if exists
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        request.setAttribute("message", "You are successfully logged out!");
        response.sendRedirect(page);
    }
}
