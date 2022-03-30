package Filter;

import javax.swing.RowFilter;
import javax.swing.RowFilter.Entry;

public class StockNoRowFilter extends RowFilter {
	
		
		private String searched;
		
		public StockNoRowFilter(String searched) {

			this.searched = searched;
		}

		@Override
		public boolean include(Entry entry) {
			return entry.getStringValue(0).indexOf(searched) >= 0;

			
		}

	}


