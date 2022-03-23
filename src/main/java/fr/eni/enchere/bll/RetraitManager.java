package fr.eni.enchere.bll;

import fr.eni.enchere.bo.Retrait;

public interface RetraitManager {
	
	public void ajouterRetrait(Retrait retrait) throws BLLException;
	
	public void modifierRetrait(Retrait retraitAModifier) throws BLLException;
	
	public void supprimerRetrait (Integer noArticle) throws BLLException;
	
	public Retrait getretraitByNoArticle (Integer noArticle)throws BLLException;
}
