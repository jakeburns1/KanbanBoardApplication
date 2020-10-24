package mainPack;

import java.util.ArrayList;

public class ListConcrete implements ListN
{

	String listName;
	
	ArrayList<Card> cards = new ArrayList<Card>();
	

	
	/**
	 * @param listName
	 */
	public ListConcrete(String listName)
	{
		super();
		this.listName = listName;
	}

	@Override
	public void addCards(Card cardToAdd)
	{
		cards.add(cardToAdd);
	}
	
	public void removeCard(Card cardToRemove) {
		cards.remove(cardToRemove);
	}

	/**
	 * @return the listName
	 */
	public String getListName()
	{
		return listName;
	}

	/**
	 * @return the cards
	 */
	public ArrayList<Card> getCards()
	{
		return cards;
	}

	public void updateName(String newName)
	{
		listName = newName;
	}

	public void reorderCard(Card selected, int newIndex)
	{
		
		Card copy = selected;
		cards.add(newIndex, copy);
		cards.remove(selected);
		
	}
	
	public int getSize() {
		return cards.size();
	}
	
	public void moveCard(Card current, ListN newList, int destIndex) // ?
	{
		Card temp = current;
		cards.remove(current);
		if(newList.getCards().isEmpty()) {
			destIndex = 0;
		}
		else {
			destIndex = newList.getSize();
		}
		newList.addCards(temp);
		newList.reorderCard(temp, destIndex);
	
		
	}

}
