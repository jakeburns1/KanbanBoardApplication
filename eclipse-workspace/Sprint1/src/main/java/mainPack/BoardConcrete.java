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
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.BoardController;
import view.LoginModel;

public class BoardConcrete implements Board, Serializable
{

	private static final long serialVersionUID = -468358412443532784L;
	
	public String unique = UUID.randomUUID().toString();
	
	BoardController boardController;

	/**
	 * @return the unique
	 */
	public String getUnique()
	{
		return unique;
	}

	/**
	 * @param unique the unique to set
	 */
	public void setUnique(String unique)
	{
		this.unique = unique;
	}

	String boardName;
	User owner = new UserConcrete();
	Set<User> members = new HashSet<User>();
	ArrayList<ListN> lists;
	
	
	
	
	/**
	 * @param boardName the boardName to set
	 */
	public void setBoardName(String boardName)
	{
		this.boardName = boardName;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(User owner)
	{
		this.owner = owner;
	}

	public long getSerialversionuid()
	{
		return serialVersionUID;
	}


	/**
	 * @param boardName
	 * @param owner
	 * @param members
	 * @param lists
	 */
	public BoardConcrete()
	{
		//String boardName, User owner, Set<User> members, ArrayList<ListN> lists
//		super();
//		this.boardName = boardName;
//		this.owner = owner;
//		this.members = members;
//		this.lists = lists;
	}
	
	public void showBoardScreen(Stage s, Scene scene, BoardConcrete b, RmiClient c)
	{
		try
		{
			FXMLLoader loader3 = new FXMLLoader();
			loader3.setLocation(BoardConcrete.class.getResource("../main/board_view.fxml"));
			BorderPane pane = loader3.load();
			
			BoardController cont3 = loader3.getController();
			cont3.setModel(c, b);
			scene = new Scene(pane);
			s.setScene(scene);
			s.show();
			
			
//			
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Boolean addMember(User member)
	{
		
		members.add(member);
		member.addBoardtoUser(this);
		
		return true;
		
	}

	public Boolean removeMember(User member)
	{
		members.remove(member);
		
		return true;
	}

	public Boolean addList(ListN listToAdd) // ArrayList 
	{
		lists.add(listToAdd);
		return true;
	}

	public Boolean deleteList(ListN listToRemove)
	{
		lists.remove(listToRemove);
		return true;
	}


	
	public void reorderList(ListN selected, int newIndex)
	{
		if(selected.getCards().isEmpty()) {
			newIndex = 0;
		}
		else {
			newIndex = selected.getSize();
		}
		ListN copy = selected;
		lists.add(newIndex, copy);
		lists.remove(selected);
		
	}

	/**
	 * @return the boardName
	 */
	public String getBoardName()
	{
		return boardName;
	}

	/**
	 * @return the owner
	 */
	public User getOwner()
	{
		return owner;
	}

	/**
	 * @return the members
	 */
	public Set<User> getMembers()
	{
		return members;
	}

	/**
	 * @return the lists
	 */
	public ArrayList<ListN> getLists()
	{
		return lists;
	}

	@Override
	public Boolean save()    // xml stuff
	{
		return true;
	}

	
	public Boolean updateBoardName(String updatedName)
	{
		boardName = updatedName;
		return true;
	}

	@Override
	public void setMembers(Set<User> members)
	{
		this.members = members;
	}

	@Override
	public void setLists(ArrayList<ListN> lists)
	{
		this.lists = lists;
		
	}
	
	public void storeToDisk() {
		XMLEncoder encoder=null;
		try{
		encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream("Board.xml")));
		}catch(FileNotFoundException fileNotFound){
			System.out.println("ERROR: While Creating or Opening the File");
		}
		encoder.writeObject(this);
		encoder.close();
	}
	
	public static BoardConcrete loadFromDisk(String string) {
		
		XMLDecoder decoder=null;
		try {
			decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream("Board.xml")));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File dvd.xml not found");
		}
		BoardConcrete b = (BoardConcrete) decoder.readObject();
		return b;
	}
	
	public boolean equals(BoardConcrete that) {
		if(lists.size() != that.lists.size()) {return false;}
		
		if(!that.boardName.equals(boardName)) {
			return false;
		}
		
		for(User u:members) {
			if(!this.containsMem(u)) {
				return false;
			}
		}
		
		for(ListN l:lists) {
			if(!this.contains(l)) {
				return false;
			}
		}
		
		return true;
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

public boolean contains(ListN l2)
{
for (ListN l:lists) {
	 if(l.equals(l2)) {
		 return true;
	 }
}
return false;
}

}
