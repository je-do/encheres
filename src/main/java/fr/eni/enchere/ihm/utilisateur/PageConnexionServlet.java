package fr.eni.enchere.ihm.utilisateur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.bll.BLLException;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bll.impl.UtilisateurManagerImpl;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class PageConnexionServlet
 */
@WebServlet("/PageConnexionServlet")
public class PageConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager manager = UtilisateurManagerImpl.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PageConnexionServlet() {
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

		String WEBINF = "WEB-INF/PageConnexion.jsp";
		if (model == null) {
			model = new UtilisateurModel();
		}

		Utilisateur utilisateurRecupere = new Utilisateur();

		if (request.getParameter("Connexion") != null) {

			//System.out.println("J'ai appuyé sur connection");
			String identifiant = request.getParameter("identifiant");
			String motDePasse = request.getParameter("motDePasse");
			String message = null;

			try {
				utilisateurRecupere = UtilisateurManagerImpl.validerLoginUtilisateur(identifiant, motDePasse);
			} catch (BLLException e) {
				e.printStackTrace();
			}

			if (utilisateurRecupere.getNoUtilisateur() != null) {

				/* l'utilisateur validé passe dans le ModelUtilisateur */
				model.setUtilisateur(utilisateurRecupere);

				/* Création d'un objet session ou récupération d'une session */
				HttpSession session = request.getSession();

				/* Mise en session du modelUtilisateur */
				request.getSession().setAttribute("model", model);

				WEBINF = "/PageListeEnchereMesVentesServlet";

				System.out.println("Connection réussie");

			}else {
				System.out.println("Connection échouée");
			}
			
		}

		if (request.getParameter("CreerUnCompte") != null) {
			WEBINF = "UtilisateurCreerServlet";
			System.out.println("J'ai appuyé sur créer un compte");
		}
		
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
