<%--
  Created by IntelliJ IDEA.
  User: Bugy
  Date: 16.12.2019
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>
<mt:mainTamplate title="Profil">
  <jsp:attribute name="content">
<html>
<head>
    <head>
        <script src="../js/profile/profile.js"></script>
    </head>
</head>
<body>
<div class="group-contajner">
    <div class="container">
        <div class="col-md-6">
            <h2 class="ui header">
                <div class="content">
                    <i class="fa fa-2x fa-object-group"></i>
                    Hlavné kategórie
                </div>
            </h2>
            <div class="col-md-8">
                <div class="column">
                    <table id="main-categories-table" class="ui celled selectable right aligned table">
                        <thead>
                        <th class="center aligned">Názov hlavnej kategórie</th>
                        </thead>
                        <tbody>
                        <c:forEach items="${mainCategories}" var="mainCategory">
                <tr data-id="${mainCategory.idTypu}">
                    <td class="center aligned">${mainCategory.nazov}</td>
                </tr>
                </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-md-4">
                <div class="container-button m-b-20">
                    <button id="new-main-category" class="ui labeled inverted green icon button  zelenac">
                        <div class="middle">
                            <i class="fa fa-plus"></i>
                            Pridaj
                        </div>
                    </button>
                    <button id="delete-main-category" class="ui labeled inverted red icon button cervenak">
                        <div class="middle">
                            <i class="fa fa-minus"></i>
                            Odstráň
                        </div>
                    </button>
                    <button id="edit-main-category" class="ui labeled inverted blue icon button belasa">
                        <div class="middle">
                            <i class="fa fa-pencil"></i>
                            Edituj
                        </div>
                    </button>

                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="main-category-modal" class="ui mini modal customModal">
    <div class="header">
        Hlavná kategória
    </div>
    <div class="ui form content">
        <div class="one field">
            <div class="field required">
                <label>Názov</label>
                <input type="text" name="main-category-name" placeholder="Názov kategórie"
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
    <input hidden type="text" name="main-category-id" value="">
</div>
<div id="delete-main-category-modal" class="ui basic modal customModal">
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
</body>
</html>
</jsp:attribute>
</mt:mainTamplate>
