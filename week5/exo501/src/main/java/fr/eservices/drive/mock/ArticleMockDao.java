package fr.eservices.drive.mock;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import fr.eservices.drive.dao.ArticleDao;
import fr.eservices.drive.model.Article;

@Component
@Qualifier("mock")
public class ArticleMockDao implements ArticleDao {
	
	private HashMap<String, Article> arts = new HashMap<>();
	
	public ArticleMockDao() {
		
		{
			Article a = new Article();
			a.setId("10101010");
			a.setName("Boisson énergétique");
			a.setPrice(299);
			a.setImg("https://static1.chronodrive.com/img/PM/P/0/76/0P_61276.gif");
			arts.put( a.getId(), a );
		}
		
		{
			Article a = new Article();
			a.setId("10101012");
			a.setName("Papier Cadeau");
			a.setPrice(150);
			a.setImg("https://static1.chronodrive.com/img/PM/P/0/72/0P_348972.gif");
			arts.put( a.getId(), a );
		}
		
		{
			Article a = new Article();
			a.setId("10101013");
			a.setName("Pur jus d'orange");
			a.setPrice(235);
			a.setImg("https://static1.chronodrive.com/img/PM/P/0/42/0P_40042.gif");
			arts.put( a.getId(), a );
		}
		
	}
	

	public void setArticle( String i, Article art) {
		arts.put(i, art);
	}
	
	@Override
	public Article find(String id) {
		return arts.get(id);
	}

}
