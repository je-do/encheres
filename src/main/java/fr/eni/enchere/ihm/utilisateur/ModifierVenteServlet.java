package fr.eni.enchere.ihm.utilisateur;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.BLLException;
import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bll.impl.ArticleManagerImpl;
import fr.eni.enchere.bll.impl.CategorieManagerImpl;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ModifierVente
 */
@WebServlet("/ModifierVenteServlet")
public class ModifierVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ArticleManager manager = ArticleManagerImpl.getInstance();
	private CategorieManager managerCat = CategorieManagerImpl.getInstance();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierVenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UtilisateurModel model = (UtilisateurModel) request.getSession().getAttribute("model");

		if (model == null) {
			model = new UtilisateurModel();
		}

		String WEBINF = "WEB-INF/ModifierVente.jsp";
		ArticleModel articleModel = new ArticleModel();

		if (request.getParameter("enregistrer") != null) {

			String nomArticle = request.getParameter("nomArticle");
			String description = request.getParameter("description");
			Integer categorieNumero = Integer.parseInt(request.getParameter("categorieNumero"));
			// PHOTO DE L'Article
			Integer miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
			LocalDate debutEnchere = LocalDate.parse(request.getParameter("debutEnchere"));
			LocalDate finEnchere = LocalDate.parse(request.getParameter("finEnchere"));
			String rueRetrait = request.getParameter("rueRetrait");
			String codePostal = request.getParameter("codePostal");
			String ville = request.getParameter("ville");

			Categorie categorie = null;
			try {
				categorie = managerCat.categorieById(categorieNumero);
			} catch (BLLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			Utilisateur utilisateur = model.getUtilisateur();
			
			Article article = new Article(utilisateur, nomArticle, description, debutEnchere, finEnchere, miseAPrix, categorie);

			System.out.println(article);

			articleModel.setArticle(article);

			try {
				manager.modifierArticle(article);
				System.out.println("Article enregistré");
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// (String nomArticle, String description, LocalDate dateDebutEncheres,
			// LocalDate dateFinEncheres,
			// Integer prixInitial, Categorie categorie, Retrait retrait) {
			
			if (request.getParameter("annulerlavente") != null) {
				
				try {
					manager.supprimerUnArticle(article.getNoArticle());
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
		if (request.getParameter("annuler") != null) {
			WEBINF = "/EssaiServlet";

		}
		request.setAttribute("articleModel", articleModel);
		request.getRequestDispatcher(WEBINF).forward(request, response);

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
