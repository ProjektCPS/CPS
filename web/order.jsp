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
<head>
    <title>Title</title>
</head>
<body>
<div class="pozadie">
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
                    <a data-toggle="collapse" href="#login-form-wrap" aria-expanded="false"
                       aria-controls="login-form-wrap">Pokročilé
                        vyhľádavanie</a>
                    <i class="fa fa-arrow-down"></i>
                </div>
            </div>
        </div>

        <form id="login-form-wrap" class="login collapse" method="post">
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

    </div>
</div>
<div class="group-contajner ">
    <div class="col-md-12">
        <div class="column">
            <table class="ui celled selectable right aligned table">
                <thead>
                <th class="left aligned">Person</th>
                <th>Calories</th>
                <th>Fat</th>
                <th>Protein</th>
                </thead>
                <tbody>
                <tr>
                    <td class="left aligned">Tasia</td>
                    <td>12</td>
                    <td>7g</td>
                    <td>21g</td>
                </tr>
                <tr>
                    <td class="left aligned">Ronnie</td>
                    <td>38</td>
                    <td>37g</td>
                    <td>38g</td>
                </tr>
                <tr>
                    <td class="left aligned">Gabriel</td>
                    <td>30</td>
                    <td>46g</td>
                    <td>46g</td>
                </tr>
                <tr>
                    <td class="left aligned">Logan</td>
                    <td>12</td>
                    <td>21g</td>
                    <td>39g</td>
                </tr>
                <tr>
                    <td class="left aligned">Clare</td>
                    <td>39</td>
                    <td>41g</td>
                    <td>2g</td>
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

