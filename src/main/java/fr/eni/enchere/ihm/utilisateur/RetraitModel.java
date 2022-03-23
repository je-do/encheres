package fr.eni.enchere.ihm.utilisateur;

import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;

public class RetraitModel {

	
	private Retrait retrait;
	private List<Retrait> listeRetraits = new ArrayList<Retrait>();
	/**
	 * @return the retrait
	 */
	public Retrait getRetrait() {
		return retrait;
	}
	/**
	 * @param retrait the retrait to set
	 */
	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}
	/**
	 * @return the listeRetraits
	 */
	public List<Retrait> getListeRetraits() {
		return listeRetraits;
	}
	/**
	 * @param listeRetraits the listeRetraits to set
	 */
	public void setListeRetraits(List<Retrait> listeRetraits) {
		this.listeRetraits = listeRetraits;
	}
	public RetraitModel(Retrait retrait, List<Retrait> listeRetraits) {
		super();
		this.retrait = retrait;
		this.listeRetraits = listeRetraits;
	}
	public RetraitModel() {
		super();
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RetraitModel [retrait=");
		builder.append(retrait);
		builder.append(", listeRetraits=");
		builder.append(listeRetraits);
		builder.append("]");
		return builder.toString();
	}
	
}
