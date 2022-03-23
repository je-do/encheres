package fr.eni.enchere.ihm.utilisateur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.BLLException;
import fr.eni.enchere.bll.impl.ArticleManagerImpl;
import fr.eni.enchere.bo.Article;

/**
 * Servlet implementation class VenteRemporteServlet
 */
@WebServlet("/VenteRemporteServlet")
public class VenteRemporteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArticleManager manager = ArticleManagerImpl.getInstance();
	Article articleRecupere = new Article();

	// Servlet qui correspond à Vous avez remporté la vente sur le PDF

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VenteRemporteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/* recupération de ModelUtilisateur depuis la session */
		UtilisateurModel model = (UtilisateurModel) request.getSession().getAttribute("model");
		ArticleModel articleModel = (ArticleModel) request.getSession().getAttribute("articleModel");

		String WEBINF = "WEB-INF/VenteRemporte.jsp";

		if (model == null) {
			model = new UtilisateurModel();
		}
		if (articleModel == null) {
			articleModel = new ArticleModel();
		}

		if (articleRecupere.getNoArticle() == null) {
			Integer noArticleClic = Integer.valueOf(request.getParameter("id"));

			try {
				articleRecupere = manager.afficherArticleById(noArticleClic);
				articleModel.setArticle(articleRecupere);
			} catch (BLLException e2) {
				e2.printStackTrace();
			}

			System.out.println(articleModel.getArticle());
			System.out.println(articleModel.getArticle().getNomArticle());

		}
		if (request.getParameter("back") != null) {

			WEBINF = "/PageListeEnchereMesVentesServlet";

		}

		request.setAttribute("articleModel", articleModel);
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
