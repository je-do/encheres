<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ventes Remportées</title>
</head>
<body>

<form action= "VenteRemporteServlet" method="POST">

	<h2> Vous avez remporté la vente</h2>

 ${articleModel.article.nomArticle}
<br>
Description : ${articleModel.article.description}
<br>
Categorie :  ${articleModel.article.categorie.libelle}
<br>
Meilleure offre :${articleModel.article.prixDeVente}
<br>
Mise à prix : ${articleModel.article.prixInitial}
<br>
Fin de l'enchère : ${articleModel.article.dateFinEncheres}
<br>
Retrait : ${articleModel.article.retrait.rue} ${articleModel.article.retrait.codePostal} ${articleModel.article.retrait.ville}
<br> 
Vendeur : ${articleModel.article.utilisateur.pseudo} 

<br>

<input type="submit" name="back" value="Retour"/>

</form> 


</body>
</html>