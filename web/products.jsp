<%--
  Created by IntelliJ IDEA.
  User: Michal
  Date: 21.06.2018
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>
<mt:mainTamplate title="Produkty">
  <jsp:attribute name="content">
<html>
<head>
    <title>Produkty</title>
</head>
<body>
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
            <button class="ui labeled icon button">
                <div class="middle">
                    <i class="fa fa-plus"></i>
                    Pridaj
                </div>
            </button>
            <button class="ui labeled icon button">
                <div class="middle">
                    <i class="fa fa-minus"></i>
                    Uprav
                </div>
            </button>
            <button class="ui labeled icon button">
                <div class="middle">
                    <i class="fa fa-pencil"></i>
                    Odstran
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