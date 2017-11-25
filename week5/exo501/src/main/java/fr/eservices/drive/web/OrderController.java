package fr.eservices.drive.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/order")
public class OrderController {
	
	@RequestMapping(path="/list.html")
	public String list(Model model) {
		return "order_list";
	}

}
