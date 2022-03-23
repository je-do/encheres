package fr.eni.enchere.dal;

import java.util.List;


import fr.eni.enchere.bo.Categorie;


public interface CategorieDAO {

	void insert(Categorie nouvelleCategorie) throws DALException;

	void update(Categorie categorieAModifier) throws DALException;

	void delete(Integer noCategorie) throws DALException;

	Categorie selectById(Integer noCategorie) throws DALException;

	Categorie selectByLibelle(String libelle) throws DALException;

	List<Categorie> selectAll() throws DALException;

}
