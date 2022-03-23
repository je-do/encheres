
package fr.eni.enchere.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.ArticleDAO;
import fr.eni.enchere.dal.CategorieDAO;
import fr.eni.enchere.dal.DALException;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.UtilisateurDAO;

/**
 * Classe en charge de
 * 
 * @author jdonal2021
 * @date 13 janv. 2022 - 10:17:25
 * @version Enchere - V0.1
 */
public class ArticleDAOJdbcImpl implements ArticleDAO {

	private final static String INSERT = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres,"
			+ " date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, no_retrait) VALUES (?,?,?,?,?,?,?,?,?)";

	private static final String UPDATE = "UPDATE ARTICLES_VENDUS SET nom_article=?, description=?, date_debut_encheres=?,"
			+ " date_fin_encheres=?, prix_initial=?, prix_vente=?, etat_vente=? WHERE no_article=?";

	private final static String DELETE = "DELETE FROM ARTICLES_VENDUS WHERE no_utilisateur=?";

	private final static String SELECT_BY_NO_ARTICLE = "SELECT * FROM ARTICLES_VENDUS WHERE no_article=?";

	private final static String SELECT_ALL = "SELECT * FROM ARTICLES_VENDUS";

	private final static String SELECT_BY_UTILISATEUR = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur=?";

	private final static String SELECT_BY_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS WHERE no_categorie=?";

	private final static String SELECT_BY_NOM_ARTICLE = "SELECT * FROM ARTICLES_VENDUS WHERE nom_article LIKE ?";

	private final static String SELECT_BY_ETATCOMMENCE = "SELECT * FROM ARTICLES_VENDUS WHERE etat_vente ='commence'";

	private final static String SELECT_BY_ETAT_BY_UTILISATEUR = "SELECT * FROM ARTICLES_VENDUS WHERE etat_vente=? AND no_utilisateur=?";


	@Override
	public void insert(Article nouvelArticle) throws DALException {

		try (Connection con = JdbcTools.getConnection()) {

			PreparedStatement pStmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, nouvelArticle.getNomArticle());
			pStmt.setString(2, nouvelArticle.getDescription());
			pStmt.setDate(3, Date.valueOf(nouvelArticle.getDateDebutEncheres()));
			pStmt.setDate(4, Date.valueOf(nouvelArticle.getDateFinEncheres()));
			pStmt.setInt(5, nouvelArticle.getPrixInitial());
			pStmt.setInt(6, nouvelArticle.getPrixDeVente());
			pStmt.setInt(7, nouvelArticle.getUtilisateur().getNoUtilisateur());
			pStmt.setInt(8, nouvelArticle.getCategorie().getNoCategorie());
			pStmt.setInt(9, nouvelArticle.getNoRetrait());

			pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				nouvelArticle.setNoArticle(id);
				nouvelArticle.setNoRetrait(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}

	}

	@Override
	public void update(Article articleAModifier) throws DALException {

		try (Connection cnx = JdbcTools.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(UPDATE);
			pStmt.setString(1, articleAModifier.getNomArticle());
			pStmt.setString(2, articleAModifier.getDescription());
			pStmt.setDate(3, Date.valueOf(articleAModifier.getDateDebutEncheres()));
			pStmt.setDate(4, Date.valueOf(articleAModifier.getDateFinEncheres()));
			pStmt.setInt(5, articleAModifier.getPrixInitial());
			pStmt.setInt(6, articleAModifier.getPrixDeVente());
			pStmt.setString(7, articleAModifier.getEtatVente());
			pStmt.setInt(8, articleAModifier.getNoArticle());

			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}

	}

	@Override
	public void delete(Integer noArticle) throws DALException {
		try (Connection cnx = JdbcTools.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(DELETE);
			stmt.setInt(1, noArticle);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}

	}

	@Override
	public Article selectById(Integer noArticle) throws DALException {
		Article article = null;

		try (Connection cnx = JdbcTools.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_NO_ARTICLE);
			stmt.setInt(1, noArticle);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				article = map(rs);

			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DALException(e.getMessage());
		}

