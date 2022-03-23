package fr.eni.enchere.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.dal.ArticleDAO;
import fr.eni.enchere.dal.DALException;
import fr.eni.enchere.dal.EnchereDAO;
import fr.eni.enchere.dal.UtilisateurDAO;

public class EnchereDAOImpl implements EnchereDAO {
	
	private static final String INSERT = "INSERT INTO ENCHERES (date_enchere, montant_enchere, no_article, no_utilisateur, remporte) VALUES(?,?,?,?,?)";
	private static final String GET_ALL = "SELECT * FROM ENCHERES";
	private static final String GET_BY_ID = "SELECT * FROM ENCHERES WHERE no_enchere=?";
	private static final String GET_BY_ENCHERISSEUR = "SELECT * FROM ENCHERES WHERE no_utilisateur=?";
	private static final String GET_REMPORTE_PAR_ENCHERISSEUR = "SELECT * FROM ENCHERES WHERE no_utilisateur=? AND remporte=?";
	private static final String UPDATE = "UPDATE ENCHERES SET date_enchere=?, montant_enchere=?, no_article=?, no_utilisateur=?, remporte=? WHERE no_enchere=?";
	private static final String DELETE = "DELETE ENCHERES WHERE no_enchere=?";
	private static final String GET_ALL_BY_ARTICLE = "SELECT * FROM ENCHERES WHERE no_article=?";
	private final static String SELECT_BY_NOM_ARTICLE = "SELECT * FROM ENCHERES AS e INNER JOIN ARTICLES_VENDUS AS a "
												+ "ON a.no_article = e.no_article WHERE nom_article LIKE ?";
	
	private final static String SELECT_BY_ENCHERE_REMPORTE = "SELECT * FROM ENCHERES AS e INNER JOIN ARTICLES_VENDUS AS a "
			+ "ON a.no_article=e.no_article WHERE  a.etat_vente='termine' AND e.remporte =1 "
			+ "AND e.no_utilisateur=?";
	
	
	private static ArticleDAO article = new ArticleDAOJdbcImpl();
	private static UtilisateurDAO utilisateur = new UtilisateurDAOImpl();

