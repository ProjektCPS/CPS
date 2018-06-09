<%--
  Created by IntelliJ IDEA.
  User: Bugy
  Date: 07.06.2018
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h1>Welcom, pleas login:</h1>

    </br> </br> </br>
    <h1>
        <div align="center">
                <% if(request.getAttribute("msg") != null) { %>
            <p style="color: red">
                <%= request.getAttribute("msg") %>
            </p>
                <% } %>
                <% if(request.getAttribute("msg2") != null) { %>
            <p style="color: green;">
                <%= request.getAttribute("msg2") %>
            </p>
                <% } %>

            <form action="login" method="POST">
                <label>Enter Username : </label>
                <input type="text" name="username"> <br> <br>
                <label>Enter Password : </label>
                <input type="password" name="password" > <br> <br>
                <input type="submit" value="Login">
            </form>
    </h1>
    </div>
</body>
</html>
