package Filter;

import javax.swing.RowFilter;

import javax.swing.RowFilter.Entry;

/**
 * @author Andrew Gilbey/C00263656
 *
 *
 */



public class StockNoRowFilter extends RowFilter {
	
		
		private String searched;
		
		/**
		 *  StockNoRow which extends the Rowfilter class, it allows a table in a class to be searched and only display values dependent on a given column value.
 * In this case only the first column is checked (which when paired with the table will be "StockNumber".
		 * @param searched
		 */
		public StockNoRowFilter(String searched) {

			this.searched = searched;
		}

		@Override
		public boolean include(Entry entry) {
			return entry.getStringValue(0).indexOf(searched) >= 0;

			
		}

	}


