/**
 * 
 */
package fr.eni.enchere.bll;

import java.util.List;


import fr.eni.enchere.bo.Categorie;


/**
 * Classe en charge de 
 * @author jdonal2021
 * @date 14 janv. 2022 - 13:21:11
 * @version Enchere - V0.1  
 */
public interface CategorieManager {

	
	public void addCategorie(Categorie nouvelleCategorie) throws BLLException;
	
	public void supprimerCategorie(Integer noCategorie) throws BLLException;
	
	public void modifierCategorie(Categorie categorieAModifier) throws BLLException;
	
	public Categorie categorieById (Integer noCategorie) throws BLLException;
	
	public Categorie categorieByLibelle (String libelle) throws BLLException;
	
	public List<Categorie> getAllCategorie() throws BLLException;
	

	
}
