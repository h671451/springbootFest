<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
<c:if test="${bindingResult.hasErrors()}">
   <p style="color:red;"></p>
</c:if>
	<form method="post" action="/paamelding" onsubmit="return validatePassword();">
		<fieldset>
		
			<label>Fornavn</label>
			<input type="text" name="fornavn" value="${Deltager.fornavn}"  pattern="^[A-Z][a-zA-ZæøåÆØÅ- ]{1,19}$" required />
			<c:if test="${bindingResult.hasFieldErrors('fornavn')}">
               <span style="color:red;">${bindingResult.getFieldError('fornavn').defaultMessage}</span>
            </c:if>
			
			<label>Etternavn</label>
			<input type="text" name="etternavn" value="${Deltager.etternavn}" pattern="^[A-Z][a-zA-ZæøåÆØÅ-]{1,19}$" required/>
			<c:if test="${bindingResult.hasFieldErrors('etternavn')}">
               <span style="color:red;">${bindingResult.getFieldError('etternavn').defaultMessage}</span>
            </c:if>

			<label>Mobil (8 siffer)</label>
			<input type="text" name="mobil" value="${Deltager.mobil}"  pattern="^\d{8}$" required/>
			<c:if test="${bindingResult.hasFieldErrors('mobil')}">
               <span style="color:red;">${bindingResult.getFieldError('mobil').defaultMessage}</span>
            </c:if>

            <label>Passord</label>
            <input type="password" name="plaintextPassword" minlength="4" required/>

            <label>Passord repetert</label>
            <input type="password" name="plaintextPasswordRepeat" minlength="4" required />
            <span id="passwordError" style="color:red; display:none;">Passwords do not match!</span>
            <c:if test="${bindingResult.hasFieldErrors('plaintextPasswordRepeat')}">
               <span style="color:red;">${bindingResult.getFieldError('plaintextPasswordRepeat').defaultMessage}</span>
            </c:if>
			
			<label>Kjønn</label> 
            <input type="radio" name="kjonn" value="MALE" ${Deltager.kjonn == 'MALE' ? 'checked' : ''} />mann
            <input type="radio" name="kjonn" value="FEMALE" ${Deltager.kjonn == 'FEMALE' ? 'checked' : ''} />kvinne
			     
			<br><br><button type="submit">Meld meg på</button>
		</fieldset>
	</form>

<script>
function validatePassword() {
    var pass1 = document.getElementsByName('passord')[0].value;
    var pass2 = document.getElementsByName('passordRepetert')[0].value;
    var passwordError = document.getElementById('passwordError');

    if (pass1 !== pass2) {
        passwordError.style.display = 'inline'; // Show the error message
        return false; // Indicates validation failure
    }
    passwordError.style.display = 'none'; // Hide the error message
    return true; // Indicates validation success
}

</script>
</body>
</html>
