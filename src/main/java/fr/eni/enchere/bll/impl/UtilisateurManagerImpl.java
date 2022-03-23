package fr.eni.enchere.bll.impl;

import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bll.BLLException;
import fr.eni.enchere.bll.ParameterException;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.DALException;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.UtilisateurDAO;

/**
 * Classe en charge de
 * 
 * @author jdonal2021
 * @date 15 janv. 2022 - 11:45:03
 * @version Enchere - V0.1
 */
public class UtilisateurManagerImpl implements UtilisateurManager {

	/** SINGLETON **/
	private static class UtilisateurManagerHolder {
		private static UtilisateurManager instance = new UtilisateurManagerImpl();
	}

	private UtilisateurManagerImpl() {
	}

	public static UtilisateurManager getInstance() {
		return UtilisateurManagerHolder.instance;
	}

	/** FIN SINGLETON **/

	private static UtilisateurDAO dao = DAOFactory.getInstance();

	/**
	 * 
	 * Méthode en charge de vérifier le Login et d'u associer un utilisateur
	 * existant
	 * 
	 * @param identifiant
	 * @param motDePasse
	 * @return un utilisateur
	 */
	public static Utilisateur validerLoginUtilisateur(String identifiant, String motDePasse) throws BLLException {

		
		Utilisateur utilisateurTeste = new Utilisateur();

		List<Utilisateur> listeUtilisateurs = new ArrayList<Utilisateur>();

		try {
			listeUtilisateurs = DAOFactory.getInstance().selectAll();
		} catch (DALException e) {
			
			e.printStackTrace();
		}

		for (Utilisateur utilisateur : listeUtilisateurs) {
			if ((identifiant.equals(utilisateur.getEmail()) && motDePasse.equals(utilisateur.getMotDePasse()))
					|| (identifiant.equals(utilisateur.getPseudo())
							&& motDePasse.equals(utilisateur.getMotDePasse()))) {

				utilisateurTeste = utilisateur;

				System.out.println("Login a un utilisateurexistant");
			}

		}
		return utilisateurTeste;

	}

	@Override
	public Utilisateur inscrireUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse, Integer credit) throws BLLException {

		BLLException be = new BLLException();

		validationPseudo(pseudo, be);
		validationNom(nom, be);
		validationPrenom(prenom, be);
		validationEmail(email, be);
		validationTelephone(telephone, be);
		validationRue(rue, be);
		validationCodePostal(codePostal, be);
		validationVille(ville, be);
		validationMotDePasse(motDePasse, be);
		validationCredit(credit, be);

		if (be.hasErreur()) {
			throw be;
		}

		Utilisateur nouvelUtilisateur = null;
		nouvelUtilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse,
				credit);

		try {
			dao.insert(nouvelUtilisateur);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}

		return nouvelUtilisateur;
	}

	@Override
	public void modifierUtilisateur(Utilisateur utilisateurAModifier) throws BLLException {

		BLLException be = new BLLException();

		//validationNoUtilisateur(utilisateurAModifier.getNoUtilisateur(), be);
		validationPseudo(utilisateurAModifier.getPseudo(), be);
		validationNom(utilisateurAModifier.getNom(), be);
		validationPrenom(utilisateurAModifier.getPrenom(), be);
		validationEmail(utilisateurAModifier.getEmail(), be);
		validationTelephone(utilisateurAModifier.getTelephone(), be);
		validationRue(utilisateurAModifier.getRue(), be);
		validationCodePostal(utilisateurAModifier.getCodePostal(), be);
		validationVille(utilisateurAModifier.getVille(), be);
		validationMotDePasse(utilisateurAModifier.getMotDePasse(), be);

		if (be.hasErreur()) {
			throw be;
		}

		try {
			dao.update(utilisateurAModifier);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
	}

	@Override
	public void supprimerUtilisateur(Integer noUtilisateur) throws BLLException {

		BLLException be = new BLLException();

		validationNoUtilisateur(noUtilisateur, be);

		if (be.hasErreur()) {
			throw be;
		}

		try {
			dao.delete(noUtilisateur);
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Utilisateur afficherUtilisateur(Integer noUtilisateur) throws BLLException {
		try {
			return dao.selectById(noUtilisateur);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
	}

	@Override
	public List<Utilisateur> getAllUtilisateurs() throws BLLException {
		try {
			return dao.selectAll();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
	}

	private void validationNoUtilisateur(Integer noUtilisateur, BLLException be) {
		if (noUtilisateur == null || noUtilisateur < 0) {
			be.ajouterErreur(new ParameterException("Le numero utilisateur ne peut pas être inférieur à 0"));
		}
	}

	private void validationCredit(Integer credit, BLLException be) {
		if (credit == null || credit < 0) {
			be.ajouterErreur(new ParameterException("Le credit ne peut pas être inférieur à 0"));
		}
	}

	private void validationMotDePasse(String motDePasse, BLLException be) {
		if (motDePasse == null || motDePasse.isBlank() || motDePasse.length() > 30) {
			be.ajouterErreur(new ParameterException("Le mot de passe doit contenir au maximum 30 caractères"));
		}

	}

	private void validationVille(String ville, BLLException be) {
		if (ville == null || ville.isBlank() || ville.length() > 50) {
			be.ajouterErreur(new ParameterException("La ville doit contenir au maximum 50 caractères"));
		}
	}

	private void validationCodePostal(String codePostal, BLLException be) {
		if (codePostal == null || codePostal.isBlank() || codePostal.length() > 10) {
			be.ajouterErreur(new ParameterException("Le code postal doit contenir au maximum 10 caractères"));
		}
	}

	private void validationRue(String rue, BLLException be) {
		if (rue == null || rue.isBlank() || rue.length() > 30) {
			be.ajouterErreur(new ParameterException("Le rue doit contenir au maximum 30 caractères"));
		}
	}

	private void validationTelephone(String telephone, BLLException be) {
		if (telephone == null || telephone.isBlank() || telephone.length() > 15) {
			be.ajouterErreur(new ParameterException("Le telephone doit contenir au maximum 15 caractères"));
		}
	}

	private void validationEmail(String email, BLLException be) {
		if (email == null || email.isBlank() || email.length() > 50) {
			be.ajouterErreur(new ParameterException("Le email doit contenir au maximum 50 caractères"));
		}
	}

	private void validationPrenom(String prenom, BLLException be) {
		if (prenom == null || prenom.isBlank() || prenom.length() > 30) {
			be.ajouterErreur(new ParameterException("Le prenom doit contenir au maximum 30 caractères"));
		}
	}

	private void validationNom(String nom, BLLException be) {
		if (nom == null || nom.isBlank() || nom.length() > 30) {
			be.ajouterErreur(new ParameterException("Le nom doit contenir au maximum 30 caractères"));
		}
	}

	private void validationPseudo(String pseudo, BLLException be) {
		if (pseudo == null || pseudo.isBlank() || pseudo.length() > 30) {
			be.ajouterErreur(new ParameterException("Le prenom doit contenir au maximum 30 caractères"));
		}
	}

}
