<%@ page language="java" contentType="text/html" %>
<%@ page errorPage="errorpage.jsp" %>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html;charset=utf-8" />
			<link href="librairieCSS.css" rel="stylesheet" media="all" type="text/css">
        	<title>Connectez-vous!</title>
    </head>
    <body> 
    	<h1>LIBRAIRIE DU COIN <img src="livres.png" name="image" border="0" alt=" "></h1>
    	<div class="error box">
    		Désolé, une erreur est survenue
    	</div>
    	<div class="bottom">
           	<form action="accueil" method="get">
				<input type="submit" value="Accueil" id="btn">
  			</form>
  		</div>
    </body>
</html>