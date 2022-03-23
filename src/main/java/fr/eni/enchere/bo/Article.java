package fr.eni.enchere.bo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Article {

	private Integer noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private Integer prixInitial;
	private Integer prixDeVente;
	
	private List<Enchere> listeEncheres = new ArrayList<>();
	private Utilisateur utilisateur;
	private Categorie categorie;
	private Retrait retrait;
	
	private String etatVente= "creer";
	private int noRetrait;
	
	

	public Article() {
		// TODO Auto-generated constructor stub
	}
	

	public Article(Integer noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, Integer prixInitial, Integer prixDeVente, Utilisateur utilisateur,
			Categorie categorie, String etatVente) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixDeVente = prixDeVente;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
		this.etatVente = etatVente;
	}

	public Article(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			Integer prixInitial, Categorie categorie, Retrait retrait) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.categorie = categorie;
		this.retrait = retrait;
	}
	

	public Article( Utilisateur utilisateur,String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			Integer prixInitial, Categorie categorie, Retrait retrait) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
		this.retrait = retrait;
		prixDeVente = prixInitial;
	}

	public Article(Utilisateur utilisateur, String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			Integer prixInitial, Categorie categorie) {
		super();
		this.utilisateur = utilisateur;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.categorie = categorie;
		prixDeVente = prixInitial;
		
	}



	public Article(Integer noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, Integer prixInitial,Integer prixDeVente,List<Enchere> listeEncheres, Utilisateur utilisateur, Categorie categorie, Retrait retrait) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixDeVente = prixDeVente;

		this.listeEncheres = listeEncheres;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
		this.retrait = retrait;
		noRetrait = noArticle;
	}


	public Integer getNoArticle() {
		return noArticle;
	}


	public void setNoArticle(Integer noArticle) {
		this.noArticle = noArticle;
	}


	public String getNomArticle() {
		return nomArticle;
	}


	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}


	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}


	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}


	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}


	public Integer getPrixInitial() {
		return prixInitial;
	}


	public void setPrixInitial(Integer prixInitial) {
		this.prixInitial = prixInitial;
	}
	
	public Integer getPrixDeVente() {
		return prixDeVente;
	}
	
	public void setPrixDeVente(Integer prixDeVente) {
		this.prixDeVente = prixDeVente;
	}

	public List<Enchere> getListeEncheres() {
		return listeEncheres;
	}


	public void setListeEncheres(List<Enchere> listeEncheres) {
		this.listeEncheres = listeEncheres;
	}


	public Utilisateur getUtilisateur() {
		return utilisateur;
	}


	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}


	public Categorie getCategorie() {
		return categorie;
	}


	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}


	public Retrait getRetrait() {
		return retrait;
	}


	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}
	




	public Article(Integer noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, Integer prixInitial, Integer prixDeVente, 
			Utilisateur utilisateur, Categorie categorie) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixDeVente = prixDeVente;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
		
	}

	
	public Article(Integer noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, Integer prixInitial, Integer prixDeVente, Integer noUtilisateur, Integer noCategorie) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixDeVente = prixDeVente;
		
		
		
	}
	public Article( String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, Integer prixInitial, Integer prixDeVente, Integer noUtilisateur, Integer noCategorie) {
		super();
		
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixDeVente = prixDeVente;
		
		
		
	}


	public Article(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			Integer prixInitial, Integer prixDeVente, Utilisateur utilisateur, Categorie categorie) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixDeVente = prixDeVente;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
		
		
	}


	 
	public Article(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			Integer prixInitial, Utilisateur utilisateur, Categorie categorie) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
		this.prixDeVente = prixInitial;
		
		
	}
	
	
	

	public String getEtatVente() {
		return etatVente;
	}



	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}



	public int getNoRetrait() {
		return noRetrait;
	}



	public void setNoRetrait(int noRetrait) {
		this.noRetrait = noRetrait;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Article [noArticle=");
		builder.append(noArticle);
		builder.append(", nomArticle=");
		builder.append(nomArticle);
		builder.append(", description=");
		builder.append(description);
		builder.append(", dateDebutEncheres=");
		builder.append(dateDebutEncheres);
		builder.append(", dateFinEncheres=");
		builder.append(dateFinEncheres);
		builder.append(", prixInitial=");
		builder.append(prixInitial);
		builder.append(", prixDeVente=");
		builder.append(prixDeVente);
		builder.append(", utilisateur=");
		builder.append(utilisateur);
		builder.append(", categorie=");
		builder.append(categorie);
		builder.append(", retrait=");
		builder.append(retrait);
		builder.append(", etatVente=");
		builder.append(etatVente);
		builder.append(", noRetrait=");
		builder.append(noRetrait);
		builder.append("]");
		return builder.toString();
	}


	
	
}
