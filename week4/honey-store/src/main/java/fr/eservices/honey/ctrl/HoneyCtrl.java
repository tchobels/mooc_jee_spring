package fr.eservices.honey.ctrl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.eservices.honey.app.PotCreator;
import fr.eservices.honey.model.HoneyDao;
import fr.eservices.honey.model.HoneyPot;

@RestController
@RequestMapping("/pots")
public class HoneyCtrl {
	
	@Autowired
	HoneyDao dao;
	
	@Autowired
	PotCreator creator;
	
	@PostConstruct
	public void initPots() {
		creator.initializePots(10);
	}

	@GetMapping("/{id}")
	public HoneyPot getPot( @PathVariable int id ) {
		HoneyPot pot = dao.find(id);
		if ( Math.random() < 0.9 )
			pot.setName("Super honey pot");
		else 
			pot.setName("Extra honey pot");
		return pot;
	}
	
	
}
