package fr.eni.enchere.ihm.utilisateur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.apt.model.ArrayTypeImpl;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.BLLException;
import fr.eni.enchere.bll.EnchereManager;
import fr.eni.enchere.bll.EnchereManagerImpl;
import fr.eni.enchere.bll.RetraitManager;
import fr.eni.enchere.bll.impl.ArticleManagerImpl;
import fr.eni.enchere.bll.impl.RetraitManagerImpl;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class PageEncherir
 */
@WebServlet("/PageEncherirServlet")
public class PageEncherirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleManager manager = ArticleManagerImpl.getInstance();
	private EnchereManager managerEnchere = EnchereManagerImpl.getInstance();
	private RetraitManager managerRetrait = RetraitManagerImpl.getInstance();
	Article articleRecupere = new Article();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PageEncherirServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		UtilisateurModel model = (UtilisateurModel) request.getSession().getAttribute("model");
		ArticleModel articleModel = (ArticleModel) request.getSession().getAttribute("articleModel");
		RetraitModel retraitModel = (RetraitModel)request.getSession().getAttribute("retraitModel");
		
		String WEBINF = "WEB-INF/PageEncherir.jsp";
		
		
		if (model == null) {
			model = new UtilisateurModel();
		}
		if (articleModel == null) {
			articleModel = new ArticleModel();
		}
		
		if(retraitModel == null) {
			retraitModel = new RetraitModel();
		}
		
		
		
		
		//if(articleRecupere.getNoArticle()== null) {
		Integer noAticleClic = Integer.valueOf(request.getParameter("id"));
		
		try {
			articleRecupere = manager.afficherArticleById(noAticleClic);
			
			Retrait retrait = managerRetrait.getretraitByNoArticle(noAticleClic);
			retraitModel.setRetrait(retrait);
			
			articleModel.setArticle(articleRecupere);
			System.out.println("Dans page enchérir, etat de articleModel :"+articleModel.getArticle().getEtatVente()+
				"et nom etat de articleRecupere: "+articleRecupere.getNomArticle()+articleRecupere.getEtatVente());
//			System.out.println("Dans page enchérir, nom et etat d'article 2 PC Gamer :"+manager.afficherArticleById(1).getNomArticle()+manager.afficherArticleById(1).getEtatVente());
		} catch (BLLException e2) {
			e2.printStackTrace();
		}
		
		
		//System.out.println("voici le retrait model " + retraitModel);
		//System.out.println("voici l'article récupéré" + articleRecupere);
		//System.out.println("dans le IF avant encherir" + articleModel.getArticle());
		//System.out.println("dans le IF avant encherir" + articleModel.getArticle().getNomArticle());
		
	//	}
		
		//System.out.println("avant encherir après le IF" + articleModel.getArticle());
		
		if (request.getParameter("encherir") != null) {

			Integer maProposition = Integer.parseInt(request.getParameter("maProposition"));

			Utilisateur encherisseur = model.getUtilisateur();
			
			Article articleAEncherir = articleRecupere;

		
			//System.out.println("après encherir" + maProposition);
			//System.out.println("après encherir" + encherisseur);
			//System.out.println("après encherir" + articleAEncherir);
			
			try {
				managerEnchere.Encherir(encherisseur, articleAEncherir, maProposition);
			} catch (BLLException e) {
				e.printStackTrace();
			}
			
			
			WEBINF = "/PageListeEnchereMesVentesServlet";
		}
		
		//request.setAttribute("model", model);
		request.setAttribute("articleModel", articleModel);
		request.setAttribute("retraitModel", retraitModel);
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
