package mainPack;

import java.util.ArrayList;
import java.util.Set;

public interface User
{

	
	public Board createBoard(String boardName, User owner,Set<User> members, ArrayList<ListN>lists);
	public boolean login(String username, String password);
	public void setUsername(String string);
	public void setPassword(String string);
	public boolean userHasboards();
	public ArrayList<Board> getBoards();
	public void addBoardtoUser(Board newBoard);
}