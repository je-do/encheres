/**
 * 
 */
package fr.eni.enchere.bll.impl;

import java.util.List;

import fr.eni.enchere.bll.BLLException;
import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bll.ParameterException;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.dal.CategorieDAO;
import fr.eni.enchere.dal.DALException;
import fr.eni.enchere.dal.DAOFactory;

/**
 * Classe en charge de
 * 
 * @author jdonal2021
 * @date 14 janv. 2022 - 13:35:20
 * @version Enchere - V0.1
 */
public class CategorieManagerImpl implements CategorieManager {

	/** SINGLETON **/
	private static class CategorieManagerHolder {
		private static CategorieManager instance = new CategorieManagerImpl();
	}

	private CategorieManagerImpl() {
	}

	public static CategorieManager getInstance() {
		return CategorieManagerHolder.instance;
	}

	/** FIN SINGLETON **/

	private static CategorieDAO dao = DAOFactory.getInstanceCategorie();

	@Override
	public void addCategorie(Categorie nouvelleCategorie) throws BLLException {
		BLLException be = new BLLException();

		validationLibelle(nouvelleCategorie.getLibelle(), be);

		if (be.hasErreur()) {

			throw be;
		}

		try {
			dao.insert(nouvelleCategorie);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
	}

	/**
	 * Méthode en charge de
	 * 
	 * @param libelle
	 * @param be
	 */
	private void validationLibelle(String libelle, BLLException be) {
		if (libelle == null || libelle.isBlank() || libelle.length() > 30) {
			be.ajouterErreur(new ParameterException("Le nom Article est supérieur à 30 caractères"));
		}

	}

	@Override
	public void supprimerCategorie(Integer noCategorie) throws BLLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void modifierCategorie(Categorie categorieAModifier) throws BLLException {
		// TODO Auto-generated method stub

	}

	@Override
	public Categorie categorieById(Integer noCategorie) throws BLLException {
		try {
			return dao.selectById(noCategorie);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
		
	}

	@Override
	public Categorie categorieByLibelle(String libelle) throws BLLException {
		
		try {
			return dao.selectByLibelle(libelle);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
	
	}

	@Override
	public List<Categorie> getAllCategorie() throws BLLException {

		try {
			return dao.selectAll();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
	}

}
