package fr.eni.enchere.ihm.utilisateur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bll.impl.UtilisateurManagerImpl;

/**
 * Servlet implementation class PageMonProfil
 */
@WebServlet("/PageMonProfilServlet")
public class PageMonProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PageMonProfilServlet() {
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
		String WEBINF = "WEB-INF/PageMonProfil.jsp";

		if (model == null) {
			model = new UtilisateurModel();
		}
		
		//System.out.println("je suis dans la servlet PageMonProfil");
		
		
		if (request.getParameter("Modifier") != null) {
			//System.out.println("J'ai appuyé sur modifier mon profil");
			WEBINF ="PageModifierMonProfilServlet";
			
		}

		
		
		//request.setAttribute("model", model);
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
