package mainPack;

import java.util.ArrayList;

public class LabelFilter implements FilterInterface {

	String filterfor;

	public LabelFilter(String filterfor) {
		this.filterfor = filterfor;
	}

	public Card executeFilter(String filterFor, Card card) {

		if (card.getLabels().contains(filterFor)) {
			return card;
		}

		else {
			return null;
		}
	}

	public String getFilterString() {
		return this.filterfor;
	}

}
