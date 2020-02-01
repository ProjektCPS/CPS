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
        <script src="../js/productCategories/categories.js"></script>
        <script src="../js/productCategories/categoryDicount.js"></script>
    </head>
    <body>
    <div id="allCategories" data-main-category-id="${categoryId}" class="container m-t-15">
        <div class="ui four stackable cards">
            <c:forEach items="${categories}" var="item">
                     <div class="ui card">
                         <div class="flex-row">
                             <a href="products?categoryName=${item.nazov}">
                                 <div class="image">
                                     <div class="ui blurring inverted dimmer">
                                         <div class="content">
                                             <div class="center">
                                             </div>
                                         </div>
                                     </div>
                                     <img src="../image/${item.nazov}.jpg"
                                          onerror="this.onerror=null;this.src='../image/placeholder-image.jpg';">
                                 </div>
                             </a>
                             <div class="flex-col actions">
                                 <div class="action">
                                     <button class="circular ui google plus icon button delete-category">
                                         <i class="fa fa-trash"></i>
                                     </button>
                                 </div>
                                 <div class="action">
                                     <button data-product-category-id="${item.idKategorie}"
                                             class="circular ui edit icon button edit-category">
                                         <i class="fa fa-edit"></i>
                                     </button>
                                 </div>
                                 <div class="action">
                                     <button data-product-category-id="${item.idKategorie}"
                                             class="circular ui linkedin icon button category-discount-edit">
                                         <i class="fa fa-percent"></i>
                                     </button>
                                 </div>
                             </div>
                         </div>
                         <div class="content">
                             <div class="header"> ${item.nazov}</div>
                         </div>
                     </div>
               <br>
            </c:forEach>

            <div class="ui card">
                <a id="new-category">
                    <div class="image">
                        <div class="ui blurring inverted dimmer">
                            <div class="content">
                                <div class="center">
                                    <div class="ui teal button">Pridaj kategoriu</div>
                                </div>
                            </div>
                        </div>
                        <img src="../image/add.jpg">
                    </div>
                </a>
                <div class="content">
                    <div class="header">Pridaj kategoriu</div>
                </div>
            </div>
        </div>
    </div>

        <%--modals--%>
    <div id="delete-category-modal" class="ui basic modal customModal">
        <div class="header">
            Odstrániť hlavnú kategoriu
        </div>
        <div class="content">
            <p>Naozaj chcete odstrániť vybranú kategóriu?</p>
        </div>
        <div class="actions">
            <div class="ui red basic cancel inverted button">
                <i class="fa fa-sign-out"></i>
                Nie
            </div>
            <div class="ui green ok inverted button">
                <i class="fa fa-trash"></i>
                Áno
            </div>
        </div>
    </div>

    <div id="category-modal" class="ui mini modal customModal">
        <div class="header">
            Hlavná kategória
        </div>
        <div class="ui form content">
            <div class="one field">
                <div class="field required">
                    <label>Názov</label>
                    <input type="text" name="category-name" placeholder="Názov kategórie"
                           value="">
                </div>
            </div>
        </div>
        <div class="actions">
            <div class="ui black cancel button">
                <i class="fa fa-sign-out"></i>
                Zrušiť
            </div>
            <div class="ui positive ok right button">
                <i class="fa fa-check"></i>
                Uložiť
            </div>
        </div>
        <input hidden type="text" name="category-id" value="">
    </div>

    <div id="category-discount-modal" class="ui modal customModal overflow-v">
        <div class="header">
            Zľavy
        </div>
        <div class="ui form content">
            <div class="one field">
                <div class="field required">
                    <label>Typ zľavy</label>
                    <div class="ui fluid search selection dropdown">
                        <input type="hidden" name="mainDiscountType" value="">
                        <i class="dropdown icon"></i>
                        <div class="default text">Vyber typ zľavy</div>
                    </div>
                </div>
            </div>
            <div class="container-fluid">
                <div class="row dis-flex">
                    <div class="col-md-9 p-l-0">
                        <table id="discounts-table" class="ui celled selectable right aligned table w-full-i">
                            <thead>
                            <th>hidden column</th>
                            <th class="left aligned">Názov</th>
                            <th>Hodnota</th>
                            <th>Dátum od</th>
                            <th>Dátum do</th>
                            <th>Typ zľavy</th>
                            </thead>
                        </table>
                    </div>
                    <div class="col-md-3 p-r-0 flex-c-t">
                        <div class="container-button m-t-78">
                            <button id="apply-discount" class="ui labeled inverted green icon button  zelenac">
                                <div class="middle">
                                    <i class="fa fa-plus"></i>
                                    Pridaj
                                </div>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div id="applied-discounts">
            </div>
        </div>
        <div class="actions">
            <div class="ui black cancel button">
                <i class="fa fa-sign-out"></i>
                Zrušiť
            </div>
            <div class="ui positive ok right button">
                <i class="fa fa-check"></i>
                Uložiť
            </div>
        </div>
        <input hidden type="text" name="category-id" value="">
    </div>

    </body>
    </html>
  </jsp:attribute>
</mt:mainTamplate>

