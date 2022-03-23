package fr.eni.enchere.dal;

import java.util.List;
import fr.eni.enchere.bo.Enchere;

public interface EnchereDAO {

	
	public Enchere insert(Enchere enchere) throws DALException;
	
	public void update(Enchere enchere) throws DALException;
	
	public void delete(Integer noEnchere)throws DALException;
	
	public Enchere getById(Integer noEnchere) throws DALException;
	
	public List<Enchere> getAll() throws DALException;
	
	public List<Enchere> getByEncherisseur(Integer noUtilisateur) throws DALException;
	
	public List<Enchere> getRemporteParEncherisseur(Integer noEnchere) throws DALException;
	
	public List<Enchere> getAllByArticle(Integer noArticle) throws DALException;
	
	public List<Enchere> selectByNomArticle(String libelle) throws DALException;

	List<Enchere> getAllEnchereTermines(Integer noArticle) throws DALException;
	
}
