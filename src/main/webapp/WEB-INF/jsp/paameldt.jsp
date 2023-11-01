<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link href="/simple.css" rel="stylesheet" type="text/css" />
<title>Påmeldingsbekreftelse</title>
</head>
<body>
	<h2>Påmeldingsbekreftelse</h2>
	<p>Påmeldingen er mottatt for</p>
	<p>
		&nbsp;&nbsp;&nbsp;${deltager.fornavn}<br />
		&nbsp;&nbsp;&nbsp;${deltager.etternavn}<br />
		&nbsp;&nbsp;&nbsp;${deltager.mobil}<br />
        &nbsp;&nbsp;&nbsp;${deltager.kjonn == 'FEMALE' ? 'kvinne' : 'mann'}
	</p>
	<a href="/deltagerliste">Gå til deltagerlisten</a>
</body>
</html>