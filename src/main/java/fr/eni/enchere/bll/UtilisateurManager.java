package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.bo.Utilisateur;

public interface UtilisateurManager {
	
	public Utilisateur inscrireUtilisateur (String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville, String motDePasse, Integer credit) throws BLLException;
	
	public void modifierUtilisateur (Utilisateur utilisateurAModifier) throws BLLException;
	
	public Utilisateur afficherUtilisateur (Integer noUtilisateur) throws BLLException;
	
	public void supprimerUtilisateur (Integer noUtilisateur) throws BLLException;
	
	public List<Utilisateur> getAllUtilisateurs()throws BLLException;

}
