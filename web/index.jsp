<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: Bugy
  Date: 07.06.2018
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Sprava Cien</title>

  <!-- Google Fonts -->
  <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,200,300,700,600' rel='stylesheet' type='text/css'>
  <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:400,700,300' rel='stylesheet' type='text/css'>
  <link href='http://fonts.googleapis.com/css?family=Raleway:400,100' rel='stylesheet' type='text/css'>

  <!-- Bootstrap -->
  <link rel="stylesheet" type="text/css" href="css/base/bootstrap.min.css">

  <!-- Font Awesome -->
  <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">

  <!-- Custom CSS -->
  <link rel="stylesheet" href="css/owl.carousel.css">
  <link rel="stylesheet" href="css/home/style.css">
  <link rel="stylesheet" href="css/home/responsive.css">

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body>



<div class="header-area">
  <div class="container">
    <div class="row">
      <div class="col-md-8">

      </div>

      <div class="col-md-4">
        <div class="header-right">
          <ul class="list-unstyled list-inline">
            <li class="dropdown dropdown-small">
              <a data-toggle="dropdown" data-hover="dropdown" class="dropdown-toggle" href="#"><span class="key">currency :</span><span class="value">EUR </span><b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="#">USD</a></li>
                <li><a href="#">EUR</a></li>
                <li><a href="#">GBP</a></li>
              </ul>
            </li>

            <li class="dropdown dropdown-small">
              <a data-toggle="dropdown" data-hover="dropdown" class="dropdown-toggle" href="#"><span class="key">language :</span><span class="value">English </span><b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="#">English</a></li>
                <li><a href="#">Slovak</a></li>
                <li><a href="#">French</a></li>
                <li><a href="#">German</a></li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</div> <!-- End header area -->

<div class="site-branding-area">
  <div class="container">
    <div class="row">
      <div class="col-sm-6">
        <div class="logo">
          <h1><a href="index.jsp">e<span>SpravaCien</span></a></h1>
        </div>
      </div>
    </div>
  </div>
</div> <!-- End site branding area -->

<div class="mainmenu-area">
  <div class="container">
    <div class="row">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
      </div>
      <div class="navbar-collapse collapse">
        <ul class="nav navbar-nav">
          <li><a href="#">Home</a></li>
          <li class="active"><a href="#">My product</a></li>
          <li><a href="#">Discount</a></li>
          <li><a href="#">Customer order</a></li>
          <li><a href="#">Profit</a></li>
          <li><a href=""><i class="fa fa-user" ></i>Log out</a></li>
          <li><%= request.getAttribute("username") %></li>
        </ul>
      </div>

    </div>
  </div>
</div> <!-- End mainmenu area -->


<!-- main content -->
<p> <strong>tu pojde obsah od konkretneho kontrolera ....</strong>
  Loren inpsun dsaaaaasdsa
</p>



<!-- footer area -->
<div class="footer-bottom-area">
  <div class="container">
    <div class="row">
      <div class="col-md-8">
        <div class="copyright">
          <p>&copy; 2018 eShop. All Rights Reserved. Coded with <i class="fa fa-heart"></i> by <a href="http://wpexpand.com" target="_blank">Tulipan & Kaktus</a></p>
        </div>
      </div>

      <div class="col-md-4">
        <div class="footer-card-icon">
          <i class="fa fa-cc-discover"></i>
          <i class="fa fa-cc-mastercard"></i>
          <i class="fa fa-cc-paypal"></i>
          <i class="fa fa-cc-visa"></i>
        </div>
      </div>
    </div>
  </div>
</div>

<!-- Latest jQuery form server -->
<script src="js/base/jquery/jquery-3.2.1.min.js"></script>

<!-- Bootstrap JS form CDN -->
<script src="js//base/bootstrap/bootstrap.min.js"></script>

<!-- jQuery sticky menu -->
<script src="js/base/owl.carousel.min.js"></script>
<script src="js/base/jquery.sticky.js"></script>

<!-- jQuery easing -->
<script src="js/base/jquery/jquery.easing.1.3.min.js"></script>

<!-- Main Script -->
<script src="js/home/home.js"></script>
</body>
</html>
