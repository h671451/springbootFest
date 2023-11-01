<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link href="/simple.css" rel="stylesheet" type="text/css" />
<title>Logg inn</title>
</head>
<body>
	<h2>Logg inn</h2>

	    <!-- Display dynamic messages based on the scenario -->
        <c:choose>
            <c:when test="${not empty errorMessage}">
                <p style="color:red;">${errorMessage}</p>
            </c:when>
            <c:when test="${not empty successMessage}">
                <p style="color:green;">${successMessage}</p>
            </c:when>
        </c:choose

	<p style="color:red;">Du er logget ut</p>
	<form method="post" action="/deltagerliste">
		<fieldset>
			<label for="mobil">Mobil:</label>
			 <input type="text" name="mobil"  />

			<label for="passord">Passord:</label>
			<input type="password" name="passord" />

			<br><br><button type="submit">Logg inn</button>
		</fieldset>
	</form>

</body>
</html>