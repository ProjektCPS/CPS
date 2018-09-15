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
<body>
<div class="ui form container p-t-15 p-b-15">

    <div class="ui toggle checkbox float-r">
        <label>Aktívny</label>
        <input type="checkbox" name="active" class="hidden">
    </div>

    <div class="group-contajner">
        <h3 class="ui dividing header">Základné informácie</h3>

        <div class="two fields">
            <div class="field">
                <label>Tenant ID / Užívateľ <i class="red-star">*</i></label>
                <input type="text" name="tenant-id" placeholder="Názov" value="${account != null ? account.uzivatel : ''}">
            </div>

            <div class="field">
                <label>Rola <i class="red-star">*</i></label>
                <div class="ui fluid search selection dropdown">
                    <input type="hidden" name="role" value="${account != null ? account.rola : ''}">
                    <i class="dropdown icon"></i>
                    <div class="default text">Vyberte rolu</div>
                    <div class="menu">
                        <div class="item" data-value="admin">Administrátor</div>
                        <div class="item" data-value="user">Užívateľ</div>
                    </div>
                </div>
            </div>
        </div>

        <div class="two fields">
            <div class="field">
                <label>Názov firmy <i class="red-star">*</i></label>
                <input type="text" name="company-name" placeholder="Názov firmy">
            </div>
            <div class="field">
                <label>IČO <i class="red-star">*</i></label>
                <input type="number" name="ICO" placeholder="IČO ">
            </div>
        </div>

        <div class="three fields">
            <div class="field">
                <label>Krstné meno <i class="red-star">*</i></label>
                <input type="text" name="fist-name" placeholder="Krstné meno" value="${person != null ? person.meno : ''}">
            </div>
            <div class="field">
                <label>Priezvisko <i class="red-star">*</i></label>
                <input type="text" name="last-name" placeholder="Priezvisko" value="${person != null ? person.priezvisko : ''}">
            </div>
            <div class="field">
                <label>Rodné číslo <i class="red-star">*</i></label>
                <input type="text" name="RC" placeholder="Rodné číslo " value="${person != null ? person.rodCislo : ''}">
            </div>
        </div>

        <div class="two fields">
            <div class="field">
                <label>E-mail <i class="red-star">*</i></label>
                <input type="email" name="email" placeholder="E-mail" value="${account != null ? account.email : ''}">
            </div>
            <div class="field">
                <label>Telefón</label>
                <input type="text" name="phone" placeholder="Telefón">
            </div>
        </div>
    </div>

    <div class="group-contajner p-t-0">
        <h3 class="ui dividing header">Adresa</h3>

        <div class="field">
            <label>Ulica <i class="red-star">*</i></label>
            <div class="two fields">
                <div class="twelve wide field">
                    <input type="text" name="billing_adress_1" placeholder="Ulica" value="${city != null ? city.ulica : ''}">
                </div>
                <div class="four wide field">
                    <input type="text" name="billing_adress_1" placeholder="Apartmán, suita, byt atď. (voliteľné)" value="${city != null ? city.cisloDomu : ''}">
                </div>
            </div>
        </div>

        <div class="two fields">
            <div class="field">
                <label>Mesto <i class="red-star">*</i></label>
                <input type="text" name="city" placeholder="Názov mesta / obce" value="${city != null ? city.nazov : ''}">
            </div>
            <div class="field">
                <label>PSČ <i class="red-star">*</i></label>
                <input type="number" name="postcode" placeholder="PSČ" value="${city != null ? city.psc : ''}">
            </div>
        </div>

        <div class="three fields">
            <div class="field">
                <label>Krajina <i class="red-star">*</i></label>
                <div class="ui fluid search selection dropdown">
                    <input type="hidden" name="country" value="${country != null ? country.nazov : ''}">
                    <i class="dropdown icon"></i>
                    <div class="default text">Vyber krajinu</div>
                    <div class="menu">
                        <div class="item" data-value="Czech Republic"><i class="cz flag"></i>Czech Republic</div>
                        <div class="item" data-value="Slovakia"><i class="sk flag"></i>Slovakia</div>
                    </div>
                </div>
            </div>
            <div class="field">
                <label>Kraj <i class="red-star">*</i></label>
                <div class="ui fluid search selection dropdown">
                    <input type="hidden" name="state"value="${state != null ? state.nazov : ''}">
                    <i class="dropdown icon"></i>
                    <div class="default text">Vyber kraj</div>
                    <div class="menu">
                        <div class="item" data-value="Banskobystrický">Banskobystrický</div>
                        <div class="item" data-value="Bratislavský">Bratislavský</div>
                        <div class="item" data-value="Košický">Košický</div>
                        <div class="item" data-value="Nitriansky">Nitriansky</div>
                        <div class="item" data-value="Prešovský">Prešovský</div>
                        <div class="item" data-value="Trenčiansky">Trenčiansky</div>
                        <div class="item" data-value="Trnavský">Trnavský</div>
                        <div class="item" data-value="Žilinský">Žilinský</div>
                    </div>
                </div>
            </div>
            <div class="field">
                <label>Okres <i class="red-star">*</i></label>
                <div class="ui fluid search selection dropdown">
                    <input type="hidden" name="region" value="${region != null ? region.nazov : ''}">
                    <i class="dropdown icon"></i>
                    <div class="default text">Vyber okres</div>
                    <div class="menu">
                        <div class="item" data-value="">Vyber okres</div>
                        <div class="item" data-value="Bánovce nad Bebravou">Bánovce nad Bebravou</div>
                        <div class="item" data-value="Banská Bystrica">Banská Bystrica</div>
                        <div class="item" data-value="Banská Štiavnica">Banská Štiavnica</div>
                        <div class="item" data-value="Bardejov">Bardejov</div>
                        <div class="item" data-value="Bratislava I">Bratislava I</div>
                        <div class="item" data-value="Bratislava II">Bratislava II</div>
                        <div class="item" data-value="Bratislava III">Bratislava III</div>
                        <div class="item" data-value="Bratislava IV">Bratislava IV</div>
                        <div class="item" data-value="Bratislava V">Bratislava V</div>
                        <div class="item" data-value="Brezno">Brezno</div>
                        <div class="item" data-value="Bytča">Bytča</div>
                        <div class="item" data-value="Čadca">Čadca</div>
                        <div class="item" data-value="Detva">Detva</div>
                        <div class="item" data-value="Dolný Kubín">Dolný Kubín</div>
                        <div class="item" data-value="Dunajská Streda">Dunajská Streda</div>
                        <div class="item" data-value="Galanta">Galanta</div>
                        <div class="item" data-value="Gelnica">Gelnica</div>
                        <div class="item" data-value="Hlohovec">Hlohovec</div>
                        <div class="item" data-value="Humenné">Humenné</div>
                        <div class="item" data-value="Ilava">Ilava</div>
                        <div class="item" data-value="Kežmarok">Kežmarok</div>
                        <div class="item" data-value="Komárno">Komárno</div>
                        <div class="item" data-value="Košice I">Košice I</div>
                        <div class="item" data-value="Košice II">Košice II</div>
                        <div class="item" data-value="Košice III">Košice III</div>
                        <div class="item" data-value="Košice IV">Košice IV</div>
                        <div class="item" data-value="Košice-okolie">Košice-okolie</div>
                        <div class="item" data-value="Krupina">Krupina</div>
                        <div class="item" data-value="Kysucké Nové Mesto">Kysucké Nové Mesto</div>
                        <div class="item" data-value="Levice">Levice</div>
                        <div class="item" data-value="Levoča">Levoča</div>
                        <div class="item" data-value="Liptovský Mikuláš">Liptovský Mikuláš</div>
                        <div class="item" data-value="Lučenec">Lučenec</div>
                        <div class="item" data-value="Malacky">Malacky</div>
                        <div class="item" data-value="Martin">Martin</div>
                        <div class="item" data-value="Medzilaborce">Medzilaborce</div>
                        <div class="item" data-value="Michalovce">Michalovce</div>
                        <div class="item" data-value="Myjava">Myjava</div>
                        <div class="item" data-value="Námestovo">Námestovo</div>
                        <div class="item" data-value="Nitra">Nitra</div>
                        <div class="item" data-value="Nové Mesto nad Váhom">Nové Mesto nad Váhom</div>
                        <div class="item" data-value="Nové Zámky">Nové Zámky</div>
                        <div class="item" data-value="Partizánske">Partizánske</div>
                        <div class="item" data-value="Pezinok">Pezinok</div>
                        <div class="item" data-value="Piešťany">Piešťany</div>
                        <div class="item" data-value="Poltár">Poltár</div>
                        <div class="item" data-value="Poprad">Poprad</div>
                        <div class="item" data-value="Považská Bystrica">Považská Bystrica</div>
                        <div class="item" data-value="Prešov">Prešov</div>
                        <div class="item" data-value="Prievidza">Prievidza</div>
                        <div class="item" data-value="Púchov">Púchov</div>
                        <div class="item" data-value="Revúca">Revúca</div>
                        <div class="item" data-value="Rimavská Sobota">Rimavská Sobota</div>
                        <div class="item" data-value="Rožňava">Rožňava</div>
                        <div class="item" data-value="Ružomberok">Ružomberok</div>
                        <div class="item" data-value="Sabinov">Sabinov</div>
                        <div class="item" data-value="Senec">Senec</div>
                        <div class="item" data-value="Senica">Senica</div>
                        <div class="item" data-value="Skalica">Skalica</div>
                        <div class="item" data-value="Snina">Snina</div>
                        <div class="item" data-value="Sobrance">Sobrance</div>
                        <div class="item" data-value="Spišská Nová Ves">Spišská Nová Ves</div>
                        <div class="item" data-value="Stará Ľubovňa">Stará Ľubovňa</div>
                        <div class="item" data-value="Stropkov">Stropkov</div>
                        <div class="item" data-value="Svidník">Svidník</div>
                        <div class="item" data-value="Šaľa">Šaľa</div>
                        <div class="item" data-value="Topoľčany">Topoľčany</div>
                        <div class="item" data-value="Trebišov">Trebišov</div>
                        <div class="item" data-value="Trenčín">Trenčín</div>
                        <div class="item" data-value="Trnava">Trnava</div>
                        <div class="item" data-value="Turčianske Teplice">Turčianske Teplice</div>
                        <div class="item" data-value="Tvrdošín">Tvrdošín</div>
                        <div class="item" data-value="Veľký Krtíš">Veľký Krtíš</div>
                        <div class="item" data-value="Vranov nad Topľou">Vranov nad Topľou</div>
                        <div class="item" data-value="Zlaté Moravce">Zlaté Moravce</div>
                        <div class="item" data-value="Zvolen">Zvolen</div>
                        <div class="item" data-value="Žarnovica">Žarnovica</div>
                        <div class="item" data-value="Žiar nad Hronom">Žiar nad Hronom</div>
                        <div class="item" data-value="Žilina">Žilina</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <div class="ui inverted blue button">Zrušiť</div>
        <div class="ui positive button">Uložiť</div>
    </div>

</div>
</body>
</html>
</jsp:attribute>
</mt:mainTamplate>
