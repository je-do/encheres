/**
 * 
 */
package fr.eni.enchere.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.CategorieDAO;
import fr.eni.enchere.dal.DALException;

/**
 * Classe en charge de 
 * @author jdonal2021
 * @date 14 janv. 2022 - 01:09:23
 * @version Enchere - V0.1  
 */
public class CategorieDAOJdbcImpl implements CategorieDAO {
	
	
	
	private final static String SELECT_BY_NO_CATEGORIE = "SELECT * FROM CATEGORIES WHERE no_categorie=?";

	private final static String SELECT_ALL = "SELECT * FROM CATEGORIES";
	
	private final static String SELECT_BY_LIBELLE = "SELECT * FROM CATEGORIES WHERE libelle=?";
	
	
	@Override
	public void insert(Categorie nouvelleCategorie) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Categorie categorieAModifier) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer noCategorie) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public Categorie selectById(Integer noCategorie) throws DALException {
		
		Categorie categorie = null;

		try (Connection cnx = JdbcTools.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_NO_CATEGORIE);
			stmt.setInt(1, noCategorie);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				categorie = map(rs);

			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DALException(e.getMessage());
		}
		
		
		return categorie;
	}

	@Override
	public Categorie selectByLibelle(String libelle) throws DALException {
		Categorie categorie = null;

		try (Connection cnx = JdbcTools.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_LIBELLE);
			stmt.setString(1, libelle);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				categorie = map(rs);

			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DALException(e.getMessage());
		}

		return categorie;
	}
		
		
	

	@Override
	public List<Categorie> selectAll() throws DALException {
		List<Categorie> listeCategories = new ArrayList<Categorie>();

		try (Connection cnx = JdbcTools.getConnection()) {
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			while (rs.next()) {
				Categorie categorie  = map(rs);
				listeCategories.add(categorie);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());

		}
		return listeCategories;
		
	}
	

	private Categorie map(ResultSet rs) throws SQLException {
		Integer noCategorie = rs.getInt("no_categorie");
		String libelle = rs.getString("libelle");
		
		Categorie categorie = null;

		categorie = new Categorie(libelle, noCategorie);

		return categorie;
	}
	
	
	
}
