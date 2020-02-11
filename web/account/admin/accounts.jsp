<%--
  Created by IntelliJ IDEA.
  User: Bugy
  Date: 25.08.2018
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>
<mt:mainTamplate title="Admin účty">
  <jsp:attribute name="content">
<html>
<header>
    <script src="../../js/admin/accounts.js"></script>
</header>
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
        <div class="column hiddenSearchBox">
            <table id="accounts-table" class="ui celled selectable right aligned table">
                <thead>
                <th class="left aligned">Tenant id</th>
                <th>Email</th>
                <th>Rola</th>
                <th>Aktívny</th>
                </thead>
                <tbody>
                <c:forEach items="${accountsList}" var="accounts">
                <tr data-id="${accounts.idAdmin}">
                    <td class="left aligned">${accounts.tenantId}</td>
                    <td>${accounts.email}</td>
                    <td>${accounts.rola}</td>
                    <td>
                        <c:choose>
                            <c:when test="${accounts.active == 1}">
                                <i class="fa fa-check" style="color:green;"></i>
                            </c:when>
                            <c:when test="${accounts.active == 0}">
                                <i class="fa fa-ban" style="color:red;"></i>
                            </c:when>
                            <c:otherwise>
                                <i class="fa fa-ban" style="color:red;"></i>
                             </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="col-md-2">
        <div class="container-button m-b-20">
            <a href="./account">
                <button class="ui labeled inverted green icon button  zelenac">
                    <div class="middle">
                        <i class="fa fa-plus"></i>
                        Pridaj
                    </div>
                </button>
            </a>
            <button id="delete-account" class="ui labeled inverted red icon button cervenak">
                <div class="middle">
                    <i class="fa fa-minus"></i>
                    Odstráň
                </div>
            </button>
            <button id="edit-account" class="ui labeled inverted blue icon button belasa">
                <div class="middle">
                    <i class="fa fa-pencil"></i>
                    Edituj
                </div>
            </button>

            </button>
        </div>

    </div>
</div>
</body>
</html>
</jsp:attribute>
</mt:mainTamplate>
