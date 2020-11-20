package mainPack;

import java.util.ArrayList;

public class FilterChain
{
	ArrayList<FilterInterface> filters =new ArrayList<FilterInterface>();
	
	
	

	
	public void addLabelFilter(FilterInterface filter) {
		
		filters.add(filter);
		
	}
	
	public void applyFilters() {
		
	}

	/**
	 * @return the filters
	 */
	public ArrayList<FilterInterface> getFilters()
	{
		return filters;
	}
}
