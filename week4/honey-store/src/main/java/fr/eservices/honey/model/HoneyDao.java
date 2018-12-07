package fr.eservices.honey.model;

public interface HoneyDao {
	
	HoneyPot find(int id);
	boolean delete(int id);
	HoneyPot save(HoneyPot honeyPot);

}
