package fr.eni.enchere.dal;

import fr.eni.enchere.dal.jdbc.ArticleDAOJdbcImpl;
import fr.eni.enchere.dal.jdbc.CategorieDAOJdbcImpl;
import fr.eni.enchere.dal.jdbc.EnchereDAOImpl;
import fr.eni.enchere.dal.jdbc.RetraitDAOImpl;
import fr.eni.enchere.dal.jdbc.UtilisateurDAOImpl;


public class DAOFactory {

	
	public static UtilisateurDAO getInstance() {
		return new UtilisateurDAOImpl();

	}
	
	public static ArticleDAO getInstanceArticle() {
		return new ArticleDAOJdbcImpl();

	}
	
	public static RetraitDAO getInstanceRetrait() {
		return new RetraitDAOImpl();

	}
	
	public static EnchereDAO getInstanceEnchere() {
		return new EnchereDAOImpl();

	}
	
	public static CategorieDAO getInstanceCategorie() {
		return new CategorieDAOJdbcImpl();

	}

	
	
	
	
}
