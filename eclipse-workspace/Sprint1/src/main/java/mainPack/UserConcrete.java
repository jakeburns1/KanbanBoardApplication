package mainPack;

import java.util.ArrayList;
import java.util.Set;

public class UserConcrete implements User
{

	String username;
	String password;
	ArrayList<Board> board = new ArrayList<Board>();
	
	public Board createBoard(String boardName, User owner,Set<User> members, ArrayList<ListN> lists) 
	{
		Board newBoard = new BoardConcrete(); 
		newBoard.updateBoardName(boardName);
		newBoard.setMembers(members);
		newBoard.setLists(lists);
		newBoard.setOwner(this);
		board.add(newBoard);
		
		return newBoard;
		
	}
	
	
	
	/**
	 * @return the username
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @return the boards
	 */
	public ArrayList<Board> getBoards()
	{
		return board;
	}

	public void addBoardtoUser(Board newBoard) {
		board.add(newBoard);
	}
	public boolean userHasboards() {
		if (board.isEmpty()) {
			return false;
		}
		else {
			return false;
		}
	}
	public boolean login(String username, String password)
	{
		if (this.username == username && this.password == password) {
			return true;
		}
		
		else{
			return false;
		}
	}


	/**
	 */
	public UserConcrete()
	{
		super();
	
	}


	/**
	 * @param username the username to set
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}




}
