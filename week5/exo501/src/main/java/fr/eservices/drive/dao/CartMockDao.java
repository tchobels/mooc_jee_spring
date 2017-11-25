package fr.eservices.drive.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import fr.eservices.drive.model.Article;
import fr.eservices.drive.model.Cart;

@Component
@Qualifier("mock")
public class CartMockDao implements CartDao {
	
	@Override
	public Cart getCartContent(int id) throws DataException {
		
		if ( id < 0 ) throw new DataException("No Such Cart");
		
		Cart c = new Cart();
		List<Article> arts = new ArrayList<>();
		
		{
			Article a = new Article();
			a.setId("10101010");
			a.setName("Boisson énergétique");
			a.setPrice("2,99");
			a.setImg("https://static1.chronodrive.com/img/PM/P/0/76/0P_61276.gif");
			arts.add( a );
		}
		
		{
			Article a = new Article();
			a.setId("10101012");
			a.setName("Papier Cadeau");
			a.setPrice("1,50");
			a.setImg("https://static1.chronodrive.com/img/PM/P/0/72/0P_348972.gif");
			arts.add( a );
		}
		
		{
			Article a = new Article();
			a.setId("10101013");
			a.setName("Pur jus d'orange");
			a.setPrice("2,35");
			a.setImg("https://static1.chronodrive.com/img/PM/P/0/42/0P_40042.gif");
			arts.add( a );
		}
		
		c.setArticles(arts);
		return c;
	}

}
