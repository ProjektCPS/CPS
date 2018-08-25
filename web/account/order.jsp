<%--
  Created by IntelliJ IDEA.
  User: Michal
  Date: 25.06.2018
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>
<mt:mainTamplate title="Objednavky">
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
                <div class="center-align dropdown">
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
                        <div class="default text">Stav</div>
                        <div class="menu">
                            <div class="item" data-value="1">Male</div>
                            <div class="item" data-value="0">Female</div>
                        </div>
                    </div>
                </div>

                <div class="col-md-3 m-b-10">
                    <div class="ui selection dropdown">
                        <input type="hidden" name="gender">
                        <i class="dropdown icon"></i>
                        <div class="default text">Cena</div>
                        <div class="menu">
                            <div class="item" data-value="1">Male</div>
                            <div class="item" data-value="0">Female</div>
                        </div>
                    </div>
                </div>

                <div class="col-md-3 m-b-10">
                    <div class="ui selection dropdown">
                        <input type="hidden" name="gender">

                        <div class="default text">Kupujúci</div>
                    </div>

                </div>

                <div class="col-md-3">
                    <div class="ui checkbox m-r-20">
                        <input type="checkbox" name="example"/>
                        <label>Darček</label>
                    </div>
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
    <div class="col-md-12">
        <div class="column">
            <table class="ui celled selectable right aligned table">
                <thead>
                <th class="left aligned">Id</th>
                <th>stav</th>
                <th>pocet poloziek</th>
                <th>zlava</th>
                <th>cena</th>
                </thead>
                <tbody>
                <tr>
                    <td class="left aligned">2</td>
                    <td>vyfakturovana</td>
                    <td>2</td>
                    <td>0</td>
                    <td>100</td>
                </tr>

                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
</jsp:attribute>
</mt:mainTamplate>

