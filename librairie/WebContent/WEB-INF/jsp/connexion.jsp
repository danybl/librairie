<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <link rel="SHORTCUT ICON" href="livre.ico" type="image/icon" />
        <title>Connexion</title>
        <link type="text/css" rel="stylesheet" href="librairieCSS.css" />
    </head>
    <body>
        <h1>LIBRAIRIE DU COIN <img src="livres.png" name="image" border="0" alt=" "></h1>
    	<fieldset>
    		<legend> <img src="login.png" name="image" border="0" alt="login"> Connectez-vous!</legend>
    		<form class="connexion-form" method="post" action="connexion">  
				<label for="username">Nom d'usager:</label><br />
				<input type="text" id="username" name="username" placeholder="Nom d'usager">
				<br /><br />
				<label for="password">Mot de passe:</label><br />
				<input type="password" id="password" name="password" placeholder="Mot de passe d'usager">
				<br /><br />
				<span class="erreur">${form.erreurs['client'] }</span> 
				<input type="submit" value="Connexion" id="btn"/>  
			</form> 
		
		<div class="connexion">
			<form method="get" action="inscription">
			<input type="submit" value="Créer un compte" id="btn">
			</form>
		</div>
	</fieldset>
    </body>
</html>