package spring;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ExtendedModelMap;

import fr.eservices.drive.dao.OrderDao;
import fr.eservices.drive.mock.CartMockDao;
import fr.eservices.drive.web.CartController;
import fr.eservices.drive.web.dto.CartEntry;
import fr.eservices.drive.web.dto.SimpleResponse.Status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=CartControllerTest.class)
@Configuration
@ComponentScan(basePackageClasses={CartMockDao.class})
public class CartControllerTest {
	
	/*
	 * Configure Test context
	 */
	
	@Bean
	CartController ctrl() {
		return new CartController();
	}
	
	@Bean
	OrderDao orderDao() {
		return new OrderDao() {};
	}
	
	/*
	 * Utils methods for test
	 */
	
	private CartEntry cartEntry(String id, int qty) {
		CartEntry e = new CartEntry();
		e.setId(id);
		e.setQty(qty);
		return e;
	}
	
	/*
	 * Tests
	 */

	@Autowired
	CartMockDao cartDao;
	
	@Autowired
	CartController ctrl;
	
	@Test
	public void cartAdd_Ok() throws Exception {
		assertEquals( Status.OK, 
			ctrl.add(
				1, 
				cartEntry("10101010", 1)
			).status
		);
	}
	
	@Test
	public void cartAdd_NoArticle() throws Exception {
		assertEquals( Status.ERROR, 
			ctrl.add(
				1, 
				cartEntry("10101019", 1)
			).status
		);
	}
	
	@Test
	public void cartAdd_NegativeQty() throws Exception {
		assertEquals( Status.ERROR, 
			ctrl.add(
				-1, 
				cartEntry("10101010", 1)
			).status
		);
	}
	
	@Test
	public void cartAdd_NewCart() throws Exception {
		assertEquals( Status.OK, 
			ctrl.add(
				2, 
				cartEntry("10101010", 1)
			).status
		);
		long cpt =
			cartDao
			.getCartContent(2)
			.getArticles()
			.stream()
			.filter( a -> a.getId().equals("10101010") )
			.count();
		assertEquals(1, cpt);
	}
	
	@Test
	public void cartGet() throws Exception {
		ExtendedModelMap m = new ExtendedModelMap();
		String view = ctrl.getCart(1, m);
		assertEquals("_cart_header", view);
		assertEquals(cartDao.getCartContent(1), m.get("cart"));
	}
	
	
}
