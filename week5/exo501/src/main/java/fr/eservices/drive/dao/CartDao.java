package fr.eservices.drive.dao;

import fr.eservices.drive.model.Cart;

public interface CartDao {

	Cart getCartContent(int id) throws DataException;

}
