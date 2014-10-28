<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Accueil</title>
        <link type="text/css" rel="stylesheet" href="librairieCSS.css" />
    </head>
    <body>
    <c:import url="/WEB-INF/librairie.xml" var="documentXML"/>
    <x:parse var="doc" doc="${documentXML}"/>
        <p><b>Liste des livres</b></p>
        <div class="livre">
        <span class="livre-image"/>
        <x:forEach var="livre" select="$doc/bookstore/category/book">
        <x:out select="$livre/image"/>
        </x:forEach>
        
        </div>
    </body>
</html>