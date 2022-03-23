package fr.eni.enchere.bo;

import java.time.LocalDate;

public class Enchere {

	private Integer noEnchere;
	private LocalDate dateEnchere;
	private Integer montantEnchere;
	private boolean remporte = true;

	private Utilisateur utilisateur;
	private Article article;

	public Enchere() {
		// TODO Auto-generated constructor stub
	}

	public Enchere(Integer noEnchere, LocalDate dateEnchere, Integer montantEnchere, Utilisateur utilisateur,
			Article article) {
		super();
		this.noEnchere = noEnchere;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.utilisateur = utilisateur;
		this.article = article;
	}
	

	public Enchere(LocalDate dateEnchere, Integer montantEnchere, Utilisateur utilisateur, Article article) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.utilisateur = utilisateur;
		this.article = article;
	}

	public Integer getNoEnchere() {
		return noEnchere;
	}

	public void setNoEnchere(Integer noEnchere) {
		this.noEnchere = noEnchere;
	}

	public LocalDate getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public Integer getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(Integer montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	
	public boolean isRemporte() {
		return remporte;
	}

	public void setRemporte(boolean remporte) {
		this.remporte = remporte;
	}
	
	@Override
	public String toString() {
		return String.format("Enchere [noEnchere=%s, dateEnchere=%s, montantEnchere=%s, utilisateur=%s, article=%s]",
				noEnchere, dateEnchere, montantEnchere, utilisateur, article);
	}



		
}
