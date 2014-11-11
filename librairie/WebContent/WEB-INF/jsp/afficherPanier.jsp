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
    <div class="box">
                	
       		<c:forEach items="${panier.listeLivres}" var="livre">
       		
       		<div class="livre">
       			<span class="livre-image">
       				<img alt="<c:out value="${livre.title}"/>" src="<c:out value="${livre.cheminImage}"/>"/>
       			</span>
       			
       			<span class="text">
       				<c:out value="${livre.number}"/>
       			</span>
       			
       			<span class="title">
       				<c:out value="${livre.title}"/>
       			</span>
       			
       			<span class="author">
       				<c:out value="${livre.author}"/>
       			</span>
       			
       			<span class="price">
       				<c:out value="${livre.price}"/>
       			</span>
       			
       			<span class="qte">
       				Quantite: <c:out value="${livre.quantite}"/>
       			</span>
       			
       			<form method="post"	action="retraitPanier">
       				<span class="ajouter">
       					<input name="number" type="hidden" value="<c:out value="${livre.number}"/>"/>
       					<input type="submit" value="Retirer" id="btn"/>
       				</span>
       			</form>
       		</div>
       		</c:forEach>
       		<div class="bottom">
       		<c:if test="${!empty panier.listeLivres }">
       		<form action="viderPanier" method="post">
       			<span class="RemoveAll">
       			<input name="number" type="hidden" value="0">
       			<input type="submit" value="Vider panier" id="btn">
       		</span>
       		</form>
       		</c:if>
       		<form action="accueil" method="get">
       			<input type="submit" value="Accueil" id="btn">
       		</form>
       		</div>
    </div>
    </body>
</html>