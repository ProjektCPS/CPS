<%--
  Created by IntelliJ IDEA.
  User: Michal
  Date: 21.06.2018
  Time: 14:25
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
    <script src="../js/products/main.js"></script>
    <script src="../js/products/productDiscounts.js"></script>
</head>
<body>
<div class="search-background">
    <div class="group-contajner">
        <div class="row m-b-25">
            <div class="col-md-12">
                <h1>Vyhľadávanie</h1>
                <div class="ui action input searcher">
                    <input type="text" placeholder="Vyhľadaj...">
                    <button class="ui button black">
                        <i class="fa fa-search"></i>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="group-contajner ">
    <div class="col-md-10">
        <c:choose>
            <c:when test="${appliedCategoryDiscountsTypes != '[]'}">
                <h3 class="ui header">Applikované zľavy katategórie</h3>
                <div class="ui section m-b-8">
                     <c:forEach items="${appliedCategoryDiscountsTypes}" var="appliedDiscountCategory">
                         <c:choose>
                            <c:when test="${appliedDiscountCategory == 'percent'}">
                                 <a class="ui red tag label">
                                     <i class="fa fa-percent"></i>
                                 </a>
                            </c:when>
                            <c:when test="${appliedDiscountCategory == 'price'}">
                                 <a class="ui red tag label">
                                     <i class="fa fa-euro"></i>
                                 </a>
                            </c:when>
                            <c:when test="${appliedDiscountCategory == 'quantity'}">
                                 <a class="ui red tag label">
                                     <i class="fa fa-balance-scale"></i>
                                 </a>
                            </c:when>
                         </c:choose>
                     </c:forEach>
                </div>
            </c:when>
        </c:choose>
    </div>
    <div class="col-md-10">
        <div class="column hiddenSearchBox">
            <table id="products-table" class="ui celled selectable right aligned table">
                <thead>
                <th class="left aligned">Nazov</th>
                <th>Značka</th>
                <th>Seriové číslo</th>
                <th>Jednotka</th>
                <th>Cena</th>
                <th>Cena po aplikovani zliav</th>
                <th class="center aligned">Zľavy produktu</th>
                </thead>
                <tbody>
                <c:forEach items="${productsItems}" var="product">
                <tr data-id=${product.product.idPredmetu} data-original-price=${product.product.cena}
                    data-price-after-discounts=${product.afterDiscountsPrice}>
                    <td class="left aligned">${product.product.nazov}</td>
                    <td>${product.product.znacka}</td>
                    <td>${product.product.serioveCislo}</td>
                    <td>${product.product.jednotka}</td>
                    <td>${product.product.cena}</td>
                    <td>${product.afterDiscountsPriceNonNegative}</td>
                    <td class="center aligned">
                    <c:forEach items="${product.appliedDiscountTypes}" var="item">
                         <c:choose>
                                <c:when test="${item == 'percent'}">
                                     <a class="ui black tag label">
                                         <i class="fa fa-percent"></i>
                                     </a>
                                </c:when>
                                <c:when test="${item == 'price'}">
                                     <a class="ui black tag label">
                                         <i class="fa fa-euro"></i>
                                     </a>
                                </c:when>
                                <c:when test="${item == 'quantity'}">
                                     <a class="ui black tag label">
                                         <i class="fa fa-balance-scale"></i>
                                     </a>
                                </c:when>
                         </c:choose>
                    </c:forEach>
                    </td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="col-md-2">
        <div class="container-button m-b-20">
            <a href="./product?productCategoryId=${productCategoryId}">
                <button class="ui labeled inverted green icon button  zelenac">
                    <div class="middle">
                        <i class="fa fa-plus"></i>
                        Pridaj
                    </div>
                </button>
            </a>
            <button class="ui labeled red inverted icon button cervenak">
                <div class="middle">
                    <i class="fa fa-minus"></i>
                    Odstran
                </div>
            </button>
            <button id="edit-product" class="ui labeled blue inverted icon button belasa">
                <div class="middle">
                    <i class="fa fa-plus"></i>
                    Edituj
                </div>
            </button>
            <button id="edit-discounts" class="ui labeled blue inverted icon button belasa">
                <div class="middle">
                    <i class="fa fa-percent"></i>
                    Zľavy
                </div>
            </button>
            </button>
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

<div id="product-discount-modal" class="ui modal customModal overflow-v">
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
        <div class="container-fluid">
            <div class="row dis-flex">
                <div class="col-md-9 p-l-0">
                    <div>
                         <c:choose>
                            <c:when test="${appliedCategoryDiscountsTypes != '[]'}">
                                <h4 class="ui header">Applikované zľavy katategórie</h4>
                                <div class="ui section divider"></div>
                            </c:when>
                         </c:choose>
                        <c:forEach items="${appliedCategoryDiscounts}" var="appliedDiscountCategory">
                            <a class="ui label red customTag">
                                <i>${appliedDiscountCategory.typZlavyEntity.nazovTypu}</i>
                                <i> - </i>
                                <i>${appliedDiscountCategory.cenovaZlavaEntity != null ? appliedDiscountCategory.cenovaZlavaEntity.hodnotaZlavy :
                                        appliedDiscountCategory.percentualnaZlavaEntity != null ? appliedDiscountCategory.percentualnaZlavaEntity.percentZlavy :
                                                appliedDiscountCategory.kvantitovaZlavaEntity != null ? appliedDiscountCategory.kvantitovaZlavaEntity.mnozstvo :
                                                        appliedDiscountCategory.datumovaZlavaEntity != null ? appliedDiscountCategory.datumovaZlavaEntity.den : "-"}</i>
                                <i> ( </i>
                                <i>${appliedDiscountCategory.cenovaZlavaEntity != null ? "Hodnota zlavy" :
                                        appliedDiscountCategory.percentualnaZlavaEntity != null ? "Počet percent" :
                                                appliedDiscountCategory.kvantitovaZlavaEntity != null ?  "Množstvo" :
                                                        appliedDiscountCategory.datumovaZlavaEntity != null ? "Počet dní" : "Hodnota"}</i>
                                <i> ) </i>
                            </a>
                         </c:forEach>
                    </div>
                    <h4 class="ui header">Applikované zľavy produktu</h4>
                    <div id="applied-discounts">
                    </div>
                </div>
                <div class="col-md-3 p-r-0 flex-c-m">
                    <span class="fs-50"><i id="price-after-discounts">-</i><i> €</i></span>
                </div>
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
    <input hidden type="text" name="product-id" value="">
</div>
</body>
</html>
</jsp:attribute>
</mt:mainTamplate>
