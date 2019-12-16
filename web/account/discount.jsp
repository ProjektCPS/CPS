<%--
  Created by IntelliJ IDEA.
  User: Michal
  Date: 22.06.2018
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>
<mt:mainTamplate title="Zlavy">
  <jsp:attribute name="content">
<html>
<header>
    <script src="../js/discounts/dicount.js"></script>
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

<div class="col-md-10">
    <div class="column">
        <table class="ui celled selectable right aligned table">
            <thead>
            <th class="left aligned">ID</th>
            <th>Typ zľavy</th>
            <th>Hodnota</th>
            <th>Validná</th>
            </thead>
            <tbody>
            <tr>
                <td class="left aligned">1</td>
                <td>cenova</td>
                <td>10</td>
                <td>ano</td>
            </tr>
            <tr>
                <td class="left aligned">3</td>
                <td>cenova</td>
                <td>25</td>
                <td>ano</td>
            </tr>
            <tr>
                <td class="left aligned">4</td>
                <td>cenova</td>
                <td>50</td>
                <td>ano</td>
            </tr>
            <tr>
                <td class="left aligned">5</td>
                <td>cenova</td>
                <td>20</td>
                <td>ano</td>
            </tr>
            <tr>
                <td class="left aligned">6</td>
                <td>cenova</td>
                <td>6</td>
                <td>nie</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<div class="col-md-2">
    <div class="container-button">
        <button class="ui labeled inverted green icon button zelenac">
            <div class="middle">
                <i class="fa fa-plus"></i>
                Pridaj
            </div>
        </button>
        <button class="ui labeled inverted red icon button cervenak">
            <div class="middle">
                <i class="fa fa-minus"></i>
                Odstran
            </div>
        </button>
        <button class="ui labeled inverted blue icon button belasa">
            <div class="middle">
                <i class="fa fa-pencil"></i>
                Edituj
            </div>
        </button>

        </button>
    </div>

</div>
</body>
</html>
</jsp:attribute>
</mt:mainTamplate>