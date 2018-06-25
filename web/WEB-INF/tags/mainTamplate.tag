<%@tag language="java" pageEncoding="UTF-8" %>
<%@attribute name="title" required="true" rtexprvalue="true" %>
<%@attribute name="content" fragment="true" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="text/html">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${title}</title>

    <!-- Google Fonts -->
    <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,200,300,700,600' rel='stylesheet'
          type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:400,700,300' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Raleway:400,100' rel='stylesheet' type='text/css'>
    <link href="https://fonts.googleapis.com/css?family=Shrikhand" rel="stylesheet">
    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="css/base/bootstrap.min.css">

    <!-- Font Awesome -->
    <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">

    <!-- Custom CSS -->
    <!-- <link rel="stylesheet" href="css/owl.carousel.css"> -->
    <link rel="stylesheet" href="css/home/style.css">
    <link rel="stylesheet" href="css/base/util.css">
    <link rel="stylesheet" href="css/home/responsive.css">

    <!--- Component CSS -->
    <link rel="stylesheet" type="text/css" href="css/base/semantic.css">


    <!-- Latest jQuery form server -->
    <script src="js/base/jquery/jquery.min.js"></script>
    <!-- Bootstrap JS form CDN -->
    <script src="js/base/bootstrap/bootstrap.min.js"></script>
    <!-- jQuery sticky menu -->
    <script src="js/base/owl.carousel.min.js"></script>
    <script src="js/base/semantic.min.js"></script>

    <!-- jQuery easing -->
    <script src="js/base/jquery/jquery.easing.1.3.min.js"></script>

    <!-- Main -->
    <script src="js/base/main.js"></script>


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
                            <a data-toggle="dropdown" data-hover="dropdown" class="dropdown-toggle" href="#"><span
                                    class="key">currency :</span><span class="value">EUR </span><b
                                    class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">USD</a></li>
                                <li><a href="#">EUR</a></li>
                                <li><a href="#">GBP</a></li>
                            </ul>
                        </li>

                        <li class="dropdown dropdown-small">
                            <a data-toggle="dropdown" data-hover="dropdown" class="dropdown-toggle" href="#"><span
                                    class="key">language :</span><span class="value">English </span><b
                                    class="caret"></b></a>
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
            <input id="id_admin" type="text" value="${id_admin}">
        </div>
    </div>
</div> <!-- End site branding area -->

<div class=" ui inverted small menu">
    <div class="container">
        <a id="home" class="blue item">Domov</a>
        <div id="products" class="ui dropdown item">
            Produkty
            <div class="menu inverted">
                <a class="inverted blue item">
                    <i class="fa fa-spinner fa-spin fa-2x fa-fw center"></i>
                </a>
            </div>
        </div>
        <a id="discount" class="blue item ">Zľavy</a>
        <a id="orders" class="blue item ">Objednávky</a>
        <a id="profit" class="blue item " href="#">Profit</a>
        <div class="right menu">
            <div class="item">
                <a href="login">
                    <div id class="ui primary button"><i class="fa fa-user"></i>Odhlásiť</div>
                </a>
            </div>
        </div>
    </div>
</div>
<!-- End mainmenu area -->


<!-- main content -->
<jsp:invoke fragment="content"/>

<!-- footer area -->
<div class="footer-bottom-area">
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div class="copyright">
                    <p>&copy; 2018 eShop. All Rights Reserved. Coded with <i class="fa fa-heart"></i> by <a
                            href="http://wpexpand.com" target="_blank">Tulipan & Kaktus</a></p>
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
</body>
</html>
