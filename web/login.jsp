<%--
  Created by IntelliJ IDEA.
  User: Bugy
  Date: 09.06.2018
  Time: 22:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/base/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/base/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/base/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/base/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/base/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/base/daterangepicker.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/base/util.css">
    <link rel="stylesheet" type="text/css" href="css/login/login.css">
    <!--===============================================================================================-->
</head>
<body>

<% if(request.getAttribute("message") != null) { %>
<p style="color: red">
    <%= request.getAttribute("message") %>
</p>
<% } %>

<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100 p-b-160 p-t-50">
            <form action="login" method="POST" class="login100-form validate-form">
                <div class="center-block">
                    <div id="loginLoader" class="loader" hidden></div>
                </div>

					<span class="login100-form-title p-b-43">
						eSpravaCien
					</span>

                <span class="login100-form-sub-title p-b-30">
						Pihlásenie
					</span>

                <div class="wrap-input100 rs1 validate-input" data-validate="Vyplňte email">
                    <input type="email" class="input100"  name="username">
                    <span class="label-input100">Email</span>
                </div>


                <div class="wrap-input100 rs2 validate-input" data-validate="Vyplňte heslo">
                    <input class="input100" type="password" name="password">
                    <span class="label-input100">Heslo</span>
                </div>

                <div class="container-login100-form-btn">
                    <button type="submit" class="login100-form-btn">
                        Prihlásiť sa
                    </button>
                </div>

                <% if (request.getAttribute("msg") != null) { %>
                <div class="center-block w-full p-t-23 ">
                    <div class="alert alert-danger m-b-0">
                        ${msg}.
                    </div>
                </div>
                <% } %>

                <div class="text-center w-full p-t-23">
                    <a href="#" class="txt1">
                        Zabudol si heslo?
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>


<!--===============================================================================================-->
<script src="js/base/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="js/base/animsition.min.js"></script>
<!--===============================================================================================-->
<script src="js/base/bootstrap/popper.min.js"></script>
<script src="js/base/bootstrap/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="js/base/select2.min.js"></script>
<!--===============================================================================================-->
<script src="js/base/daterangepicker/moment.min.js"></script>
<script src="js/base/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
<script src="js/base/countdowntime.js"></script>
<!--===============================================================================================-->
<script src="js/login/login.js"></script>

</body>
</html>
