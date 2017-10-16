package testing;

import java.lang.Thread.UncaughtExceptionHandler;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.eservices.drive.dao.CatalogDao;
import fr.eservices.drive.dao.CatalogDaoJPAImpl;
import fr.eservices.drive.model.Category;

public class AppTestDao {
	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myApp");
		EntityManager em = emf.createEntityManager();
		
		Thread.setDefaultUncaughtExceptionHandler( new UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				emf.close();
			}
		});
		
		CatalogDao dao = new CatalogDaoJPAImpl(em);
		
		System.out.println("List of categories");
		for ( Category cat : dao.getCategories() ) {
			System.out.println( cat );
		}
		
		emf.close();
	}

}
