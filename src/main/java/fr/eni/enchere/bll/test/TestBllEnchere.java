package fr.eni.enchere.bll.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.BLLException;
import fr.eni.enchere.bll.EnchereManager;
import fr.eni.enchere.bll.EnchereManagerImpl;
import fr.eni.enchere.bll.impl.ArticleManagerImpl;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.DALException;
import fr.eni.enchere.dal.DAOFactory;

public class TestBllEnchere {

	public static void main(String[] args) throws BLLException, DALException {

		List<Enchere> listeEncheres = new ArrayList<Enchere>();

		EnchereManager manager = EnchereManagerImpl.getInstance();
		ArticleManager managerArticle = ArticleManagerImpl.getInstance();
		
		Article article = new Article();
		
		Utilisateur utilisateur = new Utilisateur();
		
//		try {
//			Article articleDao = DAOFactory.getInstanceArticle().selectById(1);
//			Utilisateur utilisateurDao = DAOFactory.getInstance().selectById(7);
//
//			listeEncheres.add(manager.ajoutEnchere(new Enchere(LocalDate.of(2022, 01, 15), 500, utilisateurDao, articleDao)));
//		} catch (BLLException be) {
//			be.printStackTrace();
//		}

		System.out.println(manager.selectionnerEnchereByNomMotCle("ordinateur"));
		
		
	}

}
