<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Connexion</title>
        <link type="text/css" rel="stylesheet" href="librairieCSS.css" />
    </head>
    <body>
        <h1>LIBRAIRIE DU COIN <img src="livres.png" name="image" border="0" alt=" "></h1>
    	<fieldset>
    		<legend> Connectez-vous!</legend>
    		<form class="connexion-form" method="post" action="Connexion">  
				<label for="username">Nom d'usager:</label><br />
				<input type="text" id="username" name="username" placeholder="Nom d'usager">
				<span class="erreur">${form.erreurs['username'] }</span>
				<br /><br />
				<label for="password">Mot de passe:</label><br />
				<input type="password" id="password" name="password" placeholder="Mot de passe d'usager">
				<span class="erreur">${form.erreurs['password'] }</span>
				<br /><br /> 
				<input type="submit" value="Connexion" id="btn"/>  
			</form> 
		
		<div class="connexion">
			<form method="get" action="Accueil">
			<input type="submit" value="Accueil" id="btn">
			</form>

			<form action="Inscription">
			<input type="submit" value="Créer un compte" id="btn">
			</form>
		</div>
	</fieldset>
    </body>
</html>