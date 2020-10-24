package mainPack;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BoardConcrete implements Board
{

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
	

}