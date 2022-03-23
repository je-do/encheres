<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <link rel="stylesheet" href="/Enchere/style/stylePageModifierProfil.css">
  <head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
  </head>
  <body>
    <header>
      <div class="navbar">
        <div class="containerTitre">
          <a href="http://localhost:8080/Enchere/PageListeEnchereMesVentesServlet">
            <strong class="bold-text">ENI ENCHERES</strong>
          </a>
        </div>
      </div>
    </header>
    <div class="formulaire">
      <form action="PageModifierMonProfilServlet" method="POST">
      <div class="pseudoNom">
        <label>Pseudo:</label><input type="text" name="pseudo" value="${model.utilisateur.pseudo}"/>
        <label>Nom:</label> <input type="text" name="nom" value="${model.utilisateur.nom}"/> <br>
      </div>
      <br>
      <div class="prenomMail">
        <label>Prénom:</label> <input type="text" name="prenom" value="${model.utilisateur.prenom}"/>
        <label>Email:</label> <input type="text" name="email" value="${model.utilisateur.email}"/> <br>
      </div>
      <br>
      <div class="telephoneRue">
        <label>Téléphone:</label> <input type="text" name="telephone" value="${model.utilisateur.telephone}"/>
        <label>Rue:</label> <input type="text" name="rue" value="${model.utilisateur.rue}"/> <br>
      </div>
      <br>
      <div class="cdpVille">
        <label>Code postal:</label> <input type="text" name="codePostal" value="${model.utilisateur.codePostal}"/>
        <label> Ville:</label> <input type="text" name="ville" value="${model.utilisateur.ville}"/> <br>
      </div>
      <br>
      <div class="mdpConfirmation">
        <label>Mot de passe:</label> <input type="password" name="motDePasse" value="${model.utilisateur.motDePasse}"/>
        <label>Confirmation:</label> <input type="password" name="confirmation" value="${model.confirmation}"/> <br>
      </div>
      <br>
      <div class="boutton">
          <input id="boutton" type="submit" name="enregistrer" value="Enregistrer"/>
          <input id="boutton" type="submit" name="supprimer" value="Supprimer"/>
      </div>
         <p>${model.message} </p>
    </form>
    </div>
    <br>
  </body>
</html>