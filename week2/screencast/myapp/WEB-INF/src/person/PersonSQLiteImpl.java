package person;

import java.sql.*;
import java.util.*;

public class PersonSQLiteImpl implements PersonDao {
	
	private Connection conn;
	
	public PersonSQLiteImpl( String dbFile ) {
		// open a connection with proper jdbc url
	}
	
	
	@Override
	public List<Person> listAll() {
		// get all persons and assign each to a list
		return new ArrayList<>();
	}

}
