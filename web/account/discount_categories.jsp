<%--
  Created by IntelliJ IDEA.
  User: Michal
  Date: 22.06.2018
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>
<mt:mainTamplate title="Zlavy kategorie">
  <jsp:attribute name="content">
<html>
<header>
    <script src="../js/discounts/discount_categories.js"></script>
</header>
<body>
<div class="container m-t-15">
    <div class="ui three stackable cards">
            <div class="ui card">
                <a href="discount.jsp">
                    <div class="image">
                        <div class="ui blurring inverted dimmer">
                            <div class="content">
                                <div class="center">
                                </div>
                            </div>
                        </div>
                        <img src="../image/price.jpg">
                    </div>

                </a>
                <div class="content">
                    <div class="header">Cenová</div>
                </div>
            </div>

            <div class="ui card">
                <a href="discount.jsp">
                    <div class="image">
                        <div class="ui blurring inverted dimmer">
                            <div class="content">
                                <div class="center">
                                </div>
                            </div>
                        </div>
                        <img src="../image/percent.jpg">
                    </div>

                </a>
                <div class="content">
                    <div class="header">Percentuálna</div>
                </div>
            </div>

            <div class="ui card">
                <a href="discount.jsp">
                    <div class="image">
                        <div class="ui blurring inverted dimmer">
                            <div class="content">
                                <div class="center">
                                </div>
                            </div>
                        </div>
                        <img src="../image/balance.jpg">
                    </div>

                </a>
                <div class="content">
                    <div class="header">Kvantitatívna</div>
                </div>
            </div>
    </div>
</div>
</body>
</html>
</jsp:attribute>
</mt:mainTamplate>
