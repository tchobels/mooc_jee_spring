package webcart;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Cart {
	
	protected Map<String, Integer> entries;
	
	protected void added( String reference, int quantity ) {}
	protected void updated( String reference, int quantity ) {}
	protected void deleted( String reference ) {}
	
	public void addToCart(String reference, int quantity) {
		
		if ( entries == null ) entries = new HashMap<>();
		
		// Check if entry was in cart
		Integer qte = entries.get( reference );
		if ( qte == null ) {
			// add a new entry
			entries.put( reference, quantity);
			added( reference, quantity );
		}
		else {
			if ( quantity <= 0 ) {
				// remove entry if quantity is lower than 0
				entries.remove(reference);
				deleted(reference);
			}
			else {
				entries.put( reference, quantity );
			}
		}
	}
	
	public void print(PrintWriter out) {
		out.print("<ul>\n");
		for ( Entry<String, Integer> entry : entries.entrySet() ) {
			out.print( String.format("<li>%s (x %03d)</li>\n", entry.getKey(), entry.getValue()) );
		}
		out.print("</ul>\n");
	}
	
	public boolean mayAdd( String reference, int quantity  ) { 
		return true; 
	}

}
