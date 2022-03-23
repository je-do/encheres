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
 * Servlet implementation class EssaiServlet
 */
@WebServlet("/EssaiServlet")
public class EssaiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ArticleManager managerArticle = ArticleManagerImpl.getInstance();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EssaiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		System.out.println("Je suis arrivé dans la nouvelle servlet");
		
		System.out.println("Recupération de l'id après clic du lien: " +request.getParameter("id"));
		Integer noAticleClic = Integer.valueOf(request.getParameter("id"));
		
		Article articleRecupere= new Article();
		try {
			articleRecupere = managerArticle.afficherArticleById(noAticleClic);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(articleRecupere);
		
		
		
//		if(request.getParameter("id") != null) {
//		Integer noAticleClic = Integer.valueOf(request.getParameter("id"));
//		System.out.println("noArticle récupéré après avoir cliqué sur une lien: "+noAticleClic);
//		}
//		Article articleClique;
//		try {
//			articleClique = managerArticle.afficherArticleById(noAticleClic);
//			System.out.println(articleClique.getNoArticle());
//			modelArticle.setArticle(articleClique);
//		} catch (BLLException e) {
//			e.printStackTrace();
//		}
		
		
		
		
		
		request.getRequestDispatcher("WEB-INF/EssaiJSP.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
