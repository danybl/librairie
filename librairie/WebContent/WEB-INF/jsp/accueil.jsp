<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Accueil</title>
        <link rel="shortcut icon" href="livre.ico">
        <link href="librairieCSS.css" rel="stylesheet" media="all" type="text/css">
        <title>Librairie du coin</title>
    </head>
    <body>
    <c:import url="/WEB-INF/librairie.xml" var="documentXML" charEncoding="UTF-8"/>
    <x:parse var="doc" doc="${documentXML}"/>
    <h1>LIBRAIRIE DU COIN <img src="livres.png" name="image" border="0"></h1>
    <div id="navigation">
    	<ul>
    		<li><a href="connexion">CONNEXION</a></li>
		    <li><a href="deconnexion">DÉCONNEXION</a></li>
		    <li><a href="#">MON COMPTE</a></li>
		    <li><a href="/WEB-INF/jsp/inscription.jsp">CRÉER UN NOUVEAU COMPTE</a></li>
		    <li><a href="ajoutPanier">PANIER</a></li>
		    <li><a href="#">À PROPOS DE NOUS</a></li>
		    <li><a href="#">CONTACTEZ-NOUS</a></li>
    	</ul>
    </div>
    <div class="specialite">
    </div>
    <div class="box">
    	<x:forEach var="book" select="$doc/bookstore/category/book">
       		<div class="livre">
       			<span class="livre-image">
       				<img alt="<x:out select="$book/titre"/>" src="<x:out select="$book/image"/>"/>
       			</span>
       			
       			<span class="text">
       				<x:out select="$book/number"/>
       			</span>
       			
       			<span class="title">
       				<x:out select="$book/title"/>
       			</span>
       			
       			<span class="author">
       				<x:out select="$book/author"/>
       			</span>
       			
       			<span class="price">
       				<x:out select="$book/price"/>
       			</span>
       			
       			<form method="post"	action="ajoutPanier">
       				<span class="ajouter">
       					<input name="image"  type="hidden" value="<x:out select="$book/image"/>"/>
       					<input name="number" type="hidden" value="<x:out select="$book/number"/>"/>
       					<input name="title"	 type="hidden" value="<x:out select="$book/title"/>"/>
       					<input name="author" type="hidden" value="<x:out select="$book/author"/>"/>
       					<input name="price" type="hidden" value="<x:out select="$book/price"/>"/>
       					<input type="submit" value="Ajouter" id="btn"/>
       				</span>
       			</form>
       		</div>
       		</x:forEach>
    </div>
    </body>
</html>