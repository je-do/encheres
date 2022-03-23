package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Utilisateur;

public interface ArticleDAO {

	void insert(Article nouvelArticle) throws DALException;

	void update(Article articleAModifier) throws DALException;

	void delete(Integer noArticle) throws DALException;

	Article selectById(Integer noArticle) throws DALException;

	List<Article> selectAll() throws DALException;

	List<Article> selectByUtilisateurVendeur(Utilisateur utilisateur) throws DALException;

	List<Article> selectByCategorie(Categorie categorie) throws DALException;
	
	List<Article> selectByNomArticle(String libelle) throws DALException;
	
	List<Article> selectByEtat(Article article) throws DALException;
	
	List<Article> selectByEtatCommence() throws DALException;

}
