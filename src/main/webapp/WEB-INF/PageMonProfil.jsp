<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PageMonProdil</title>
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

<form action="PageMonProfilServlet" method="POST">
Pseudo : ${model.utilisateur.pseudo}
<br>
Nom : ${model.utilisateur.nom}
<br>
Prenom : ${model.utilisateur.prenom}
<br>
Email : ${model.utilisateur.email}
<br>
Teléphone : ${model.utilisateur.telephone}
<br>
Rue : ${model.utilisateur.rue}
<br>
Code postal : ${model.utilisateur.codePostal}
<br>
Ville : ${model.utilisateur.ville}
<br>
<input type="submit" name="Modifier" value="Modifier"/>

</form>
</body>
</html>