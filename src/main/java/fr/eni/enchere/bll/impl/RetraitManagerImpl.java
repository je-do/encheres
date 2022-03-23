package fr.eni.enchere.bll.impl;

import fr.eni.enchere.bll.BLLException;
import fr.eni.enchere.bll.ParameterException;
import fr.eni.enchere.bll.RetraitManager;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.dal.DALException;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.RetraitDAO;

public class RetraitManagerImpl implements RetraitManager {

	/** SINGLETON **/
	private static class RetraitManagerHolder {
		private static RetraitManager instance = new RetraitManagerImpl();
	}

	private RetraitManagerImpl() {
	}

	public static RetraitManager getInstance() {
		return RetraitManagerHolder.instance;

	}

	// FIN SINGLETON
	private static RetraitDAO dao = DAOFactory.getInstanceRetrait();

	@Override
	public void ajouterRetrait(Retrait retrait) throws BLLException {

		BLLException be = new BLLException();

		validationRue(retrait.getRue(), be);
		validationCodePostal(retrait.getCodePostal(), be);
		validationVille(retrait.getVille(), be);

		if (be.hasErreur()) {

			throw be;
		}

		try {
			dao.insert(retrait);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
	}

	@Override
	public void modifierRetrait(Retrait retraitAModifier) throws BLLException {

		BLLException be = new BLLException();

		validationNoRetrait(retraitAModifier.getArticle().getNoRetrait(), be);
		validationRue(retraitAModifier.getRue(), be);
		validationCodePostal(retraitAModifier.getCodePostal(), be);
		validationVille(retraitAModifier.getVille(), be);
		if (be.hasErreur()) {
			throw be;
		}

		try {
			dao.update(retraitAModifier);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
	}

	@Override
	public void supprimerRetrait(Integer noRetrait) throws BLLException {
		BLLException be = new BLLException();

		validationNoRetrait(noRetrait, be);

		if (be.hasErreur()) {
			throw be;
		}

		try {
			dao.delete(noRetrait);
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Retrait getretraitByNoArticle(Integer noArticle) throws BLLException {
				
			try {
				
				return dao.retraitByNoArticle(noArticle);
			
			} catch (DALException e) {

				e.printStackTrace();
				throw new BLLException(e);
			}
		
	}

	private void validationNoRetrait(Integer noRetrait, BLLException be) {
		if (noRetrait == null || noRetrait < 0) {
			be.ajouterErreur(new ParameterException("Le numéro Retrait est inférieur à 0"));
		}
	}

	private void validationRue(String rue, BLLException be) {
		if (rue == null || rue.isBlank() || rue.length() > 30) {
			be.ajouterErreur(new ParameterException("Le nom Article est supérieur à 30 caractères"));
		}
	}

	private void validationCodePostal(String codePostal, BLLException be) {
		if (codePostal == null || codePostal.isBlank() || codePostal.length() > 30) {
			be.ajouterErreur(new ParameterException("Le code postal est supérieur à 5 caractères"));
		}

	}

	private void validationVille(String ville, BLLException be) {
		if (ville == null || ville.isBlank() || ville.length() > 30) {
			be.ajouterErreur(new ParameterException("Le nom ville est supérieur à 30 caractères"));
		}
	}

}
