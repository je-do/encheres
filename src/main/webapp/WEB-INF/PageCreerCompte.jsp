<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Creer Compte</title>
</head>
<body>

<form action="UtilisateurCreerServlet" method="POST">

<header>
    <div class="navbar">
      <div class="containerTitre">
        <a href="http://localhost:8080/Enchere/PageConnexionServlet" aria-current="page" class="brand">
          <strong class="bold-text">ENI ENCHERES</strong>
        </a>
      </div>
    </div>
  </header>

Pseudo: <input type="text" name="pseudo" value="${model.utilisateur.pseudo}"/>
Nom: <input type="text" name="nom" value="${model.utilisateur.nom}"/> <br>
Prénom: <input type="text" name="prenom" value="${model.utilisateur.prenom}"/> 
Email: <input type="text" name="email" value="${model.utilisateur.email}"/> <br>
Téléphone: <input type="text" name="telephone" value="${model.utilisateur.telephone}"/>
Rue: <input type="text" name="rue" value="${model.utilisateur.rue}"/> <br>
Code postal: <input type="text" name="codePostal" value="${model.utilisateur.codePostal}"/>
Ville: <input type="text" name="ville" value="${model.utilisateur.ville}"/> <br>
Mot de passe: <input type="password" name="motDePasse" value="${model.utilisateur.motDePasse}"/>
Confirmation: <input type="password" name="confirmation" value="${model.confirmation}"/> <br>
<input type="submit" name="creer" value="Créer"/>
<input type="submit" name="annuler" value="annuler"/>
</form>



<p>${model.message} </p>

</body>
</html>