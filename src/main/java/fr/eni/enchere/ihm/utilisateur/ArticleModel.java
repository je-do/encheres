/**
 * 
 */
package fr.eni.enchere.ihm.utilisateur;

import java.util.List;
import java.util.ArrayList;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Retrait;

/**
 * Classe en charge de 
 * @author jdonal2021
 * @date 16 janv. 2022 - 15:45:07
 * @version Enchere - V0.1  
 */
public class ArticleModel {

	private Article article;
	private Retrait retrait;
	private List<Article> listeArticles = new ArrayList<Article>();
	

	public Retrait getRetrait() {
		return retrait;
	}
	
	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public List<Article> getListeArticles() {
		return listeArticles;
	}
	public void setListeArticles(List<Article> listeArticles) {
		this.listeArticles = listeArticles;
	}

	public ArticleModel(Article article, Retrait retrait, List<Article> listeArticles) {
		super();
		this.article = article;
		this.retrait = retrait;
		this.listeArticles = listeArticles;
	}

	public ArticleModel() {
		super();
	}

	public ArticleModel(Retrait retrait, List<Article> listeArticles) {
		super();
		this.retrait = retrait;
		this.listeArticles = listeArticles;
	}
	
	
	
}
