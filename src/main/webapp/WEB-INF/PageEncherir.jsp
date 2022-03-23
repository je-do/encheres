<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page encherir</title>
</head>
<body>

<header>
    <div class="navbar">
      <div class="containerTitre">
        <a href="http://localhost:8080/Enchere/PageListeEnchereMesVentesServlet" aria-current="page" class="brand">
          <strong class="bold-text">ENI ENCHERES</strong>
        </a>
      </div>
    </div>
  </header>

<form action= "PageEncherirServlet" method="POST">

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
Retrait : ${retraitModel.retrait.rue} ${retraitModel.retrait.codePostal} ${retraitModel.retrait.ville}
<br> 
Vendeur : ${articleModel.article.utilisateur.pseudo} 

<br>
Ma proposition:<input type = "number" name= "maProposition" value = "${articleModel.article.prixDeVente}"/>

<input type="submit" name="encherir" value="encherir"/>

</form> 
</body>
</html>

<!-- <form action="PageMonProfilServlet" method="POST"> -->
<%-- Pseudo : ${model.utilisateur.pseudo} --%>
<!-- <br> -->
<%-- Nom : ${model.utilisateur.nom} --%>
<!-- <br> -->
<%-- Prenom : ${model.utilisateur.prenom} --%>
<!-- <br> -->
<%-- Email : ${model.utilisateur.email} --%>
<!-- <br> -->
<%-- Teléphone : ${model.utilisateur.telephone} --%>
<!-- <br> -->
<%-- Rue : ${model.utilisateur.rue} --%>
<!-- <br> -->
<%-- Code postal : ${model.utilisateur.codePostal} --%>
<!-- <br> -->
<%-- Ville : ${model.utilisateur.ville} --%>
<!-- <br> -->
<!-- <input type="submit" name="Modifier" value="Modifier"/> -->

<!-- </form> -->