package fr.eservices.honey.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import fr.eservices.honey.model.HoneyDao;
import fr.eservices.honey.model.HoneyPot;
import fr.eservices.honey.model.HoneyPotMemoryDao;


@Configuration
@ComponentScan(basePackages= {"fr.eservices.honey.ctrl"} )
@EnableWebMvc
public class PotCreator {
	
	
	@Bean
	HoneyDao getDao() {
		return new HoneyPotMemoryDao();
	}
	
	
	@Autowired
	HoneyDao dao;
	
	public void initializePots( int numberOfPots ) {
		for ( int i = 0; i < numberOfPots; i++ ) {
			HoneyPot pot = new HoneyPot();
			pot.setQuantity( (int) (Math.random() * 1000) );
			dao.save(pot);
		}
	}
	
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register( PotCreator.class );
		
		PotCreator potCreator = context.getBean( PotCreator.class );
		potCreator.initializePots(10);
		
		System.out.println("Ok !");
		context.close();
	}
	

}
