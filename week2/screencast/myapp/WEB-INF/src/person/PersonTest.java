package person;

import java.util.List;

public class PersonTest {
	
	public static void main(String[] args) {
		PersonDao dao = new PersonSQLiteImpl("persons.db");
		
		List<Person> persons = dao.listAll();
		if ( persons.isEmpty() ) {
			System.out.println("Liste des personnes vide");
		}
		else
		for ( Person person : persons ) {
			System.out.println(
				String.format(
					"%4d | %16s | %16s | %3d", 
					person.getId(),
					person.getFirtName(),
					person.getLastName(),
					person.getAge()
				)
			);
		}
	}

}
