package mainPack;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Set;

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

	public ListConcrete() {
		
	}
	/**
	 * @param listName the listName to set
	 */
	public void setListName(String listName)
	{
		this.listName = listName;
	}

	/**
	 * @param cards the cards to set
	 */
	public void setCards(ArrayList<Card> cards)
	{
		this.cards = cards;
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
	
	public void storeToDisk() {
		XMLEncoder encoder=null;
		try{
		encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream("List.xml")));
		}catch(FileNotFoundException fileNotFound){
			System.out.println("ERROR: While Creating or Opening the File");
		}
		encoder.writeObject(this);
		encoder.close();
	}

	


	public static ListConcrete loadFromDisk() {
		
		XMLDecoder decoder=null;
		try {
			decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream("List.xml")));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File dvd.xml not found");
		}
		ListConcrete d = (ListConcrete) decoder.readObject();
		return d;
	}
	
//	public boolean equals(ListConcrete that) {
//		if(cards.size() != that.cards.size()) {return false;}
//		
//	
//		return true;
//	}

	
public boolean equals(ListConcrete that) {
	
	if(!this.getListName().equals(that.getListName())) {
		return false;
	}
	
	for(Card c:cards) {
		if(!this.contains(c)) {
			return false;
		}
	}
	
	return true;
	
	
}

	public boolean contains(Card a)
	{
		for (Card c:cards) {
			 if(c.equals(a)) {
				 return true;
			 }
		}
		return false;
	}
	

	

}