		return article;
	}

	@Override
	public List<Article> selectAll() throws DALException {
		List<Article> listeArticles = new ArrayList<Article>();

		try (Connection cnx = JdbcTools.getConnection()) {
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			while (rs.next()) {
				Article article = map(rs);
				listeArticles.add(article);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());

		}
		return listeArticles;

	}

	@Override
	public List<Article> selectByUtilisateurVendeur(Utilisateur utilisateur) throws DALException {
		List<Article> listeArticles = new ArrayList<Article>();

		try (Connection cnx = JdbcTools.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_UTILISATEUR);
			Integer no_utilisateur = utilisateur.getNoUtilisateur();
			pStmt.setInt(1, no_utilisateur);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				Article article = map(rs);
				listeArticles.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}

		return listeArticles;

	}

	@Override
	public List<Article> selectByCategorie(Categorie categorie) throws DALException {
		List<Article> listeArticles = new ArrayList<Article>();

		try (Connection cnx = JdbcTools.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_CATEGORIE);
			Integer no_categorie = categorie.getNoCategorie();
			pStmt.setInt(1, no_categorie);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				Article article = map(rs);
				listeArticles.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}

		return listeArticles;

	}

	@Override
	public List<Article> selectByNomArticle(String libelle) throws DALException {

		List<Article> articles = new ArrayList<Article>();

		try (Connection cnx = JdbcTools.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_NOM_ARTICLE);
			pStmt.setString(1, '%' + libelle + '%');
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				Article article = map(rs);
				articles.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}

		return articles;
	}

	public List<Article> selectByEtatCommence() throws DALException {

		List<Article> ListeArticlesEtatCommence = new ArrayList<Article>();

		try (Connection cnx = JdbcTools.getConnection()) {
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_BY_ETATCOMMENCE);

			while (rs.next()) {
				Article articleEtatCommence = map(rs);
				ListeArticlesEtatCommence.add(articleEtatCommence);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}

		return ListeArticlesEtatCommence;
	}

	public List<Article> selectByEtat(Article article) throws DALException {
		List<Article> listeArticles = new ArrayList<Article>();

		try (Connection cnx = JdbcTools.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_ETAT_BY_UTILISATEUR);
			Integer noUtilisateur = article.getUtilisateur().getNoUtilisateur();
			String etatVente = article.getEtatVente();
			
			pStmt.setString(1, etatVente);
			pStmt.setInt(2,noUtilisateur);
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				Article articleEtatSouhaite = map(rs);
				listeArticles.add(articleEtatSouhaite);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listeArticles;
	}

	private Utilisateur getUtilisateurArticle(Integer noUtilisateur) {
		UtilisateurDAO utilisateurDAO = DAOFactory.getInstance();
		Utilisateur utilisateur = null;
		try {
			utilisateur = utilisateurDAO.selectById(noUtilisateur);
		} catch (DALException e) {

			e.printStackTrace();
		}

		return utilisateur;

	}

	private Categorie getCategorieArticle(Integer noCategorie) {
		CategorieDAO categorieDAO = DAOFactory.getInstanceCategorie();
		Categorie categorie = null;
		try {
			categorie = categorieDAO.selectById(noCategorie);
		} catch (DALException e) {

			e.printStackTrace();
		}

		return categorie;

	}

	private Article map(ResultSet rs) throws SQLException {
		Integer noArticle = rs.getInt("no_article");
		String nomArticle = rs.getString("nom_article");
		String description = rs.getString("description");
		LocalDate dateDebutEncheres = rs.getDate("date_debut_encheres").toLocalDate();
		LocalDate dateFinEncheres = rs.getDate("date_fin_encheres").toLocalDate();
		Integer prixInitial = rs.getInt("prix_initial");
		Integer prixDeVente = rs.getInt("prix_vente");
		Utilisateur utilisateur = this.getUtilisateurArticle(rs.getInt("no_utilisateur"));
		Categorie categorie = this.getCategorieArticle(rs.getInt("no_categorie"));
		String etatVente = rs.getString("etat_vente");

//		Integer noUtilisateur = rs.getInt("no_utilisateur");
//		Integer noCategorie = rs.getInt("no_categorie");

		Article article = null;

		article = new Article(noArticle, nomArticle, description, dateDebutEncheres, dateFinEncheres, prixInitial,
				prixDeVente, utilisateur, categorie, etatVente);

		return article;
	}

}
