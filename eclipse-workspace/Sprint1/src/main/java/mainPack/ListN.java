package mainPack;

import java.util.ArrayList;

public interface ListN
{
	public void addCards(Card cardToAdd);
	public void updateName(String newName);
	public void reorderCard(Card selected, int newIndex); // reorderCard(index);
	public void removeCard(Card cardToRemove);
	public void moveCard(Card current, ListN newList, int destIndex);
	public ArrayList<Card> getCards();
	public int getSize();
}
