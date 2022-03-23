/**
 * 
 */
package fr.eni.enchere.ihm.utilisateur;

import java.util.List;
import java.util.ArrayList;

import fr.eni.enchere.bo.Utilisateur;

/**
 * Classe en charge de 
 * @author jdonal2021
 * @date 12 janv. 2022 - 10:47:39
 * @version Enchere - V0.1  
 */
public class UtilisateurModel {

	
	private Utilisateur utilisateur;
	private List<Utilisateur> listeUtilisateurs = new ArrayList<Utilisateur>();
	private String message="";
	private String confirmation;
	private String nouveauMotDePasse;
	
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public List<Utilisateur> getListeUtilisateurs() {
		return listeUtilisateurs;
	}
	public void setListeUtilisateurs(List<Utilisateur> listeUtilisateurs) {
		this.listeUtilisateurs = listeUtilisateurs;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * Constructeur.
	 * @param utilisateur
	 * @param listeUtilisateurs
	 * @param message
	 */
	public UtilisateurModel(Utilisateur utilisateur, List<Utilisateur> listeUtilisateurs, String message) {
		super();
		this.utilisateur = utilisateur;
		this.listeUtilisateurs = listeUtilisateurs;
		this.message = message;
	}
	
	/**
	 * Constructeur.
	 */
	public UtilisateurModel() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UtilisateurModel [utilisateur=");
		builder.append(utilisateur);
		builder.append(", listeUtilisateurs=");
		builder.append(listeUtilisateurs);
		builder.append(", message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}
	public String getConfirmation() {
		return confirmation;
	}
	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}
	public String getNouveauMotDePasse() {
		return nouveauMotDePasse;
	}
	public void setNouveauMotDePasse(String nouveauMotDePasse) {
		this.nouveauMotDePasse = nouveauMotDePasse;
	}
	
	
	
}
