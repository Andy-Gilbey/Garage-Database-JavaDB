package Filter;

import javax.swing.RowFilter;

/**
 * @author Andrew Gilbey/C00263656
.
 *
 */

public class RegRowFilter extends RowFilter {
	
	private String searched;
	
	/**
	 * RegRowFilter which extends the Rowfilter class, it allows a table in a class to be searched and only display values dependent on a given column value.
 * 		In this case only the first column is checked (which when paired with the table will be "Reg"
	 * @param searched
	 */
	public RegRowFilter(String searched) {

		this.searched = searched;
	}

	@Override
	public boolean include(Entry entry) {
		return entry.getStringValue(1).indexOf(searched) >= 0;

		
	}

}
