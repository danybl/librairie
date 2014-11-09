<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Confirmation Inscription</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="librairieCSS.css"/>"/>
	</head>
	<body>
		<fieldset>
		<p><c:if test="${empty form.erreurs ? 'succes' : 'erreur' }"/><c:out value="${form.resultat}"/></p>
		<br/><br/>
		<form method="get" action="connexion.jsp">
		<input id="btn" type="submit" value="Connexion" class="sansLabel" /> 
		</form>	
		</fieldset>
		

	</body>
</html>