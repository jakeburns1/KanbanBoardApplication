package mainPack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

public interface Board extends Serializable
{

	public Boolean addMember(User member);
	public Boolean removeMember(User member);
	public Boolean addList(ListN listToAdd);
	public Boolean deleteList(ListN listToRemove);
	public Boolean save(); // save(Board);
	public Boolean updateBoardName(String updatedName);
	public Object getBoardName();
	public Object getOwner();
	public void setOwner(User owner);
	public void setMembers(Set<User> members);
	public void setLists(ArrayList<ListN> lists);
	public ArrayList<ListN> getLists();
	public Set<User> getMembers();
	public void reorderList(ListN selected, int newIndex);
	public void storeToDisk();
	public boolean equals(BoardConcrete diskB);
	public long getSerialversionuid();
	public void setUnique(String unique);
	public static BoardConcrete loadFromDisk()
	{
		// TODO Auto-generated method stub
		return null;
	}
	public String getUnique();
	
	

	
	
}
