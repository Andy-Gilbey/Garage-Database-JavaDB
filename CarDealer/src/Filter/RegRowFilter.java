package Filter;

import javax.swing.RowFilter;



public class RegRowFilter extends RowFilter {
	
	private String searched;
	
	public RegRowFilter(String searched) {

		this.searched = searched;
	}

	@Override
	public boolean include(Entry entry) {
		return entry.getStringValue(1).indexOf(searched) >= 0;

		
	}

}
