package fr.eni.enchere.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.ArticleDAO;
import fr.eni.enchere.dal.DALException;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.RetraitDAO;
import fr.eni.enchere.dal.UtilisateurDAO;

public class RetraitDAOImpl implements RetraitDAO {


	private final static String INSERT = "INSERT INTO RETRAITS (rue, code_postal, ville, no_article) VALUES (?,?,?,?)";
	private final static String DELETE = "DELETE FROM RETRAITS WHERE no_article=?";
	private final static String UPDATE = "UPDATE RETRAITS SET rue=?, code_postal=?, ville=? WHERE no_article=?";
	private final static String SELECT_RETRAITS_BY_NO_ARTICLE = "SELECT * FROM RETRAITS WHERE no_article=?";

	@Override
	public void insert(Retrait nouveauRetrait) throws DALException {

		try (Connection con = JdbcTools.getConnection()) {

			PreparedStatement stmt = con.prepareStatement(INSERT);
			stmt.setString(1, nouveauRetrait.getRue());
			stmt.setString(2, nouveauRetrait.getCodePostal());
			stmt.setString(3, nouveauRetrait.getVille());
			stmt.setInt(4, nouveauRetrait.getArticle().getNoArticle());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}

	}

	@Override
	public void update(Retrait retraitAModifier) throws DALException {

		try (Connection cnx = JdbcTools.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(UPDATE);

			stmt.setString(1, retraitAModifier.getRue());
			stmt.setString(2, retraitAModifier.getCodePostal());
			stmt.setString(3, retraitAModifier.getVille());

			stmt.setInt(4, retraitAModifier.getArticle().getNoArticle());

			stmt.executeUpdate();
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
	public Retrait retraitByNoArticle(Integer noArticle) throws DALException {
		Retrait retrait = null;

		try (Connection cnx = JdbcTools.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SELECT_RETRAITS_BY_NO_ARTICLE);
			stmt.setInt(1, noArticle);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				retrait = map(rs);

			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DALException(e.getMessage());
		}

		return retrait;
	}
	
	private Retrait map(ResultSet rs) throws SQLException {

		String rue = rs.getString("rue");
		String codePostal = rs.getString("code_postal");
		String ville = rs.getString("ville");
		
		Article article = this.getNoArticle(rs.getInt("no_article"));

		Retrait retrait = null;

		retrait = new Retrait (rue, codePostal, ville, article);

		return retrait;
	}
	
	
	private Article getNoArticle(Integer noArticle) {
		ArticleDAO articleDAO = DAOFactory.getInstanceArticle();
		Article article = null;
		try {
			article = articleDAO.selectById(noArticle);
		} catch (DALException e) {

			e.printStackTrace();
		}

		return article;

	}

}
