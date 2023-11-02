<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="/simple.css" rel="stylesheet" type="text/css" />
	<title>Deltagerliste</title>
</head>
<body>
<p>Innlogget som: ${loggedInDeltager.mobil} / ${loggedInDeltager.fornavn} ${loggedInDeltager.etternavn}</p>
	<h2>Deltagerliste</h2>
	<table>
        <tr>
            <th>Kjønn</th>
            <th align="left">Navn</th>
            <th align="left">Mobil</th>
        </tr>

<c:forEach var="deltager" items="${deltagers}">
    <c:choose>
        <c:when test="${deltager.mobil == loggedInDeltager.mobil}">
            <tr style= "background-color:#aaffaa" >
                <td align="center">${deltager.kjonn == 'FEMALE' ? '&#9792;' : '&#9794;'}</td>
                <td>${deltager.fornavn} ${deltager.etternavn}</td>
                <td>${deltager.mobil}</td>
            </tr>
        </c:when>
        <c:otherwise>
            <tr>
                <td align="center">${deltager.kjonn == 'FEMALE' ? '&#9792;' : '&#9794;'}</td>
                <td>${deltager.fornavn} ${deltager.etternavn}</td>
                <td>${deltager.mobil}</td>
            </tr>
        </c:otherwise>
    </c:choose>
</c:forEach>


	</table>
	<br>
	<form action="/logout" method="POST">
	   <button type="submit">Logg ut</button>
	</form>
</body>
</html>
