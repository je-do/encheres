<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ENI Enchères</title>
<!-- <link href = "style\stylePageAccueilNonConnecte.css" rel="stylesheet"> -->
</head>

<body class="body">

<h2>ENI-Enchères</h2>
<a href="http://localhost:8080/Enchere/PageConnexionServlet" id="InscrireConnecte">S'inscrire - Se connecter</a>

<h3>Liste des enchères</h3>

<h4>Filtres :</h4>
<form action="PageAccueilNonConnecteServlet" method="POST">
<input type="search" name="rechercheArticle"/>


<h5>Catégorie :</h5>

<select name="categorie">
<option value="0">Toutes</option>
<option value="1">Informatique</option>
<option value="2">Ameublement</option>
<option value="3">Vêtement</option>
<option value="4">Sport et Loisirs</option>
</select>



<input type="submit" name="Rechercher" value="Rechercher"/>
</form>

<c:forEach items="${modelArticle.listeArticles}" var="article">
	<p><a href="http://localhost:8080/Enchere/PageConnexionServlet">${article.nomArticle}</a> : ${article.categorie.libelle}</p>
	
	
	
</c:forEach>



</body>
</html>