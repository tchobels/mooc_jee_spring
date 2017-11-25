package fr.eservices.drive.web;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import fr.eservices.drive.dao.CartDao;
import fr.eservices.drive.dao.DataException;
import fr.eservices.drive.model.Cart;
import fr.eservices.drive.web.dto.CartEntry;
import fr.eservices.drive.web.dto.SimpleResponse;
import fr.eservices.drive.web.dto.SimpleResponse.Status;

@Controller
@RequestMapping(path="/cart")
public class CartController {
	
	@Autowired
	CartDao dao;
	
	@ExceptionHandler(DataException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String dataExceptionHandler(Exception ex) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintWriter w = new PrintWriter( out );
		ex.printStackTrace(w);
		w.close();
		return 
			"ERROR"
			+ "<!--\n" + out.toString() + "\n-->";
	}
	
	@GetMapping(path="/{id}.html", produces="text/html")
	public String getCart(@PathVariable(name="id") String id, Model model) throws DataException {
		Cart c = dao.getCartContent( Integer.parseInt(id) );
		model.addAttribute("cart", c);
		return "_cart_header";
	}

	@ResponseBody
	@PostMapping(path="/{id}/add.json",consumes="application/json")
	public SimpleResponse add(@PathVariable(name="id") String id, @RequestBody CartEntry art) {
		SimpleResponse res = new SimpleResponse();
		
		System.out.println(
			"********************\n"
			+ "***** " + String.format("Add Article %d x [%s] to cart", art.getQty(), art.getId()) + "\n" 
			+ "********************"
		);
		
		res.status = Status.ERROR;
		res.message = "Not yet implemented";
		return res;
	}
	
	@RequestMapping("/{id}/validate.html")
	public String validateCart(@PathVariable(name="id") String id, Model model) {
		
		// Do the job, then redirect to order list
		
		return "redirect:../order/list.html";
	}
}
