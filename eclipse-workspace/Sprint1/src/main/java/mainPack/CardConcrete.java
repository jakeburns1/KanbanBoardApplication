package mainPack;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.CardController;

public class CardConcrete implements Card, Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2118681597509186513L;
	
	String cardName;
	Set<String> labels;
	Set<User> members;
	Set<Component> components;
	
	
	
	/**
	 * @param cardName
	 * @param labels
	 * @param members
	 * @param components
	 */
	public CardConcrete(String cardName, Set<String> labels, Set<User> members, Set<Component> components)
	{
		super();
		this.cardName = cardName;
		this.labels = labels;
		this.members = members;
		this.components = components;
	}

	public CardConcrete() {
		
	}
	@Override
	public void createLabel(String text)
	{
		labels.add(text);
	}

	@Override
	public void deleteLabel(String text)
	{
		labels.remove(text);
	}

	/**
	 * @return the members
	 */
	public Set<User> getMembers()
	{
		return members;
	}

	/**
	 * @param members the members to set
	 */
	public void setMembers(User member)
	{
		members.add(member);
	}

	public void deleteMember(User member) {
		members.remove(member);
	}
	/**
	 * @return the cardName
	 */
	public String getCardName()
	{
		return cardName;
	}

	/**
	 * @return the labels
	 */
	public Set<String> getLabels()
	{
		return labels;
	}

	/**
	 * @return the components
	 */
	public Set<Component> getComponents()
	{
		return components;
	}
	
	public Component getDesComponent() {
		Component com = new DescriptionComponent("Set a description!");
		try {
		for (Component c: components) {
			if(c.getClass().equals(com.getClass())) {
				return c;
			}
			else {
				return null;
			}
		}
		}
		catch(Exception e) {
		//e.printStackTrace();
		return com;
		}
		return com;
	}
	
	public ArrayList<Component> getCheckListComponent() {
		Component check = new ChecklistComponent();
		ArrayList<String> test = new ArrayList<String>();
		ArrayList<Component> array = new ArrayList<Component>();
		test.add("Add an item!");
		check.setItems(test);
		try {
		for (Component c: components) {
			if(c.getClass().equals(check.getClass())) {
				array.add(c);
			}
			else {
				return null;
			}
		}
		}
		catch(Exception e) {
		//e.printStackTrace();
		return array;
		}
		return array;
	}

	public void addComponent(Component component)
	{
		components.add(component);
	}

	/**
	 * @param labels the labels to set
	 */
	public void setLabels(Set<String> labels)
	{
		this.labels = labels;
	}

	/**
	 * @param members the members to set
	 */
	public void setMembers(Set<User> members)
	{
		this.members = members;
	}

	/**
	 * @param components the components to set
	 */
	public void setComponents(Set<Component> components)
	{
		this.components = components;
	}

	public void deleteComponent(Component component) {
		components.remove(component);
	}
	
	public void setCardName(String newName)
	{
		cardName = newName;
	}

	
	public void storeToDisk() {
		XMLEncoder encoder=null;
		try{
		encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream("Card.xml")));
		}catch(FileNotFoundException fileNotFound){
			System.out.println("ERROR: While Creating or Opening the File");
		}
		encoder.writeObject(this);
		encoder.close();
	}
	public static CardConcrete loadFromDisk() {
		
		XMLDecoder decoder=null;
		try {
			decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream("Card.xml")));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File dvd.xml not found");
		}
		CardConcrete c = (CardConcrete) decoder.readObject();
		return c;
	}


//	@Override
//	public boolean equals(Object obj)
//	{
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		CardConcrete other = (CardConcrete) obj;
//		if (cardName == null)
//		{
//			if (other.cardName != null)
//				return false;
//		} else if (!cardName.equals(other.cardName))
//			return false;
//		if (components == null)
//		{
//			if (other.components != null)
//				return false;
//		} else if (!components.equals(other.components))
//			return false;
//		if (labels == null)
//		{
//			if (other.labels != null)
//				return false;
//		} else if (!labels.equals(other.labels))
//			return false;
//		if (members == null)
//		{
//			if (other.members != null)
//				return false;
//		} else if (!members.equals(other.members))
//			return false;
//		return true;
//	}

	public boolean equals(CardConcrete that) {
		if(labels.size() != that.labels.size()) {return false;}
		if(!that.cardName.equals(cardName)) {
			return false;
		}
		
		for(String l:labels) {
			if(!that.contains(l)) {
				return false;
			}
		}
		
	
		for(User u:members) {
			if(!this.containsMem(u)) {
				return false;
			}
		}
////		
//		for(Component c:components) {
//			if(!that.containsCom(c)){
//				return false;
//			}
//		}
		return true;
	}

	public boolean containsCom(Component c)
{
		for(Component d: components) {
			if(d.equals(c)) {
				return true;
			}
		}
	return false;
}

	public boolean containsMem(User u)
{
		for (User b:members) {
			 if(b.shallowEquals(u)) {
				 return true;
			 }
		}
	return false;
}

	public boolean contains(String a)
	{
		for (String l:labels) {
			 if(l.equals(a)) {
				 return true;
			 }
		}
		return false;
	}

}