	@Override
	public Enchere insert(Enchere enchere)throws DALException{
		try (Connection con = JdbcTools.getConnection()) {

			PreparedStatement pStmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pStmt.setDate(1, Date.valueOf(enchere.getDateEnchere()));
			pStmt.setInt(2, enchere.getMontantEnchere());
			pStmt.setInt(3, enchere.getArticle().getNoArticle());
			pStmt.setInt(4, enchere.getUtilisateur().getNoUtilisateur());
			pStmt.setBoolean(5, enchere.isRemporte());
			
			pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			
			if (rs.next()) {
				enchere.setNoEnchere(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}
		
		return enchere;

	}	

	@Override
	public void update(Enchere enchere)throws DALException {
		try (Connection con = JdbcTools.getConnection()) {
			PreparedStatement pStmt = con.prepareStatement(UPDATE);
			pStmt.setDate(1, Date.valueOf(enchere.getDateEnchere()));
			pStmt.setInt(2, enchere.getMontantEnchere());
			pStmt.setInt(3, enchere.getArticle().getNoArticle());
			pStmt.setInt(4, enchere.getUtilisateur().getNoUtilisateur());
			pStmt.setBoolean(5, enchere.isRemporte());
			pStmt.setInt(6, enchere.getNoEnchere());
            
			pStmt.executeUpdate();
           
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}


	}

	@Override
	public void delete(Integer noEnchere) throws DALException {
		try (Connection con = JdbcTools.getConnection()) {
			PreparedStatement statement = con.prepareStatement(DELETE);
			statement.setInt(1, noEnchere);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());

		}

	}

	@Override
	public Enchere getById(Integer noEnchere)throws DALException {
		Enchere enchere = null;
		
		try (Connection cnx = JdbcTools.getConnection()) {
			PreparedStatement statement = cnx.prepareStatement(GET_BY_ID);
			statement.setInt(1, noEnchere);
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()) 
			{
				enchere = new Enchere();
				enchere.setNoEnchere(rs.getInt("no_enchere"));
				enchere.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
				enchere.setMontantEnchere(rs.getInt("montant_enchere"));
				enchere.setArticle(article.selectById(rs.getInt("no_article")));
				enchere.setUtilisateur(utilisateur.selectById(rs.getInt("no_utilisateur")));
				enchere.setRemporte(rs.getBoolean("remporte"));
				
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}
				
		return enchere;
	}

	@Override
	public List<Enchere> getAll() throws DALException {
		
		List<Enchere> listeEncheres = new ArrayList<Enchere>();
		
		try (Connection con = JdbcTools.getConnection()) {

			PreparedStatement pStmt = con.prepareStatement(GET_ALL);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				Enchere enchere = new Enchere();
				enchere.setNoEnchere(rs.getInt("no_enchere"));
				enchere.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
				enchere.setMontantEnchere(rs.getInt("montant_enchere"));
				enchere.setArticle(article.selectById(rs.getInt("no_article")));
				enchere.setUtilisateur(utilisateur.selectById(rs.getInt("no_utilisateur")));
				enchere.setRemporte(rs.getBoolean("remporte"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
			}
		return listeEncheres;
	}

	@Override
	public List<Enchere> getByEncherisseur(Integer noUtilisateur) throws DALException {
		List<Enchere> listeEncheres = new ArrayList<>();
		
		try (Connection con = JdbcTools.getConnection()) {
			 PreparedStatement statement = con.prepareStatement(GET_BY_ENCHERISSEUR);
			 statement.setInt(1, noUtilisateur);
			 ResultSet rs = statement.executeQuery();
			 
			while (rs.next()) {
				Enchere enchere = new Enchere();
				enchere.setNoEnchere(rs.getInt("no_enchere"));
				enchere.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
				enchere.setMontantEnchere(rs.getInt("montant_enchere"));
				enchere.setArticle(article.selectById(rs.getInt("no_article")));
				enchere.setRemporte(rs.getBoolean("remporte"));
				
				listeEncheres.add(enchere);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}
		return listeEncheres;

	}

	@Override
	public List<Enchere> getRemporteParEncherisseur(Integer noEnchere) throws DALException {
		List<Enchere> list = new ArrayList<>();
		
		try (Connection con = JdbcTools.getConnection()) {
			 PreparedStatement statement = con.prepareStatement(GET_REMPORTE_PAR_ENCHERISSEUR);
			 statement.setInt(1, noEnchere);
			 statement.setBoolean(2, true);
			 ResultSet rs = statement.executeQuery();
			 
			while (rs.next()) {
				Enchere enchere = new Enchere();
				enchere.setNoEnchere(rs.getInt("no_enchere"));
				enchere.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
				enchere.setMontantEnchere(rs.getInt("montant_enchere"));
				enchere.setArticle(article.selectById(rs.getInt("no_article")));

				list.add(enchere);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}
		return list;
	}

	@Override
	public List<Enchere> getAllByArticle(Integer noArticle) throws DALException {
		
		List<Enchere> encheres = new ArrayList<>();
		
		try (Connection con = JdbcTools.getConnection()) {
			 PreparedStatement statement = con.prepareStatement(GET_ALL_BY_ARTICLE);
			 statement.setInt(1, noArticle);
			
			 ResultSet rs = statement.executeQuery();
			 
			while (rs.next()) {
				Enchere enchere = new Enchere();
				enchere.setNoEnchere(rs.getInt("no_enchere"));
				enchere.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
				enchere.setMontantEnchere(rs.getInt("montant_enchere"));
				enchere.setArticle(article.selectById(rs.getInt("no_article")));
				enchere.setUtilisateur(utilisateur.selectById(rs.getInt("no_utilisateur")));
				enchere.setRemporte(rs.getBoolean("remporte"));
				
				encheres.add(enchere);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}

		return encheres;

	}

	@Override
	public List<Enchere> selectByNomArticle(String libelle) throws DALException {
		List<Enchere> encheres = new ArrayList<>();
		
		try (Connection con = JdbcTools.getConnection()) {
			 PreparedStatement statement = con.prepareStatement(SELECT_BY_NOM_ARTICLE);
			 statement.setString(1, '%'+libelle+'%');
			
			 ResultSet rs = statement.executeQuery();
			 
			while (rs.next()) {
				Enchere enchere = new Enchere();
				enchere.setNoEnchere(rs.getInt("no_enchere"));
				enchere.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
				enchere.setMontantEnchere(rs.getInt("montant_enchere"));
				enchere.setArticle(article.selectById(rs.getInt("no_article")));
				enchere.setUtilisateur(utilisateur.selectById(rs.getInt("no_utilisateur")));
				enchere.setRemporte(rs.getBoolean("remporte"));
				
				encheres.add(enchere);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}

		return encheres;
	}
	
	@Override
	public List<Enchere> getAllEnchereTermines(Integer noUtilisateur) throws DALException {
		
		List<Enchere> listeEncheresTermines = new ArrayList<Enchere>();
		
		try (Connection con = JdbcTools.getConnection()) {

			PreparedStatement pStmt = con.prepareStatement(SELECT_BY_ENCHERE_REMPORTE);
			ResultSet rs = pStmt.executeQuery();
			
			pStmt.setInt(1, noUtilisateur);
			
			while (rs.next()) {
				Enchere enchere = new Enchere();
				enchere.setNoEnchere(rs.getInt("no_enchere"));
				enchere.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
				enchere.setMontantEnchere(rs.getInt("montant_enchere"));
				enchere.setArticle(article.selectById(rs.getInt("no_article")));
				enchere.setUtilisateur(utilisateur.selectById(rs.getInt("no_utilisateur")));
					
				listeEncheresTermines.add(enchere);
			}
		
			} catch (SQLException e) {

				e.printStackTrace();
			}
		return listeEncheresTermines;

}
}
