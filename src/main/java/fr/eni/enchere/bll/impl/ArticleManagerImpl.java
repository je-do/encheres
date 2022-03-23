package fr.eni.enchere.bll.impl;

import java.time.LocalDate;
import java.util.List;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.BLLException;
import fr.eni.enchere.bll.ParameterException;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.ArticleDAO;
import fr.eni.enchere.dal.DALException;
import fr.eni.enchere.dal.DAOFactory;

public class ArticleManagerImpl implements ArticleManager {

	/** SINGLETON **/
	private static class ArticleManagerHolder {
		private static ArticleManager instance = new ArticleManagerImpl();
	}

	private ArticleManagerImpl() {
	}

	public static ArticleManager getInstance() {
		return ArticleManagerHolder.instance;
	}

	/** FIN SINGLETON **/

	private static ArticleDAO dao = DAOFactory.getInstanceArticle();

	public void miseAJourEtatVente(Article article) {

		if ((LocalDate.now().isAfter(article.getDateFinEncheres()))) {
			article.setEtatVente("termine");
			try {
				modifierArticle(article);
			} catch (BLLException e) {

				e.printStackTrace();
			}

		} else if (article.getDateDebutEncheres().isBefore(LocalDate.now()) ||
				article.getDateDebutEncheres().equals(LocalDate.now())) {
			article.setEtatVente("commence");
			try {
				modifierArticle(article);
			} catch (BLLException e) {

				e.printStackTrace();
			}

			//System.out.println("je suis dans les articles états commencés");

		}


		//System.out.println("j'ai mise à jour les états");
	}

	@Override
	public void vendreArticle(Article article) throws BLLException {

		BLLException be = new BLLException();

		validationNomArticle(article.getNomArticle(), be);
		validationDescription(article.getDescription(), be);
		validationDateDebutEnchere(article.getDateDebutEncheres(), be);
		validationDateDebutEnchere(article.getDateFinEncheres(), be);
		validationPrixInitial(article.getPrixInitial(), be);

		if (be.hasErreur()) {

			throw be;
		}

		Article nouvelArticle = null;
		nouvelArticle = new Article();

		try {
			dao.insert(article);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}

	}

	@Override
	public void supprimerUnArticle(Integer noArticle) throws BLLException {

		BLLException be = new BLLException();

		validationNoArticle(noArticle, be);

		if (be.hasErreur()) {
			throw be;
		}

	
		
		try {
			dao.delete(noArticle);
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void modifierArticle(Article articleAModifier) throws BLLException {

		BLLException be = new BLLException();

		validationNoArticle(articleAModifier.getNoArticle(), be);
		validationPrixDeVente(articleAModifier.getPrixDeVente(), be);

		if (be.hasErreur()) {
			throw be;
		}

		try {
			dao.update(articleAModifier);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
	}

	@Override
	public List<Article> getAllArticlesVendeur(Utilisateur utilisateur) throws BLLException {

		try {
			return dao.selectByUtilisateurVendeur(utilisateur);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
	}

	@Override
	public List<Article> getAllArticle() throws BLLException {
		try {
			return dao.selectAll();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
	}

	@Override
	public List<Article> getAllArticlesByCategories(Categorie categorie) throws BLLException {

		try {
			return dao.selectByCategorie(categorie);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
	}

	@Override
	public List<Article> getAllArticleByNomMotCle(String libelle) throws BLLException {

		try {
			return dao.selectByNomArticle(libelle);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}

	}

	@Override
	public Article afficherArticleById(Integer noArticle) throws BLLException {

		try {
			return dao.selectById(noArticle);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}

	}
	
	@Override
	public List<Article> getAllVenteCommence() throws BLLException {
		try {
			return dao.selectByEtatCommence();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
	}

	@Override
	public List<Article> getAllVenteByEtatByUtilisateur(Article article) throws BLLException {
		try {
			return dao.selectByEtat(article);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
	}

	private void validationNoArticle(Integer noArticle, BLLException be) {
		if (noArticle == null || noArticle < 0) {
			be.ajouterErreur(new ParameterException("Le numéro Article est inférieur à 0"));
		}
	}

	private void validationNomArticle(String nomArticle, BLLException be) {
		if (nomArticle == null || nomArticle.isBlank() || nomArticle.length() > 30) {
			be.ajouterErreur(new ParameterException("Le nom Article est supérieur à 30 caractères"));
		}
	}

	private void validationDescription(String description, BLLException be) {
		if (description == null || description.isBlank() || description.length() > 300) {
			be.ajouterErreur(new ParameterException("Le nom Article est supérieur à 300 caractères"));
		}
	}

	private void validationDateDebutEnchere(LocalDate dateDebutEncheres, BLLException be) {
		if (dateDebutEncheres.isBefore(LocalDate.now()) == true) {
			be.ajouterErreur(new ParameterException("La date d'enchère n'est pas valide"));
		}
	}

	private void validationDateFinEncheres(LocalDate dateFinEncheres, BLLException be) {
		if (dateFinEncheres.isBefore(LocalDate.now()) == true && dateFinEncheres.isAfter(LocalDate.now())) {
			be.ajouterErreur(new ParameterException("La date d'enchère n'est pas valide"));
		}
	}

	private void validationPrixDeVente(Integer prixDeVente, BLLException be) {
		if (prixDeVente == null || prixDeVente < 0 ) {
			be.ajouterErreur(new ParameterException("Le Prix de vente Article est inférieur à 0"));
		}

	}

	private void validationPrixInitial(Integer prixInitial, BLLException be) {
		if (prixInitial == null || prixInitial < 0) {
			be.ajouterErreur(new ParameterException("Le Prix initial de l'Article est inférieur à 0"));
		}

	}

}
