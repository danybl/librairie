<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Confirmation Inscription</title>
		<link type="text/css" rel="stylesheet" href="librairieCSS.css"/>
	</head>
	<body>
		<fieldset class="confirmationInscription">
			<p><c:if test="${empty form.erreurs ? 'succes' : 'erreur' }"/><c:out value="${form.resultat}"/></p>
			<br/><br/>
			<div class="connexion">
				<form class="connexion-form" method="get" action="connexion">
					<input id="btn" type="submit" value="Connexion"/> 
				</form>
			</div>	
		</fieldset>
	</body>
</html>