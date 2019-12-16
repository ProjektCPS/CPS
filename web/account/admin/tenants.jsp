<%--
  Created by IntelliJ IDEA.
  User: Bugy
  Date: 15.12.2019
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>
<mt:mainTamplate title="Tenanti">
  <jsp:attribute name="content">
<html>
<head>
    <script src="../../js/tenants/tenants.js"></script>
</head>
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
        <div class="col-md-3 m-b-10">
            <div class="ui selection dropdown">
                <input type="hidden" name="gender">
                <i class="dropdown icon"></i>
                <div class="default text">Značka</div>
                <div class="menu">
                    <div class="item" data-value="1">Male</div>
                    <div class="item" data-value="0">Female</div>
                </div>
            </div>
        </div>

        <div class="col-md-3 m-b-5">
            <div class="ui selection dropdown">
                <input type="hidden" name="gender">
                <i class="dropdown icon"></i>
                <div class="default text">Cena</div>
                <div class="menu">
                    <div class="item" data-value="1">100- 200</div>
                    <div class="item" data-value="0">200 a viac</div>

                </div>
            </div>
        </div>

        <div class="col-md-3">
            <div class="ui checkbox m-r-20">
                <input type="checkbox" name="example"/>
                <label>Zľavnené</label>
            </div>

            <div class="ui checkbox">
                <input type="checkbox" name="example"/>
                <label>Nezľavnené</label>
            </div>
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
            <table id="tenants-table" class="ui celled selectable right aligned table">
                <thead>
                <th class="left aligned">Tenant id</th>
                <th class="left aligned">Nazov</th>
                </thead>
                <tbody>
                <c:forEach items="${tenants}" var="tenant">
                <tr data-id="${tenant.tenantId}">
                    <td class="left aligned">${tenant.tenantId}</td>
                    <td class="left aligned">${tenant.nazov}</td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="col-md-2">
        <div class="container-button m-b-20">
            <button id="new-tenant" class="ui labeled inverted green icon button  zelenac">
                <div class="middle">
                    <i class="fa fa-plus"></i>
                    Pridaj
                </div>
            </button>
            <button id="delete-tenant" class="ui labeled inverted red icon button cervenak">
                <div class="middle">
                    <i class="fa fa-minus"></i>
                    Odstráň
                </div>
            </button>
            <button id="edit-tenant" class="ui labeled inverted blue icon button belasa">
                <div class="middle">
                    <i class="fa fa-pencil"></i>
                    Edituj
                </div>
            </button>

            </button>
        </div>

    </div>
</div>

<div id="tenant-modal" class="ui modal customModal">
    <div class="header">
        Tenant
    </div>
    <div class="ui form content">
        <div class="two fields">
            <div class="field">
                <label>Tenant id</label>
                <input disabled type="text" name="tenant-id" placeholder="Tenant id"
                       value="automaticky generovany">
            </div>
            <div class="field required">
                <label>Názov</label>
                <input type="text" name="tenant-name" placeholder="Názov tenanta"
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
</div>
<div id="delete-modal" class="ui basic modal customModal">
    <div class="header">
        Delete tenant
    </div>
    <div class="content">
        <p>Naozaj chcete odstranit tenanta?</p>
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
</body>
</html>
</jsp:attribute>
</mt:mainTamplate>
