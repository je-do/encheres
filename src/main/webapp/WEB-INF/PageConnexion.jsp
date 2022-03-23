<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <link rel="stylesheet" href="/Enchere/style/stylePageConnection.css">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <header>
    <div class="navbar">
      <div class="containerTitre">
        <a href="http://localhost:8080/Enchere/PageAccueilNonConnecteServlet" aria-current="page" class="brand">
          <strong class="bold-text">ENI ENCHERES</strong>
        </a>
      </div>
    </div>
  </header>
  <div class="formulaire">
    <form action="PageConnexionServlet" method="POST">
      <p class="heading">Se connecter</p>
      <br>
      <div class="login">
        <div class="identifiant">
          <input type="text" name="identifiant" placeholder="Identifiant" />
        </div>
        <br>
        <div class="motDePasse">
          <input type="password" name="motDePasse" placeholder="Mot de passe" />
        </div>
        <br>
      </div>
      <div class="checkBox">
        <input type="checkbox" name="SeSouvenir" /> Se souvenir de moi 
      </div>
      <div class="bouttonConnexion">
        <button type="submit" name="Connexion" value="connexion" id="boutton">Connexion</button>
      </div>
      <div class="motDePasseOublie">
        <a href="">Mot de passe oublié</a>
      </div>
      <div class="BouttonCreerUnCompte" >
        <input id="boutton" type="submit" name="CreerUnCompte" value="Créer un Compte"/>
      </div>
    </form>
  </div>
</body>
</html>