package fr.eservices.honey.model;


public class HoneyPot {
	
	int id;
	String name;
	int quantity;
	int price;
	
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setQuantity(int i) {
		this.quantity = i;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public int getPrice() {
		return price;
	}

}
