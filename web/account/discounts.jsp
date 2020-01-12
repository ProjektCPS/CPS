<%--
  Created by IntelliJ IDEA.
  User: Bugy
  Date: 11.01.2020
  Time: 15:04
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mt:mainTamplate title="Zlavy">
  <jsp:attribute name="content">
<html>
<header>
    <script src="../js/discounts/discount.js"></script>
</header>
<body>
<div class="search-background">
    <div class="group-contajner">
        <div class="row m-b-25">
            <div class="col-md-12">
                <h1>Vyhľadávanie</h1>
                <div class="ui action input searcher">
                    <input type="text" placeholder="Search...">
                    <button class="ui button black">
                        <i class="fa fa-search"></i>
                    </button>
                </div>
            </div>
        </div>

        <div class="row m-b-25">
            <div class="col-md-12">
                <div class="center-align">
                    <a data-toggle="collapse" href="#filter-background" aria-expanded="false"
                       aria-controls="filter-background">Pokročilé
                        vyhľádavanie</a>
                    <i class="fa fa-arrow-down"></i>
                </div>
            </div>
        </div>
    </div>
</div>
<form id="filter-background" class="collapse" method="post">
    <hr class="horizontal-separator">
    <div class="row m-b-30">
        <div class="col-md-3">
            <div class="ui selection dropdown">
                <input type="hidden" name="gender">
                <i class="dropdown icon"></i>
                <div class="default text">Typ zľavy</div>
                <div class="menu">
                    <div class="item" data-value="1">Male</div>
                    <div class="item" data-value="0">Female</div>
                </div>
            </div>
        </div>

        <div class="col-md-3 m-b-5">
            <div class="ui checkbox m-r-20">
                <input type="checkbox" name="example"/>
                <label>Aktívne</label>
            </div>

            <div class="ui checkbox">
                <input type="checkbox" name="example"/>
                <label>Neaktivne</label>
            </div>
        </div>

        <div class="col-md-3">

        </div>

        <div class="col-md-3">
        </div>
    </div>

    <div class="row">
        <div class="col-md-3">

        </div>

        <div class="col-md-3">
            <div class="container-button">
                <button class="ui labeled icon button border-button">
                    <div class="middle">
                        <i class="fa fa-search"></i>
                        Hľadaj
                    </div>
                </button>
            </div>
        </div>
        <div class="col-md-3">
            <div class="container-button">
                <button class="ui labeled icon button border-button">
                    <div class="middle">
                        <i class="fa fa-remove"></i>
                        Resetni
                    </div>
                </button>
            </div>
        </div>

        <div class="col-md-3">

        </div>
    </div>


</form>

<div class="group-contajner ">
    <div class="col-md-10">
        <div class="column">
            <table class="ui celled selectable right aligned table">
                <thead>
                <th class="left aligned">Typ zľavy</th>
                <th>${discountType == "price" ? "Hodnota zlavy" :
                        discountType == "percent" ? "Počet percent" :
                                discountType == "quantity" ?  "Množstvo" :
                                        discountType == "date" ? "Počet dní" : "Hodnota"}
                </th>
                <th>Dátum od</th>
                <th>Dátum do</th>
                </thead>
                <tbody>
                <c:forEach items="${discounts}" var="discount">
                <tr data-id="${discount.zlavaEntity.idZlavy}">
                    <td class="left aligned">${discount.typZlavyEntity.nazovTypu}</td>
                    <td>${discountType == "price" ? discount.cenovaZlavaEntity.hodnotaZlavy :
                            discountType == "percent" ? discount.percentualnaZlavaEntity.percentZlavy :
                                    discountType == "quantity" ?  discount.kvantitovaZlavaEntity.mnozstvo :
                                            discountType == "date" ? discount.datumovaZlavaEntity.den : "-"}</td>
                    <td>${discount.zlavaEntity.datOd}</td>
                    <td>${discount.zlavaEntity.datDo}</td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="col-md-2">
        <div class="container-button m-b-20">
            <button id="new-discount" class="ui labeled inverted green icon button  zelenac">
                <div class="middle">
                    <i class="fa fa-plus"></i>
                    Pridaj
                </div>
            </button>
            <button id="delete-discount" class="ui labeled inverted red icon button cervenak">
                <div class="middle">
                    <i class="fa fa-minus"></i>
                    Odstráň
                </div>
            </button>
            <button id="edit-discount" class="ui labeled inverted blue icon button belasa">
                <div class="middle">
                    <i class="fa fa-pencil"></i>
                    Edituj
                </div>
            </button>
        </div>
    </div>
</div>
</body>
</html>
</jsp:attribute>
</mt:mainTamplate>
