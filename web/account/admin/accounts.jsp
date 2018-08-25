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
<mt:mainTamplate title="Účty">
  <jsp:attribute name="content">
<html>
<body>
<div class="search-background">
    <div class="group-contajner">
        <div class="row m-b-25">
            <div class="col-md-12">
                <h1>Vyhľadávanie</h1>
                <div class="ui action input searcher">
                    <input type="text" placeholder="Search...">
                    <button class="ui button belasa">
                        <i class="fa fa-search"></i>
                    </button>
                </div>
            </div>
        </div>

        <div class="row m-b-25">
            <div class="col-md-12">
                <div class="center-align">
                    <a data-toggle="collapse" href="#filer-background" aria-expanded="false"
                       aria-controls="filer-background">Pokročilé
                        vyhľádavanie</a>
                    <i class="fa fa-arrow-down"></i>
                </div>
            </div>
        </div>

    </div>
</div>
<form id="filer-background" class="collapse" method="post">
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
            <table class="ui celled selectable right aligned table">
                <thead>
                <th class="left aligned">Meno užívateľa / tenant id</th>
                <th>Email</th>
                <th>Rola</th>
                <th>Aktívny</th>
                </thead>
                <tbody>
                <c:forEach items="${accountsList}" var="accounts">
                <tr>
                    <td class="left aligned">${accounts.uzivatel}</td>
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
            <button class="ui labeled icon button  zelenac">
                <div class="middle">
                    <i class="fa fa-plus"></i>
                    Pridaj
                </div>
            </button>
            <button class="ui labeled icon button cervenak">
                <div class="middle">
                    <i class="fa fa-minus"></i>
                    Odstráň
                </div>
            </button>
            <button class="ui labeled icon button belasa">
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