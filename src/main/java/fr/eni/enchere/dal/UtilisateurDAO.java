package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Utilisateur;

public interface UtilisateurDAO {
	
	void insert (Utilisateur nouvelUtilisateur) throws DALException;
	void update (Utilisateur utilisateurAModifier) throws DALException;
	void delete (Integer noUtilisateur) throws DALException;
	List<Utilisateur> selectAll() throws DALException;	
	Utilisateur selectById (Integer noUtilisateur) throws DALException;
	
}
