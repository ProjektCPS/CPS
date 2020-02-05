<%--
  Created by IntelliJ IDEA.
  User: Bugy
  Date: 13.09.2018
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>
<mt:mainTamplate title="Detail účtu">
  <jsp:attribute name="content">
<html>
<header>
    <script src="../js/products/AddOrUpdateProduct.js"></script>
</header>
<body>
<div class="ui form container m-t-15 p-t-15 p-b-15">

    <div class="group-contajner m-t-0 p-t-0">
        <h3 class="ui dividing header">Pridanie produktu</h3>
        <div class="two fields">
            <div class="field required">
                <label>Názov produktu</label>
                <input type="text" name="name" placeholder="Názov"
                       value="${account != null ? account.uzivatel : ''}">
            </div>

            <div class="field required">
                <label>Jednotka</label>
                <div class="ui fluid search selection dropdown">
                    <input type="hidden" name="unit" value="${account != null ? account.tenantId : ''}">
                    <i class="dropdown icon"></i>
                    <div class="default text">ks</div>
                    <div class="menu">
                        <div class="item" data-value="ks">ks</div>
                        <div class="item" data-value="m">m</div>
                        <div class="item" data-value="kg">kg</div>
                        <div class="item" data-value="g">g</div>
                    </div>
                </div>
            </div>
        </div>

        <div class="two fields">
            <div class="field">
                <label>Značka</label>
                <input type="text" name="brand" placeholder="Názov firmy">
            </div>
            <div class="field required">
                <label>Cena</label>
                <input type="number" name="price" placeholder="50 ">
            </div>
        </div>

        <div class="two fields">
            <div class="field">
                <label>Seriové číslo</label>
                <input type="text" name="serial-number" placeholder="Seriove čislo"
                       value="${person != null ? person.meno : ''}">
            </div>
            <div class="field">
                <label>Datum expirácie</label>
                <input type="text" name="date-expiracion" placeholder="01.01.2020"
                       value="${person != null ? person.priezvisko : ''}">
            </div>
        </div>

        <label>Description</label>
        <div class="fields">
            <input type="text" name="description" placeholder="Detailne popiš svoj product"
                   value="${account != null ? account.email : ''}">
        </div>
    </div>

    <div class="modal-footer">
            <div id="discard" class="ui inverted blue button">Zrušiť</div>
        <div id="product-save" class="ui positive button">Uložiť</div>
    </div>
</div>
</body>
</html>
</jsp:attribute>
</mt:mainTamplate>
