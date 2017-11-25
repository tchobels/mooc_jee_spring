package spring;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.eservices.drive.web.dto.CartEntry;
import fr.eservices.drive.web.dto.SimpleResponse;
import web.HtmlUtils;
import web.WebTool;

public class JSTLCartCheck {
	
	WebTool wt;
	ObjectMapper mapper = new ObjectMapper();
	
	@Before
	public void init() {
		String port = System.getProperty("usePort");
		int p = 8080;
		if ( port != null ) {
			p = Integer.parseInt(port);
		}
		wt = new WebTool("localhost:"+p, "/exo501");
	}
	
	@Test
	public void testRunTomcat() throws Exception {
		assertTrue(
			WebTool.toString(
				wt.get("/exo501.txt")
			).contains("eiV0ahng")
		);
	}
	
	@Test
	public void cartContentOne() throws Exception {
		String cart = HtmlUtils.toString( wt.get("/cart/1.html") );
		
		Assert.assertTrue( cart.contains("Boisson énergétique") );
		Assert.assertTrue( cart.contains("2,99") );
		
		Assert.assertTrue( cart.contains("Papier Cadeau") );
		Assert.assertTrue( cart.contains("1,50") );
		
		Assert.assertTrue( cart.contains("Pur jus d'orange") );
		Assert.assertTrue( cart.contains("2,35") );
	}
	
	@Test
	public void cartNoContent() throws Exception {
		String cart = HtmlUtils.toString( wt.get("/cart/666.html") );
		
		Assert.assertTrue( cart.contains("Aucun article") );
	}
	
	@Test
	public void addToCart_One() throws Exception {
		CartEntry e = new CartEntry();
		e.setId("10101010");
		e.setQty(1);
		String data = mapper.writeValueAsString( e );
		String cart = HtmlUtils.toString( wt.post("/cart/1.json", data) );
		SimpleResponse response = mapper.readValue(cart, SimpleResponse.class);
		assertEquals("OK", response.status);
	}
	
	@Test
	public void addToCart_NoSuchProduct() throws Exception {
		CartEntry e = new CartEntry();
		e.setId("20101010");
		e.setQty(1);
		String data = mapper.writeValueAsString( e );
		String cart = HtmlUtils.toString( wt.post("/cart/1.json", data) );
		SimpleResponse response = mapper.readValue(cart, SimpleResponse.class);
		assertEquals("ERROR", response.status);
	}
	
	@Test
	public void addToCart_QtyError() throws Exception {
		CartEntry e = new CartEntry();
		e.setId("10101010");
		e.setQty(-1);
		String data = mapper.writeValueAsString( e );
		String cart = HtmlUtils.toString( wt.post("/cart/1.json", data) );
		SimpleResponse response = mapper.readValue(cart, SimpleResponse.class);
		assertEquals("ERROR", response.status);
	}
	
	@Test
	public void addToCart_NewCart() throws Exception {
		CartEntry e = new CartEntry();
		e.setId("10101010");
		e.setQty(1);
		String data = mapper.writeValueAsString( e );
		int cartId = (int)(Math.random() * 10000) + 1;
		String cart = HtmlUtils.toString( wt.post("/cart/"+cartId+".json", data) );
		SimpleResponse response = mapper.readValue(cart, SimpleResponse.class);
		assertEquals("OK", response.status);
		
		String content = HtmlUtils.toString( wt.get("/cart/"+cartId+".html") );
		Assert.assertTrue( content.contains("Boisson énergétique") );
	}
	
	@Test
	public void validateCart() throws Exception {
		CartEntry e = new CartEntry();
		e.setId("10101010");
		e.setQty(1);
		String data = mapper.writeValueAsString( e );
		int cartId = (int)(Math.random() * 10000) + 1;
		String cart = HtmlUtils.toString( wt.post("/cart/"+cartId+".json", data) );
		SimpleResponse response = mapper.readValue(cart, SimpleResponse.class);
		assertEquals("OK", response.status);
		
		String content = HtmlUtils.toString( wt.get("/cart/"+cartId+".html") );
		Assert.assertTrue( content.contains("Boisson énergétique") );
	}

}
