<%--
  Created by IntelliJ IDEA.
  User: Bugy
  Date: 07.02.2020
  Time: 18:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>
<mt:mainTamplate title="Error">
  <jsp:attribute name="content">
<html>
<header>
</header>
<body>
<c:choose>
    <c:when test="${responseCode == 400}">
         <span>Nastala chyba</span><b> ${responseCode} </b><span>Skontrolujte requeset prosim.</span>
    </c:when>
    <c:when test="${responseCode == 401}">
         <span>Nastala chyba</span><b> ${responseCode} </b><span>Neautorizovaný request. Prihláste sa prosím</span>
    </c:when>
    <c:when test="${responseCode == 404}">
         <span>Nastala chyba</span><b> ${responseCode} </b><span>Je nam ľúto, ale pre vašú požiadavku sme nič nenašli.</span>
    </c:when>
    <c:otherwise>
        <span>Nastala chyba</span><b> ${responseCode} </b>
    </c:otherwise>
</c:choose>
</body>
</html>
</jsp:attribute>
</mt:mainTamplate>
