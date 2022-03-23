package fr.eni.enchere.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.DALException;
import fr.eni.enchere.dal.UtilisateurDAO;


public class UtilisateurDAOImpl implements UtilisateurDAO {

	
	private final static String INSERT = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	private final static String SELECT_BY_NO_UTILISATEUR = "SELECT * FROM UTILISATEURS WHERE no_utilisateur=?";
	private final static String DELETE = "DELETE FROM UTILISATEURS WHERE no_utilisateur=?";
	private final static String UPDATE = "UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=?"
			+ ", credit=? WHERE no_utilisateur=?";
	private final static String SELECT_ALL= "SELECT * FROM UTILISATEURS" ; 

	@Override
	public void insert(Utilisateur nouvelUtilisateur) throws DALException {

		try (Connection con = JdbcTools.getConnection()) {

			PreparedStatement stmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, nouvelUtilisateur.getPseudo());
			stmt.setString(2, nouvelUtilisateur.getNom());
			stmt.setString(3, nouvelUtilisateur.getPrenom());
			stmt.setString(4, nouvelUtilisateur.getEmail());
			stmt.setString(5, nouvelUtilisateur.getTelephone());
			stmt.setString(6, nouvelUtilisateur.getRue());
			stmt.setString(7, nouvelUtilisateur.getCodePostal());
			stmt.setString(8, nouvelUtilisateur.getVille());
			stmt.setString(9, nouvelUtilisateur.getMotDePasse());
			stmt.setInt(10, nouvelUtilisateur.getCredit());
			stmt.setInt(11, nouvelUtilisateur.getAdministrateur());
			

			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				nouvelUtilisateur.setNoUtilisateur(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}

	}

	@Override
	public void update(Utilisateur utilisateurAModifier) throws DALException {
			
		try(Connection cnx = JdbcTools.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(UPDATE);
			
			stmt.setString(1, utilisateurAModifier.getPseudo());
			stmt.setString(2, utilisateurAModifier.getNom());
			stmt.setString(3, utilisateurAModifier.getPrenom());
			stmt.setString(4, utilisateurAModifier.getEmail());
			stmt.setString(5, utilisateurAModifier.getTelephone());
			stmt.setString(6, utilisateurAModifier.getRue());
			stmt.setString(7, utilisateurAModifier.getCodePostal());
			stmt.setString(8, utilisateurAModifier.getVille());
			stmt.setString(9, utilisateurAModifier.getMotDePasse());
			stmt.setInt(10, utilisateurAModifier.getCredit());
			stmt.setInt(11, utilisateurAModifier.getNoUtilisateur());
			
			stmt.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
		throw new DALException(e.getMessage());
		
	}
	}

	@Override
	public void delete(Integer noUtilisateur) throws DALException {
		try(Connection cnx = JdbcTools.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(DELETE);
			stmt.setInt(1, noUtilisateur);
			stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}
	}

	@Override
	public List<Utilisateur> selectAll() throws DALException {
		List<Utilisateur> lstUtilisateurs = new ArrayList<Utilisateur>();
		
		try(Connection cnx = JdbcTools.getConnection()){
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			while(rs.next()) {
				Utilisateur utilisateur = map(rs);
				lstUtilisateurs.add(utilisateur);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
			
		}	
		return lstUtilisateurs;
	}

	@Override
	public Utilisateur selectById(Integer noUtilisateur) throws DALException {
		Utilisateur utilisateur = null;

		try (Connection cnx = JdbcTools.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_NO_UTILISATEUR);
			stmt.setInt(1, noUtilisateur);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				utilisateur = map(rs);

			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DALException(e.getMessage());
		}

		return utilisateur;
	}

	private Utilisateur map(ResultSet rs) throws SQLException {
		int noUtilisateur = rs.getInt("no_utilisateur");
		String pseudo = rs.getString("pseudo");
		String nom = rs.getString("nom");
		String prenom = rs.getString("prenom");
		String email = rs.getString("email");
		String telephone = rs.getString("telephone");
		String rue = rs.getString("rue");
		String codePostal = rs.getString("code_postal");
		String ville = rs.getString("ville");
		String motDePasse = rs.getString("mot_de_passe");
		int credit = rs.getInt("credit");

		Utilisateur utilisateur = null;

		utilisateur = new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville,
				motDePasse, credit);

		return utilisateur;
	}
		
}
