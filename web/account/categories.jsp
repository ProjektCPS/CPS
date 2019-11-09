<%--
  Created by IntelliJ IDEA.
  User: Bugy
  Date: 20.06.2018
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>
<mt:mainTamplate title="Produkty">
  <jsp:attribute name="content">
    <html>
    <head>
        <!---Javascript -->
        <script src="../js/productCategories/main.js"></script>
        <script src="../js/productCategories/categories.js"></script>
    </head>
    <body>
        <div class="container m-t-15">
            <div class="ui four stackable cards">
            <c:forEach items="${categories}" var="item">
                     <div id="categorie" class="ui card">
                         <a href="products?categoryName=${item}">
                             <div class="image">
                                 <div class="ui blurring inverted dimmer">
                                     <div class="content">
                                         <div class="center">
                                         </div>
                                     </div>
                                 </div>
                                 <img src="../image/${item}.jpg"
                                      onerror="this.onerror=null;this.src='../image/placeholder-image.jpg';">
                             </div>

                         </a>
                         <div class="content">
                             <div class="header"> ${item}</div>
                         </div>
                     </div>
               <br>
            </c:forEach>

                <div class="ui card">
                    <a href="https://www.facebook.com/">
                        <div class="image">
                            <div class="ui blurring inverted dimmer">
                                <div class="content">
                                    <div class="center">
                                        <div class="ui teal button">Pridaj kategoriu</div>
                                    </div>
                                </div>
                            </div>
                            <img src="../image/plus.jpg">
                        </div>
                    </a>
                    <div class="content">
                        <div class="header">Pridaj kategoriu</div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    </html>
  </jsp:attribute>
</mt:mainTamplate>

