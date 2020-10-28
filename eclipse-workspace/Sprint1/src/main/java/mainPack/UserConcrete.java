package mainPack;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((board == null) ? 0 : board.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserConcrete other = (UserConcrete) obj;
		if (board == null)
		{
			if (other.board != null)
				return false;
		} else if (!board.equals(other.board))
			return false;
		if (password == null)
		{
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null)
		{
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
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

	public void storeToDisk() {
		XMLEncoder encoder=null;
		try{
		encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream("User.xml")));
		}catch(FileNotFoundException fileNotFound){
			System.out.println("ERROR: While Creating or Opening the File");
		}
		encoder.writeObject(this);
		encoder.close();
	}


public boolean shallowEquals(User a) {
	if(this.username != a.getUsername()) {
		return false;
	}
	
	if(this.password != a.getPassword()) {
		return false;
	}
	
	return true;
		
	}
	/**
	 * @return the board
	 */
	public ArrayList<Board> getBoard()
	{
		return board;
	}



	/**
	 * @param board the board to set
	 */
	public void setBoard(ArrayList<Board> board)
	{
		this.board = board;
	}


}
