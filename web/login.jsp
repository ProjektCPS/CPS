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

    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/responsive.css">
</head>
<body>
    <h1>Welcome, please login:</h1>

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
        </h1>
                    <form id="login-form-wrap" class="login collapse" action="login" method="post">
                        <p class="form-row form-row-first">
                            <label for="username">Username or email <span class="required">*</span>
                            </label>
                            <input type="text" id="username" name="username" class="input-text">
                        </p>
                        <p class="form-row form-row-last">
                            <label for="password">Password <span class="required">*</span>
                            </label>
                            <input type="password" id="password" name="password" class="input-text">
                        </p>
                        <div class="clear"></div>


                        <p class="form-row">
                            <input type="submit" value="Login" name="login" class="button">
                            <label class="inline" for="rememberme"><input type="checkbox" value="forever" id="rememberme" name="rememberme"> Remember me </label>
                        </p>
                        <p class="lost_password">
                            <a href="#">Lost your password?</a>
                        </p>

                        <div class="clear"></div>
                    </form>

    </div>
</body>
</html>
