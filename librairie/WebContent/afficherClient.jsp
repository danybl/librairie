<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Affichage du client</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="inc/style.css"/>"/>
	</head>
	<body>
		<!-- Import du menu -->
		<c:import  url="inc/menu.jsp"/>
		
		<p class="info">${message }</p>
			
		<c:if test="${validation}">
			<p><c:out value="Nom : ${client.nom }"/></p>
			<p><c:out value="Prénom : ${client.prenom }"/></p>
			<p><c:out value ="Adresse : ${client.adresse}"/></p>
			<p><c:out value ="Numéro de téléphone : ${client.numTelephone}"/></p>
			<p><c:out value="Email : ${client.email }"/></p>
			<p><c:out value="${clientCree}"/></p>
		</c:if>

	</body>
</html>