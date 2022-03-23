package fr.eni.enchere.ihm.utilisateur;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.BLLException;
import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bll.impl.ArticleManagerImpl;
import fr.eni.enchere.bll.impl.CategorieManagerImpl;
import fr.eni.enchere.bll.impl.UtilisateurManagerImpl;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;

/**
 * Servlet implementation class PageAccueilNonConnecte
 */
@WebServlet("/PageAccueilNonConnecteServlet")
public class PageAccueilNonConnecteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager manager = UtilisateurManagerImpl.getInstance();
	private ArticleManager managerArticle = ArticleManagerImpl.getInstance();
	private CategorieManager managerCategorie = CategorieManagerImpl.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PageAccueilNonConnecteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//Supprimer un cession si il y en a une en cours
		HttpSession session = request.getSession();
		session.invalidate();
		
		UtilisateurModel model = new UtilisateurModel();
		ArticleModel modelArticle = new ArticleModel();
		String WEBINF = "WEB-INF/PageAccueilNonConnecte.jsp";
		List<Article> listeArticles = new ArrayList<Article>();

		
		// Mise à jour de l'état des articles vendus en fonction de la date du jour
		try {
			listeArticles = managerArticle.getAllArticle();
			for (Article article : listeArticles) {
				managerArticle.miseAJourEtatVente(article);

				
			}
		}
		catch (BLLException e) {
			e.printStackTrace();
		}

		if (request.getParameter("InscrireConnecte") != null) {
		}

		// Lister les articles en fonction des filtres NomArticle et Categorie
		if (request.getParameter("Rechercher") != null) {

			String rechercheArticle = request.getParameter("rechercheArticle");
			Integer noCategorie = Integer.parseInt(request.getParameter("categorie"));
			Categorie categorie = null;
			String etatVente = "commence"; 

			try {
				categorie = managerCategorie.categorieById(noCategorie);
				
				if (rechercheArticle == "") {
					if (noCategorie == 0) {
						modelArticle.setListeArticles(managerArticle.getAllVenteCommence());
								
					} else {
						
						//modelArticle.setListeArticles(managerArticle.getAllArticlesByCategories(categorie));
						List<Article>lstArticlesCommence = managerArticle.getAllVenteCommence();
						List<Article> lstCommenceParCategories = new ArrayList<Article>();
						
						for (Article article : lstArticlesCommence) {
							
							if (noCategorie == (article.getCategorie().getNoCategorie())) {
								lstCommenceParCategories.add(article);
							}
						}
							modelArticle.setListeArticles(lstCommenceParCategories);
					}
						
				} else {
					if (noCategorie == 0) {
						List<Article>lstParMotCle = managerArticle.getAllArticleByNomMotCle(rechercheArticle);
						List<Article>lstArticlesCommencesParMotCle = new ArrayList<Article>();
						for (Article article : lstParMotCle) {
							
							if (etatVente.equals(article.getEtatVente())) {
								
								lstArticlesCommencesParMotCle.add(article);
							}
							modelArticle.setListeArticles(lstArticlesCommencesParMotCle);
						}
						
					} else {
						List<Article> listeArticlesNom = managerArticle.getAllArticleByNomMotCle(rechercheArticle);
						List<Article> listeArticlebyNomByCategorie = new ArrayList<Article>();
						List<Article>lstArticlesCommenceParNomParCategorie = new ArrayList<Article>();
						for (Article article : listeArticlesNom) {
							if (noCategorie == (article.getCategorie().getNoCategorie())) {
								listeArticlebyNomByCategorie.add(article);
							}
							
							for (Article article2 : listeArticlebyNomByCategorie) {
								
								if (etatVente.equals(article2.getEtatVente())) {
									
									lstArticlesCommenceParNomParCategorie.add(article);
								}
								
							}
						}
					
						modelArticle.setListeArticles(lstArticlesCommenceParNomParCategorie);

					}

				}

			} catch (BLLException e1) {
				e1.printStackTrace();
			}
		}

		request.setAttribute("model", model);
		request.setAttribute("modelArticle", modelArticle);
		request.getRequestDispatcher(WEBINF).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
