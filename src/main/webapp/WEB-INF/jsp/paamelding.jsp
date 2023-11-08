<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="no">
<head>
	<meta charset="UTF-8"> <!-- This line ensures UTF-8 encoding -->
	<link href="/simple.css" rel="stylesheet" type="text/css" />
<!-- <script src="js/myscript.js" defer></script>  -->  
	<title>Påmelding</title>
</head>

<body>
	<h2>Påmelding</h2>
	    <c:if test="${not empty error}">
            <div>
                <p style="color:red;"> ${error} </p>
            </div>
        </c:if>
            <c:if test="${not empty feilmeldinger}">
                <div>
                    <p style="color:red;" ${feilmeldinger}></p>
                </div>
            </c:if>


	<form method="post" action="/paamelding" >
		<fieldset>
		
            <label>Fornavn</label>
            <input type="text" name="fornavn" value=""  required />


            <label>Etternavn</label>
            <input type="text" name="etternavn" value="" required />


            <label>Mobil (8 siffer)</label>
            <input type="text" name="mobil" value=""  required />


            <label>Passord</label>
            <input type="password" name="passord" value = "" minlength="4" required/>

            <label>Passord repetert</label>
            <input type="password" name="repetertpassord" value ="" minlength="4" required />


			<label>Kjønn</label> 
			<input type="radio" name="kjonn" value="MALE" checked="checked" />mann
			<input type="radio" name="kjonn" value="FEMALE" />kvinne
			     
			<br><br><button type="submit">Meld meg på</button>
		</fieldset>
	</form>


</body>
</html>
