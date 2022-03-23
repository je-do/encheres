<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modification Vente</title>
</head>
<body>

<form action="ModifierVenteServlet" method="POST">

<h2>Modification Vente</h2>

Article: <input type="text" name="nomArticle" value="${articleModel.article.nomArticle}"/><br>
Description: <input type="text" name="description" value="${articleModel.article.description}"/> <br>

 Catégorie : <select name="categorieNumero">
<option value="1">Informatique</option>
<option value="2">Ameublement</option>
<option value="3">Vêtement</option>
<option value="4">Sport et Loisirs</option>
</select> <br>


Mise à Prix: <input type="text" name="miseAPrix" value="${articleModel.article.prixInitial}"/> <br>
Date début enchère: <input type="date" name="debutEnchere" value="${articleModel.article.dateDebutEncheres}"/> <br>
Date Fin enchère: <input type="date" name="finEnchere" value="${articleModel.article.dateFinEncheres}"/> <br>
<h3>Retrait</h3> <br>

Rue: <input type="text" name="rueRetrait" value="${articleModel.retrait.rue}"/> <br>
Code postal: <input type="text" name="codePostal" value="${articleModel.retrait.codePostal}"/> <br>
Ville: <input type="text" name="ville" value="${articleModel.retrait.ville}"/> <br>

<input type="submit" name="enregistrer" value="enregistrer"/>
<input type="submit" name="annuler" value="annuler"/>
<input type="submit" name="annulerlavente" value="Annuler la Vente"/>
</form>

</body>
</html>