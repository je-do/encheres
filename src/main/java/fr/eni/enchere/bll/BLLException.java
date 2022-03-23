package fr.eni.enchere.bll;

import java.util.ArrayList;
import java.util.List;

public class BLLException extends Exception {

	private List<Exception> erreurs = new ArrayList<Exception>();
	
	public void ajouterErreur(Exception e) {
		erreurs.add(e);
	}
	
	public boolean hasErreur() {
		return !erreurs.isEmpty();
	}
	
	
	public BLLException() {
		super();
	}

	public BLLException(Exception e) {
		this();
		erreurs.add(e);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("BLLException");
		sb.append(System.lineSeparator());
		for(Exception e : erreurs) {
			sb.append(e.getMessage())
				.append(System.lineSeparator());
		}
		return sb.toString();
	}
	
	
}
