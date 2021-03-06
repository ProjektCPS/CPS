<%@tag language="java" pageEncoding="UTF-8" %>
<%@attribute name="title" required="true" rtexprvalue="true" %>
<%@attribute name="content" fragment="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <link rel="icon" href="../../image/icon.png">
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
    <link rel="stylesheet" type="text/css" href="../../css/base/bootstrap.min.css">

    <!-- Font Awesome -->
    <link rel="stylesheet" type="text/css" href="../../fonts/font-awesome-4.7.0/css/font-awesome.min.css">

    <!--- Component CSS -->
    <link rel="stylesheet" type="text/css" href="../../css/base/semantic.css">

    <!--- Node modules CSS -->
    <link rel="stylesheet" href="../../node_modules/semantic-ui-calendar/dist/calendar.min.css">
    <link rel="stylesheet" type="text/css" href="../../node_modules/datatables.net-se/css/dataTables.semanticui.min.css">

    <!-- Custom CSS -->
    <!-- <link rel="stylesheet" href="css/owl.carousel.css"> -->
    <link rel="stylesheet" href="../../css/base/main.css">
    <link rel="stylesheet" href="../../css/base/util.css">
    <link rel="stylesheet" href="../../css/base/responsive.css">
    <link rel="stylesheet" href="../../css/base/customSemantic.css">

    <!-- Latest jQuery form server -->
    <script src="../../js/base/jquery/jquery.min.js"></script>
    <!-- Bootstrap JS form CDN -->

    <!-- jQuery sticky menu -->
    <script src="../../js/base/owl.carousel.min.js"></script>
    <script src="../../js/base/semantic.min.js"></script>
    <script src="../../js/base/dropdown.js"></script>
    <script src="../../js/base/checkbox.js"></script>
    <script src="../../js/base/popup.js"></script>
    <script src="../../js/base/constants/constants.js"></script>
    <script src="../../js/utils/helpers.js"></script>
    <script src="../../node_modules/semantic-ui-calendar/dist/calendar.min.js"></script>
    <script src="../../node_modules/datatables.net-se/js/dataTables.semanticui.min.js"></script>
    <script src="../../node_modules/datatables.net/js/jquery.dataTables.min.js"></script>

    <!-- jQuery easing -->
    <script src="../../js/base/jquery/jquery.easing.1.3.min.js"></script>

    <!-- Main -->
    <script src="../../js/base/main.js"></script>


    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="dis-flex flex-col h-full">
    <div class="site-branding-area">
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    <div class="logo">
                        <h1><a href="/">e<span>SpravaCien</span></a></h1>
                    </div>
                </div>
                <div class="col-sm-6 flex-r h-full">
                    <div class="avatarWrapper">
                        <div class="avatar">
                            <img class="ui small circular avatarImg"
                                 data-html="<p>Rola: ${sessionScope.role}</br> Tenant id: ${sessionScope.tenantId == null || sessionScope.tenantId == -1 ? "žiadny" : sessionScope.tenantId}</p>"
                                 src=${sessionScope.role == 'admin' ? "../../image/admin.png" : "../../image/user.png"}>
                        </div>
                        <span class="avatarName">${sessionScope.userName}</span>
                    </div>
                </div>
            </div>
        </div>
    </div> <!-- End site branding area -->

    <div class="ui menuCustom inverted small menu">
        <div class="container">
            <a id="home" href="/" class="blue item">Domov</a>
            <c:if test="${sessionScope.tenantId != -1 && sessionScope.tenantId != null}">
                <div id="products" class="ui dropdown item">
                    Produkty
                    <div class="menu inverted">
                        <a class="inverted blue item">
                            <i class="fa fa-spinner fa-spin fa-2x fa-fw center"></i>
                        </a>
                    </div>
                </div>
                <a id="discounts" class="blue item " href="/account/discount_categories.jsp">Zľavy</a>
                <a id="externalSystemAccounts" class="blue item "
                   href="/account/externalSystemAccounts/externalSystemAccounts">Účty</a>
            </c:if>
            <c:if test="${sessionScope.role == 'admin'}">
                <c:out value=""/><a id="accounts" class="blue item " href="/account/admin/accounts">Správa cien účty</a>
                <a id="tenants" class="blue item " href="/account/admin/tenants">Tenanti</a>
            </c:if>
            <c:if test="${sessionScope.tenantId != -1 && sessionScope.tenantId != null}">
                <a id="profile" class="blue item " href="/account/profile">Profil</a>
            </c:if>
            <div class="right menu">
                <div class="item">
                    <a href="/account/logout">
                        <div id class="ui primary button"><i class="fa fa-user"></i>Odhlásiť</div>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <!-- End mainmenu area -->


    <!-- main content -->
    <div class="contentWrapper">
        <jsp:invoke fragment="content"/>
    </div>

    <!-- footer area -->
    <div class="footer-bottom-area">
        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <div class="copyright">
                        <p>&copy; 2019 eSpravaCien. All Rights Reserved. Coded with <i class="fa fa-heart"></i> by <a
                                href="http://wpexpand.com" target="_blank">the best students</a></p>
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
</div>
</body>
</html>
