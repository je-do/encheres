package fr.eni.enchere.bll.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.BLLException;
import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bll.RetraitManager;
import fr.eni.enchere.bll.impl.ArticleManagerImpl;
import fr.eni.enchere.bll.impl.CategorieManagerImpl;
import fr.eni.enchere.bll.impl.RetraitManagerImpl;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.DALException;
import fr.eni.enchere.dal.DAOFactory;

public class TestBllArticle {

	public static void main(String[] args) {

		List<Article> lstArticles = new ArrayList<>();

		ArticleManager manager = ArticleManagerImpl.getInstance();

		CategorieManager managerCategorie = CategorieManagerImpl.getInstance();
		RetraitManager managerRetrait = RetraitManagerImpl.getInstance();
		
		
		try {
			Utilisateur utilisateur = DAOFactory.getInstance().selectById(5);
			Categorie categorie =  DAOFactory.getInstanceCategorie().selectById(4);
			try {
				manager.vendreArticle(new Article("Mon Velo", "Velo qui va vite pas vite", LocalDate.of(2022, 11, 22),
						LocalDate.of(2022, 11, 22), 23, utilisateur, categorie));
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} catch (DALException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
//		try {
//			
//			Utilisateur utilisateur = DAOFactory.getInstance().selectById(4);
//			Categorie categorie =  DAOFactory.getInstanceCategorie().selectById(4);
//			
//			Utilisateur utilisateur2 = DAOFactory.getInstance().selectById(5);
//			Categorie categorie2 =  DAOFactory.getInstanceCategorie().selectById(1);
//			
//			
//			manager.vendreArticle(new Article("Velo", "VTT qui va vite", LocalDate.of(2022, 11, 22),
//				LocalDate.of(2022, 11, 22), 500, 500, utilisateur, categorie));
//
//		
//
//		manager.vendreArticle(new Article("PC", "PC Gamer", LocalDate.of(2022, 01, 23),
//				LocalDate.of(2022, 01, 24), 300, 300, utilisateur2, categorie2));
//		
//		
//		
//
//		} catch (DALException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (BLLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		
//		try {
//			System.out.println(manager.getAllArticle());
//		} catch (BLLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
//		
//	
//		
//		try {
//			System.out.println(managerCategorie.categorieById(2));
//			System.out.println(managerCategorie.categorieByLibelle("Ameublement"));
//			System.out.println(managerCategorie.getAllCategorie());
//		} catch (BLLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		try {
			
			System.out.println(manager.getAllArticleByNomMotCle("ordinateur"));
			
			
		} catch (BLLException e) {
			
			e.printStackTrace();
		}
		
		
//		Article article;
//		
//		
//		try {
//			article = DAOFactory.getInstanceArticle().selectById(2);
//			try {
//				managerRetrait.ajouterRetrait(new Retrait("rue de truc","22500","rennes", article));
//			} catch (BLLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} catch (DALException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
	
		
	
//			Retrait retrait = new Retrait();
//			
//				try {
//					retrait = DAOFactory.getInstanceRetrait().retraitByNoArticle(2);
//					System.out.println(retrait);
//				} catch (DALException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				retrait.setRue("rue modifiée");
//				retrait.setCodePostal("9999");
//				retrait.setVille("VilleModifiee");
//				try {
//					managerRetrait.modifierRetrait(retrait);
//				} catch (BLLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//		
//		
//	}
	}}
	

