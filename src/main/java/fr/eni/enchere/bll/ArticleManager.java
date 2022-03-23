package fr.eni.enchere.bll;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;

public interface ArticleManager {
	
	public void vendreArticle(Article article) throws BLLException;
	
	public void supprimerUnArticle(Integer noArticle) throws BLLException;
	
	public void modifierArticle(Article articleAModifier) throws BLLException;
	
	public Article afficherArticleById (Integer noArticle) throws BLLException;
	
	public List<Article> getAllArticlesVendeur(Utilisateur utilisateur) throws BLLException;
	
	public List<Article> getAllArticle() throws BLLException;
	
	public List<Article> getAllArticlesByCategories (Categorie categorie) throws BLLException;
	
	public List<Article> getAllArticleByNomMotCle (String libelle) throws BLLException;

	
	public void miseAJourEtatVente(Article article) throws BLLException;
	
	public List<Article> getAllVenteCommence () throws BLLException;
	
	public List<Article> getAllVenteByEtatByUtilisateur (Article article) throws BLLException;
		
}
