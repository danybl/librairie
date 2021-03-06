<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <link rel="SHORTCUT ICON" href="livre.ico" type="image/icon" />
        <title>Inscription</title>
        <link type="text/css" rel="stylesheet" href="librairieCSS.css" />
    </head>
    <body>
    	<h1>LIBRAIRIE DU COIN <img src="livres.png" name="image" border="0" alt=" "></h1>

        <form method="post" action="inscription">
            <fieldset>
                <legend>Inscription</legend>
                
                <label for="email">Adresse email <span class="requis">*</span></label><br />
                <input type="text" id="email" name="email" value="<c:out value="${client.email }"/>" size="20" maxlength="60" />
                <span class="erreur">${form.erreurs['email'] }</span>
                <br /><br /> 

                <label for="motdepasse">Mot de passe <span class="requis">*</span></label><br />
                <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['motdepasse'] }</span>
                <br /><br /> 

                <label for="confirmation">Confirmation du mot de passe <span class="requis">*</span></label><br />
                <input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['confirmation'] }</span>
                <br /><br /> 

                <label for="nom">Nom d'utilisateur</label><br />
                <input type="text" id="username" name="username" value="<c:out value="${client.username }"/>" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['nom'] }</span>
                <br /><br />

                <input id ="btn" type="submit" value="Inscription"/>   
                           
        </form>
        <form method="get" action="accueil">
            <input id="btn" type="submit" value="Accueil"/>
        </form>  
        <br />  
         </fieldset>  
    </body>
</html>