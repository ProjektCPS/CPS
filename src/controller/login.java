package controller;

import appLayer.User;

import java.io.IOException;
import java.io.PrintWriter;

@javax.servlet.annotation.WebServlet(name = "login")
public class login extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        User userObject = new User();

        String login = request.getParameter("loginName");
        String password = request.getParameter("password");

        request.setAttribute("userName", login);
        request.setAttribute("password", password);

        if(userObject.isValidUserCredentials(login,password)){
            request.getRequestDispatcher("/welcome.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Invalid login or password");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }


    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.print("login name: " + request.getParameter("loginName") + " Password: " + request.getParameter("password"));
    }
}
