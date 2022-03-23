package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bo.Retrait;

public interface RetraitDAO {

	void insert(Retrait nouveuRetrait) throws DALException;
	
	void update (Retrait retraitAModifier) throws DALException;
	
	void delete(Integer noArticle) throws DALException;
	
	Retrait retraitByNoArticle (Integer noArticle) throws DALException;
	
}
