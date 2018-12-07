package fr.eservices.honey.model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

@Component
public class HoneyPotMemoryDao implements HoneyDao {
	
	Map<Integer, HoneyPot> pots = new HashMap<>();
	AtomicInteger nextId = new AtomicInteger(1);
	
	@Override
	public HoneyPot find(int id) {
		return pots.get(id);
	}
	
	@Override
	public boolean delete(int id) {
		return pots.remove(id) != null;
	}
	
	@Override
	public HoneyPot save(HoneyPot honeyPot) {
		if ( honeyPot.id == 0 ) honeyPot.id = nextId.getAndIncrement();
		pots.put(honeyPot.id, honeyPot);
		return honeyPot;
	}
	
	

}
