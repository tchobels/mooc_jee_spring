package fr.eservices.drive.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.eservices.drive.model.Article;
import fr.eservices.drive.web.dto.SimpleResponse;
import fr.eservices.drive.web.dto.SimpleResponse.Status;

@Controller
@RequestMapping(path="/cart/")
public class CartController {

	@ResponseBody
	@RequestMapping(path="{id}/add.json",method=RequestMethod.POST,consumes="application/json")
	public SimpleResponse add(@PathVariable(name="id") String id, @RequestBody Article art) {
		SimpleResponse res = new SimpleResponse();
		
		System.out.println(
			"********************\n"
			+ "***** Add Article " + art.getId() + " to cart" 
			+ "********************"
		);
		
		res.status = Status.ERROR;
		res.message = "Not yet implemented";
		return res;
	}
	
	@RequestMapping("{id}/validate.html")
	public void validateCart(@PathVariable(name="id") String id, Model model) {
		
	}
}
