package fr.eservices.drive.dao;

import java.util.Date;
import java.util.List;

import fr.eservices.drive.model.Article;
import fr.eservices.drive.model.Category;
import fr.eservices.drive.model.Perishable;

public interface CatalogDao {
	
	/**
	 * List categories, ordered by order_idx
	 * 
	 * @return list of all categories
	 */
	List<Category> getCategories( );
	
	/**
	 * List categories associated to an article
	 * 
	 * @return categories of article
	 */
	List<Category> getArticleCategories( int id );
	
	/**
	 * List all articles in a category
	 * 
	 * @return articles
	 */
	List<Article> getCategoryContent( );
	
	/**
	 * List perished article considering a defined day.
	 * 
	 * @param day for perishable date to compare with
	 * @return perished article
	 */
	List<Perishable> getPerished( Date day );
	

}
