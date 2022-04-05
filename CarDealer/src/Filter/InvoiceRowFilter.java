package Filter;

import javax.swing.RowFilter;

/**
 * @author Andrew Gilbey/C00263656
 *
 */
public class InvoiceRowFilter extends RowFilter {

private String searched;
	
	public InvoiceRowFilter(String searched) {

		this.searched = searched;
	}

	@Override
	public boolean include(Entry entry) {
		return entry.getStringValue(1).indexOf(searched) >= 0 ||  entry.getStringValue(2).indexOf(searched) >= 0 
				|| entry.getStringValue(0).indexOf(searched) >= 0 || entry.getStringValue(3).indexOf(searched) >= 0 
				|| entry.getStringValue(4).indexOf(searched) >= 0 || entry.getStringValue(5).indexOf(searched) >= 0
				|| entry.getStringValue(6).indexOf(searched) >= 0;

		
	}
}



