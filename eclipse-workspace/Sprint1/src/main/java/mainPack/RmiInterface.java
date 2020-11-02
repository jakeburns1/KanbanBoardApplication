package mainPack;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RmiInterface extends Remote
{


	
		//public String concatenate(String string1, String string2) throws RemoteException;
		public User checkUsernamePassword(String user, String pass) throws RemoteException;
		//public Boolean login(String username, String password) throws RemoteException;
		public Board getBoard(String id) throws RemoteException;
		//public Component getCompenet() throws RemoteException;
		public void updateBoard(Board board) throws RemoteException;
		public Board createBoard(String s, User u) throws RemoteException;

}
