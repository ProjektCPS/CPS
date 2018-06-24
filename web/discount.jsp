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
<head>
    <title>Zľavy</title>
</head>
<body>

<div class="group-contajner border">
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
                <input type="checkbox" name="example" />
                <label>Aktívne</label>
            </div>

            <div class="ui checkbox">
                <input type="checkbox" name="example" />
                <label>Neaktivne</label>
            </div>
        </div>

        <div class="col-md-3">

        </div>

        <div class="col-md-3">
            <div class="item">
                <div class="ui icon input">
                    <input type="text" placeholder="Search...">
                </div>
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

    </div>


    <div class="group-contajner">
        <div class="col-md-10">
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
        <div class="col-md-2">
            <div class="container-button">
                <button class="ui labeled icon button zelenac">
                    <div class="middle">
                        <i class="fa fa-plus"></i>
                        Pridaj
                    </div>
                </button>
                <button class="ui labeled icon button cervenak">
                    <div class="middle">
                        <i class="fa fa-minus"></i>
                        Odstran
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