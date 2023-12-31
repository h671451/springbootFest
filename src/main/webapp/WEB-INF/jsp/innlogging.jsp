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

    <c:if test="${not empty error}">
        <div>
            ${error}
        </div>
    </c:if>

<c:if test="${not empty loggedOut}">
    <p style="color:red;">Du er logget ut</p>
</c:if>

	<form method="post" action="/innlogging">
		<fieldset>
			<label for="mobil">Mobil:</label>
			 <input type="text" name="mobil"  value =""/>

			<label for="passord">Passord:</label>
			<input type="password" name="passord" value =""/>

			<br><br><button type="submit">Logg inn</button>
		</fieldset>
	</form>

</body>
</html>