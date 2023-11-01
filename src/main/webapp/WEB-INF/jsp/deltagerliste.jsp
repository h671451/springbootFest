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
<p>Innlogget som: ${loggedInUser.mobil} / ${loggedInUser.fornavn} ${loggedInUser.etternavn}</p>
	<h2>Deltagerliste</h2>
	<table>
        <tr>
            <th>Kjønn</th>
            <th align="left">Navn</th>
            <th align="left">Mobil</th>
        </tr>

        <c:forEach var="deltager" items="${deltagers}">
            <tr <c:if test="${deltager.mobil == loggedInMobil}">style="background-color:#aaffaa;"</c:if>>
                <td align="center">${deltager.kjonn == 'FEMALE' ? '&#9792;' : '&#9794;'}</td>
                <td>${deltager.navn}</td>
                <td>${deltager.mobil}</td>
            </tr>
        </c:forEach>

	</table>
	<br>
	<form action="/innlogging" method="post">
	   <button type="submit">Logg ut</button>
	</form>
</body>
</html>
