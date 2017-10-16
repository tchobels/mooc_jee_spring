package fr.eservices.drive.dao;

import java.util.Date;
import java.util.List;

import fr.eservices.drive.model.Article;
import fr.eservices.drive.model.Category;
import fr.eservices.drive.model.Perishable;

public class CatalogDaoJPAImpl implements CatalogDao {

	@Override
	public List<Category> getCategories() {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public List<Category> getArticleCategories(int id) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public List<Article> getCategoryContent() {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public List<Perishable> getPerished(Date day) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not yet implemented");
	}

	
}
